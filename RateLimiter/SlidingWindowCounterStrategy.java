package RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounterStrategy implements RateLimitingStrategy {

    private final ConcurrentHashMap<String, long[]> counters = new ConcurrentHashMap<>();

    @Override
    public RateLimitDecision allow(String key, RateLimitRule rule, long nowMillis) {
        String slotKey = key + ":" + rule.getId();
        long windowSize = rule.getWindowMillis();
        long currWindowStart = (nowMillis / windowSize) * windowSize;

        long[] slot = counters.compute(slotKey, (k, s) -> {
            if (s == null) return new long[]{currWindowStart - windowSize, 0, currWindowStart, 0};
            return s;
        });

        synchronized (slot) {
            if (currWindowStart > slot[2]) {
                if (currWindowStart == slot[2] + windowSize) {
                    slot[0] = slot[2];
                    slot[1] = slot[3];
                } else {
                    slot[0] = currWindowStart - windowSize;
                    slot[1] = 0;
                }
                slot[2] = currWindowStart;
                slot[3] = 0;
            }

            double elapsed = nowMillis - currWindowStart;
            double overlapRatio = 1.0 - (elapsed / windowSize);
            double estimated = (slot[1] * overlapRatio) + slot[3];

            if (estimated < rule.getMaxRequests()) {
                slot[3]++;
                return RateLimitDecision.allowed("Allowed by sliding window");
            }

            long retryAfter = (long) ((estimated - rule.getMaxRequests() + 1)
                              / rule.getMaxRequests() * windowSize);
            return RateLimitDecision.denied(
                "Rate limit exceeded (sliding window)",
                rule.getId(),
                retryAfter
            );
        }
    }

    @Override
    public String name() { return "SlidingWindow"; }
}