package lld.multilevelcache.datastore;

import java.util.List;

public interface DataStore<T> {

    void save(T t);

    List<T> getAll();
}
