package lld.multilevelcache.cache;

import lld.multilevelcache.cache.strategy.CacheEvictionStrategy;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCache<K, V> implements DataStoreCache<K, V> {

    private int hits;
    private int misses;
    private final int capacity;
    private final Map<K, V> kvMap;
    private final CacheEvictionStrategy<K> cacheEvictionStrategy;

    public InMemoryCache(int capacity, CacheEvictionStrategy<K> cacheEvictionStrategy) {
        this.capacity = capacity;
        this.kvMap = new HashMap<>();
        this.cacheEvictionStrategy = cacheEvictionStrategy;
        this.hits = 0;
        this.misses = 0;
    }

    @Override
    public void put(K key, V value) {
        synchronized (this) {
            if (kvMap.size() == capacity) {
                K keyEvicted = cacheEvictionStrategy.evict();
                kvMap.remove(keyEvicted);
            }
            kvMap.put(key, value);
            cacheEvictionStrategy.keyAccessed(key);
        }
    }

    @Override
    public V get(K key) {
        V value = kvMap.get(key);
        if (value == null) {
            synchronized (this) {
                ++misses;
                return null;
            }
        }
        synchronized (this) {
            ++hits;
            cacheEvictionStrategy.keyAccessed(key);
        }
        return value;
    }

    @Override
    public int getHits() {
        return hits;
    }

    @Override
    public int getMisses() {
        return misses;
    }

    @Override
    public void showStatistics() {
        System.out.println("Number of hits : " + hits);
        System.out.println("Number of misses : " + misses);
    }
}
