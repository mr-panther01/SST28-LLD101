package RateLimiter;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        RateLimitRule rule = RateLimitRule.perMinutes("rule-5-per-min", 5, 1);
        ExternalProviderClient client = new ExternalProviderClient();
        Clock clock = new SystemClock();

        RateLimitingStrategy strategy = new FixedWindowCounterStrategy();

        RateLimiter rateLimiter = new PluggableRateLimiter(strategy, List.of(rule), clock);
        InternalBusinessService service = new InternalBusinessService(rateLimiter, client);

        for (int i = 1; i <= 8; i++) {
            boolean needsExternal = i != 3 && i != 7; // requests 3 and 7 don't hit the API
            service.processRequest("req-" + i, "T1", needsExternal);
        }
    }
}