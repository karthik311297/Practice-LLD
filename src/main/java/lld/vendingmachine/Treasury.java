package lld.vendingmachine;

public class Treasury {

    private int totalCollected;

    public Treasury() {
        totalCollected = 0;
    }

    public synchronized void addMoney(int value) {
        totalCollected += value;
    }

    public void collect() {
        System.out.println("Total money collected: " + totalCollected);
    }
}
