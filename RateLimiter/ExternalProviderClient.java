package RateLimiter;

public class ExternalProviderClient {
    public void callProvider(String tenantId, String payload) {
        System.out.printf("[ExternalProvider] tenantId=%s payload=%s%n", tenantId, payload);
    }
}