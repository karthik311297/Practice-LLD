package lld.multilevelcache.cache.strategy;

import java.util.HashMap;
import java.util.Map;

public class LRUStrategy<K> implements CacheEvictionStrategy<K> {

    private final DLL<K> dll;
    private final Map<K, Entry<K>> keyEntryMap;

    public LRUStrategy() {
        dll = new DLL<>();
        keyEntryMap = new HashMap<>();
    }

    @Override
    public void keyAccessed(K key) {
        Entry<K> entry;
        if (keyEntryMap.containsKey(key)) {
            entry = keyEntryMap.get(key);
            dll.moveFront(entry);
        } else {
            entry = new Entry<>(key);
            dll.addFront(entry);
        }
        keyEntryMap.put(key, entry);
    }

    @Override
    public K evict() {
        Entry<K> evicted = dll.removeLast();
        keyEntryMap.remove(evicted.getKey());
        return evicted.getKey();
    }

    private static class DLL<K> {
        private Entry<K> head;

        private Entry<K> tail;

        public DLL() {
            head = new Entry<>(null);
            tail = new Entry<>(null);
            head.next = tail;
            tail.prev = head;
        }

        public void addFront(Entry<K> entry) {
            Entry<K> temp = head.next;
            head.next = entry;
            entry.prev = head;
            entry.next = temp;
            temp.prev = entry;
        }

        public Entry<K> removeLast() {
            Entry<K> temp = tail.prev;
            tail.prev = temp.prev;
            temp.prev.next = tail;
            return temp;
        }

        public void moveFront(Entry<K> entry) {
            Entry<K> thePrev = entry.prev;
            Entry<K> theNext = entry.next;
            thePrev.next = theNext;
            theNext.prev = thePrev;
            addFront(entry);
        }
    }

    private static class Entry<K> {
        private K key;
        private Entry<K> next;
        private Entry<K> prev;

        public Entry(K key) {
            this.key = key;
            this.next = null;
            this.prev = null;
        }

        public K getKey() {
            return key;
        }
    }
}
