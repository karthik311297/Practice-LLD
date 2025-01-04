package lld.snakeandladder;

import java.util.Random;

public class Dice {
    private final int id;
    private final int maxNumber;
    private final Random random = new Random();

    public Dice(int id, int maxNumber) {
        this.id = id;
        this.maxNumber = maxNumber;
    }

    public int roll() {
        int lowerBound = 1;
        return random.nextInt(lowerBound, maxNumber + 1);
    }

    public int getId() {
        return id;
    }
}
