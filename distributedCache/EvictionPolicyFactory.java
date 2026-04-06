package distributedCache;

public interface EvictionPolicyFactory<K> {
    EvictionPolicy<K> create();
}