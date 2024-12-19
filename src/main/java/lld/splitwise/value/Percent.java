package lld.splitwise.value;

public class Percent implements Value {

    private final int value;

    public Percent(int value) {
        this.value = value;
    }

    @Override
    public int get() {
        return value;
    }
}
