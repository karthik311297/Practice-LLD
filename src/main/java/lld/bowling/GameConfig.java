package lld.bowling;

public class GameConfig {
    private final int standingPins;
    private final int chances;
    private final int numFrames;

    public GameConfig(int standingPins, int chances, int numFrames) {
        this.standingPins = standingPins;
        this.chances = chances;
        this.numFrames = numFrames;
    }

    public int getStandingPins() {
        return standingPins;
    }

    public int getChances() {
        return chances;
    }

    public int getNumFrames() {
        return numFrames;
    }
}
