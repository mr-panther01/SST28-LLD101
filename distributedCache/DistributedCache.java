package distributedCache;

// DistributedCache.java
import java.util.ArrayList;
import java.util.List;

public class DistributedCache<K, V> {

    private final List<CacheNode<K, V>> nodes;
    private final DistributionStrategy<K, V> distributionStrategy;
    private final Database<K, V> database;

    public DistributedCache(int numberOfNodes,
                             int capacityPerNode,
                             DistributionStrategy<K, V> distributionStrategy,
                             EvictionPolicyFactory<K> evictionPolicyFactory,
                             Database<K, V> database) {
        this.distributionStrategy = distributionStrategy;
        this.database = database;
        this.nodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new CacheNode<>("node-" + i, capacityPerNode, evictionPolicyFactory));
        }
    }

    /**
     * Cache-aside pattern:
     * 1. Hash the key → select the responsible node.
     * 2. Check the node (cache hit → return).
     * 3. On miss, read from DB, populate the node, return.
     */
    public V get(K key) {
        CacheNode<K, V> node = distributionStrategy.selectNode(key, nodes);
        V value = node.get(key);

        if (value != null) {
            System.out.printf("[Cache HIT]  key='%s' → %s%n", key, node.getNodeId());
            return value;
        }

        // Cache miss — read-through from the database
        System.out.printf("[Cache MISS] key='%s' → fetching from DB%n", key);
        value = database.get(key);

        if (value != null) {
            node.put(key, value);   // populate cache
        }
        return value;
    }

    /**
     * Write-through: write to both cache and database.
     * Assumption: database.put() is always called; consistency is maintained.
     */
    public void put(K key, V value) {
        CacheNode<K, V> node = distributionStrategy.selectNode(key, nodes);
        node.put(key, value);
        database.put(key, value);
        System.out.printf("[Cache PUT]  key='%s' → %s%n", key, node.getNodeId());
    }
}
