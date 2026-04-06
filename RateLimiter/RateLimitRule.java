package RateLimiter;
public class RateLimitRule {
    private final String id;
    private final int maxRequests;
    private final long windowMillis;

    private RateLimitRule(String id, int maxRequests, long windowMillis) {
        this.id = id;
        this.maxRequests = maxRequests;
        this.windowMillis = windowMillis;
    }

    public static RateLimitRule perMinutes(String id, int maxRequests, int minutes) {
        return new RateLimitRule(id, maxRequests, (long) minutes * 60_000);
    }

    public static RateLimitRule perHours(String id, int maxRequests, int hours) {
        return new RateLimitRule(id, maxRequests, (long) hours * 3_600_000);
    }

    public String getId()          { return id; }
    public int getMaxRequests()    { return maxRequests; }
    public long getWindowMillis()  { return windowMillis; }
}