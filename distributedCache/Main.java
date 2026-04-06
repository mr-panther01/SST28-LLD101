package distributedCache;

// Main.java
public class Main {
    public static void main(String[] args) {
        Database<String, String> db = new InMemoryDatabase<>();
        db.put("user:1", "Alice");
        db.put("user:2", "Bob");
        db.put("user:3", "Charlie");

        DistributedCache<String, String> cache = new DistributedCache<>(
            3,                                    // 3 nodes
            2,                                    // capacity 2 per node
            new ModuloDistributionStrategy<>(),
            new LRUEvictionPolicyFactory<>(),
            db
        );

        System.out.println(cache.get("user:1"));  // Alice
        System.out.println(cache.get("user:2"));  // Bob
        System.out.println(cache.get("user:1"));  // Alice (HIT)

        cache.put("user:4", "Diana");
        cache.put("user:7", "Eve");   // same node as user:1 (hash % 3 == 0)

        System.out.println(cache.get("user:1"));  // miss again after eviction

        DistributedCache<String, String> cache2 = new DistributedCache<>(
            3, 2,
            new ModuloDistributionStrategy<>(),   // swap to ConsistentHashStrategy here
            new LRUEvictionPolicyFactory<>(),
            db
        );
        System.out.println(cache2.get("user:3")); // Charlie
    }
}
