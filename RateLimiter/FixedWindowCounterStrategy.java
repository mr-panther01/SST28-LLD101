package RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowCounterStrategy implements RateLimitingStrategy {

    private final ConcurrentHashMap<String, long[]> counters = new ConcurrentHashMap<>();

    @Override
    public RateLimitDecision allow(String key, RateLimitRule rule, long nowMillis) {
        String slotKey = key + ":" + rule.getId();
        long windowStart = (nowMillis / rule.getWindowMillis()) * rule.getWindowMillis();

        counters.compute(slotKey, (k, existing) -> {
            if (existing == null || existing[0] != windowStart) {
                return new long[]{windowStart, 0};
            }
            return existing;
        });

        long[] slot = counters.get(slotKey);
        synchronized (slot) {
            if (slot[0] != windowStart) {
                slot[0] = windowStart;
                slot[1] = 0;
            }
            if (slot[1] < rule.getMaxRequests()) {
                slot[1]++;
                return RateLimitDecision.allowed("Allowed by fixed window");
            }
        }

        long windowEnd = windowStart + rule.getWindowMillis();
        return RateLimitDecision.denied(
            "Rate limit exceeded (fixed window)",
            rule.getId(),
            windowEnd - nowMillis
        );
    }

    @Override
    public String name() { return "FixedWindow"; }
}
