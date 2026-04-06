package RateLimiter;

// PluggableRateLimiter.java
import java.util.List;
import java.util.Objects;

public class PluggableRateLimiter implements RateLimiter {

    private final RateLimitingStrategy strategy;
    private final List<RateLimitRule> rules;
    private final Clock clock;

    public PluggableRateLimiter(RateLimitingStrategy strategy,
                                 List<RateLimitRule> rules,
                                 Clock clock) {
        this.strategy = Objects.requireNonNull(strategy);
        this.rules     = Objects.requireNonNull(rules);
        this.clock     = Objects.requireNonNull(clock);
    }

    @Override
    public RateLimitDecision shouldAllow(String key) {
        long now = clock.nowMillis();
        // ALL configured rules must pass (fail-fast on first violation)
        for (RateLimitRule rule : rules) {
            RateLimitDecision decision = strategy.allow(key, rule, now);
            if (!decision.isAllowed()) {
                return decision;
            }
        }
        return RateLimitDecision.allowed("Request permitted");
    }
}