package lld.multilevelcache.cache;

public interface DataStoreCache<K, V> {
    void put(K key, V value);
    V get(K key);
    int getHits();
    int getMisses();
    void showStatistics();
}
