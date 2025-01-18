package lld.multilevelcache.cache.strategy;

import java.util.HashMap;
import java.util.Map;

public class LFUStrategy<K> implements CacheEvictionStrategy<K> {

    private final Map<K, Integer> frequencyMap;

    public LFUStrategy() {
        this.frequencyMap = new HashMap<>();
    }

    @Override
    public void keyAccessed(K key) {
        frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
    }

    @Override
    public K evict() {
        K minFrqKey = null;
        int minFrq = Integer.MAX_VALUE;
        for (Map.Entry<K, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() < minFrq) {
                minFrq = entry.getValue();
                minFrqKey = entry.getKey();
            }
        }
        frequencyMap.remove(minFrqKey);
        return minFrqKey;
    }
}
