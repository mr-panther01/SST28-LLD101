package RateLimiter;

// RateLimitDecision.java
public class RateLimitDecision {
    private final boolean allowed;
    private final String message;
    private final String violatedRuleId;
    private final long retryAfterMillis;

    private RateLimitDecision(boolean allowed, String message,
                               String violatedRuleId, long retryAfterMillis) {
        this.allowed = allowed;
        this.message = message;
        this.violatedRuleId = violatedRuleId;
        this.retryAfterMillis = retryAfterMillis;
    }

    public static RateLimitDecision allowed(String message) {
        return new RateLimitDecision(true, message, null, 0);
    }

    public static RateLimitDecision denied(String message,
                                            String violatedRuleId,
                                            long retryAfterMillis) {
        return new RateLimitDecision(false, message, violatedRuleId, retryAfterMillis);
    }

    public boolean isAllowed()          { return allowed; }
    public String getMessage()          { return message; }
    public String getViolatedRuleId()   { return violatedRuleId; }
    public long getRetryAfterMillis()   { return retryAfterMillis; }
}