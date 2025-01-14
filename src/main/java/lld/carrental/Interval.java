package lld.carrental;

import java.time.LocalDateTime;
import java.util.Objects;

public class Interval {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Interval(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Objects.equals(startDate, interval.startDate) && Objects.equals(endDate, interval.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
