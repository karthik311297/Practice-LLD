package lld.moviesearchwithcache;

import lld.multilevelcache.cache.DataStoreCache;
import lld.multilevelcache.cache.InMemoryCache;
import lld.multilevelcache.cache.MultiLevelCache;
import lld.multilevelcache.cache.strategy.LFUStrategy;
import lld.multilevelcache.cache.strategy.LRUStrategy;
import lld.multilevelcache.datastore.DataStore;
import lld.multilevelcache.datastore.InMemoryDataStore;

import java.util.Arrays;

public class DemoM {
    public static void main(String[] args) {
        DataStore<Movie> dataStore = new InMemoryDataStore<>();
        DataStoreCache<String, Movie> l1Cache = new InMemoryCache<>(2, new LRUStrategy<>());
        DataStoreCache<String, Movie> l2Cache = new InMemoryCache<>(3, new LFUStrategy<>());
        MultiLevelCache<String, Movie> movieMultiLevelCache = new MultiLevelCache<>(Arrays.asList(l1Cache, l2Cache));
        MovieRepository movieRepository  = new MovieRepository(dataStore, movieMultiLevelCache);

        Movie m1 = new Movie("m1", 2);
        Movie m2 = new Movie("m2", 1);
        Movie m3 = new Movie("m3", 4);
        Movie m4 = new Movie("m4", 3);
        Movie m5 = new Movie("m5", 2);
        Movie m6 = new Movie("m6", 3);
        Movie m7 = new Movie("m7", 1);
        movieRepository.create(m1);
        movieRepository.create(m2);
        movieRepository.create(m3);
        movieRepository.create(m4);
        movieRepository.create(m5);
        movieRepository.create(m6);
        movieRepository.create(m7);

        Movie mm1 = movieRepository.find(m1);
        Movie mm2 = movieRepository.find(m2);
        Movie mm3 = movieRepository.find(m3);
        Movie mm4 = movieRepository.find(m1);
        Movie mm5 = movieRepository.find(m1);
        Movie mm6 = movieRepository.find(m4);
        Movie mm7 = movieRepository.find(m4);

        movieMultiLevelCache.displayStats();

    }
}
