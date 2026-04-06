package distributedCache;

import java.util.List;

public class ModuloDistributionStrategy<K, V> implements DistributionStrategy<K, V> {

    @Override
    public CacheNode<K, V> selectNode(K key, List<CacheNode<K, V>> nodes) {
        // Math.abs guards against negative hashCodes
        int index = Math.abs(key.hashCode()) % nodes.size();
        return nodes.get(index);
    }
}
