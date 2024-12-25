package lld.vendingmachine;

public enum Money {
    TEN(10),
    TWENTY(20),
    FIFTY(50);

    private int value;

    Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
