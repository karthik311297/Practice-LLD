package lld.moviesearchwithmultilevelcache;

import lld.multilevelcache.cache.MultiLevelCache;
import lld.multilevelcache.datastore.DataStore;
import lld.multilevelcache.repository.Repository;

import java.util.List;

public class MovieRepository implements Repository<Movie> {

    private final DataStore<Movie> dataStore;
    private final MultiLevelCache<String, Movie> movieMultiLevelCache;

    public MovieRepository(DataStore<Movie> dataStore, MultiLevelCache<String, Movie> movieMultiLevelCache) {
        this.dataStore = dataStore;
        this.movieMultiLevelCache = movieMultiLevelCache;
    }

    @Override
    public void create(Movie movie) {
        dataStore.save(movie);
    }

    @Override
    public Movie find(Movie movieToFind) {
        Movie movie = movieMultiLevelCache.get(movieToFind.toString());
        if (movie != null) return movie;
        List<Movie> movies = dataStore.getAll();
        for (Movie m : movies) {
            if (m.equals(movieToFind)) {
                movie = m;
                movieMultiLevelCache.put(m.toString(), m);
                return movie;
            }
        }
        return null;
    }
}
