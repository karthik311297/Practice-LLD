package lld.googlecalendar;

public class Location {
    private String name;

    public Location(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                '}';
    }
}
