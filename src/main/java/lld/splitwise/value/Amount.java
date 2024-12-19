package lld.splitwise.value;

public class Amount implements Value{

    private final int value;

    public Amount(int value) {
        this.value = value;
    }

    @Override
    public int get() {
        return value;
    }
}
