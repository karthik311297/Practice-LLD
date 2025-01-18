package lld.multilevelcache.repository;

public interface Repository<T> {

    void create(T t);
    T find(T t);
}
