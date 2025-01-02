package lld.bowling;

public class Frame {
    private int standingPins;
    private int score;
    private int chancesRemaining;

    public Frame(int standingPins, int score, int chancesRemaining) {
        this.standingPins = standingPins;
        this.score = score;
        this.chancesRemaining = chancesRemaining;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStandingPins() {
        return standingPins;
    }

    public void setStandingPins(int standingPins) {
        this.standingPins = standingPins;
    }

    public int getChancesRemaining() {
        return chancesRemaining;
    }

    public void setChancesRemaining(int chancesRemaining) {
        this.chancesRemaining = chancesRemaining;
    }
}
