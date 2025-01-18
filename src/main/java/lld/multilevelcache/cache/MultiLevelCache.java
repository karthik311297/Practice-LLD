package lld.multilevelcache.cache;

import java.util.LinkedList;
import java.util.List;

public class MultiLevelCache<K, V> {

    private List<DataStoreCache<K, V>> caches;

    public MultiLevelCache(List<DataStoreCache<K, V>> caches) {
        this.caches = new LinkedList<>();
        this.caches.addAll(caches);
    }

    public V get(K key) {
        for (int i = 0; i < caches.size(); i++) {
            DataStoreCache<K, V> cache = caches.get(i);
            V value = cache.get(key);
            if (value != null) {
                put(key, value, i);
                System.out.println("Got data from cache at level : " + (i + 1));
                return value;
            }
        }
        return null;
    }

    public void put(K key, V value) {
        put(key, value, caches.size());
    }

    private void put(K key, V value, int level) {
        for (int i = 0; i < level; i++) {
            DataStoreCache<K, V> cache = caches.get(i);
            cache.put(key, value);
        }
    }

    public void displayStats() {
        for (int i = 0; i < caches.size(); i++) {
            System.out.println("--------------------");
            System.out.println("Showing stats for cache at level : " + (i + 1));
            caches.get(i).showStatistics();
            System.out.println("--------------------");
        }
    }
}
