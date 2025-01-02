package lld.bowling;

import java.util.Random;

public class BowlRoller {

    private final Random random = new Random();

    public int roll(int standingPins)
    {
        int lowerBound = 0;
        return random.nextInt(lowerBound, standingPins + 1);
    }
}
