package lld.moviesearchwithmultilevelcache;

import java.util.Objects;

public class Movie {

    private String title;
    private int durationInHours;

    public Movie(String title, int durationInHours) {
        this.title = title;
        this.durationInHours = durationInHours;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return durationInHours == movie.durationInHours && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, durationInHours);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", durationInHours=" + durationInHours +
                '}';
    }
}
