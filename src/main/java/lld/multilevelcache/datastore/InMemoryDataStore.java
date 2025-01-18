package lld.multilevelcache.datastore;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataStore<T> implements DataStore<T> {

    private final List<T> objects;

    public InMemoryDataStore() {
        this.objects = new ArrayList<>();
    }

    @Override
    public void save(T t) {
        objects.add(t);
    }

    @Override
    public List<T> getAll() {
        return objects;
    }
}
