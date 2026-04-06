package RateLimiter;


public class InternalBusinessService {

    private final RateLimiter rateLimiter;
    private final ExternalProviderClient externalClient;

    public InternalBusinessService(RateLimiter rateLimiter,
                                    ExternalProviderClient externalClient) {
        this.rateLimiter  = rateLimiter;
        this.externalClient = externalClient;
    }

    public void processRequest(String requestId, String tenantId, boolean needsExternalCall) {
        System.out.printf("[Service] Processing requestId=%s tenantId=%s%n", requestId, tenantId);

        if (needsExternalCall) {
            RateLimitDecision decision = rateLimiter.shouldAllow(tenantId);
            if (!decision.isAllowed()) {
                System.out.printf("[Service] DENIED – %s. Retry after %dms%n",
                    decision.getMessage(), decision.getRetryAfterMillis());
                return;
            }
            externalClient.callProvider(tenantId, "payload-for-" + requestId);
        } else {
            System.out.println("[Service] No external call needed — skipping rate limit check.");
        }
    }
}