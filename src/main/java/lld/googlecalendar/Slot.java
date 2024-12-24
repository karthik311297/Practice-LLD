package lld.googlecalendar;

public class Slot {
    private int start;
    private int end;

    public Slot(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
