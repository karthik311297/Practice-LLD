package lld.multilevelcache.cache.strategy;

public interface CacheEvictionStrategy<K> {

    void keyAccessed(K key);

    K evict();
}
