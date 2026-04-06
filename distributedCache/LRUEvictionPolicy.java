package distributedCache;

import java.util.LinkedHashSet;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

    // LinkedHashSet preserves insertion order; we use it as an ordered access log.
    // Most-recently-used = tail, least-recently-used = head.
    private final LinkedHashSet<K> accessOrder = new LinkedHashSet<>();

    @Override
    public synchronized void onKeyAccess(K key) {
        accessOrder.remove(key);   // remove from current position
        accessOrder.add(key);       // re-insert at tail (most recent)
    }

    @Override
    public synchronized void onKeyInsert(K key) {
        accessOrder.add(key);
    }

    @Override
    public synchronized void onKeyRemove(K key) {
        accessOrder.remove(key);
    }

    @Override
    public synchronized K selectEvictionCandidate() {
        if (accessOrder.isEmpty()) throw new IllegalStateException("No keys to evict");
        return accessOrder.iterator().next();  // head = LRU
    }
}