package distributedCache;

import java.util.HashMap;
import java.util.Map;

public class CacheNode<K, V> {

    private final String nodeId;
    private final int capacity;
    private final EvictionPolicy<K> evictionPolicy;
    private final Map<K, V> data = new HashMap<>();

    public CacheNode(String nodeId, int capacity, EvictionPolicyFactory<K> factory) {
        this.nodeId = nodeId;
        this.capacity = capacity;
        this.evictionPolicy = factory.create();
    }

    public synchronized V get(K key) {
        if (!data.containsKey(key)) return null;
        evictionPolicy.onKeyAccess(key);
        return data.get(key);
    }

    public synchronized void put(K key, V value) {
        if (data.containsKey(key)) {
            data.put(key, value);
            evictionPolicy.onKeyAccess(key);
            return;
        }
        if (data.size() >= capacity) {
            K victim = evictionPolicy.selectEvictionCandidate();
            data.remove(victim);
            evictionPolicy.onKeyRemove(victim);
            System.out.printf("[CacheNode %s] Evicted key='%s' (LRU)%n", nodeId, victim);
        }
        data.put(key, value);
        evictionPolicy.onKeyInsert(key);
    }

    public String getNodeId() { return nodeId; }
}
