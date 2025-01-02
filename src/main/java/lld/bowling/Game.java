package lld.bowling;

import java.util.List;

public class Game {
    public static final int STRIKE_BONUS = 10;
    public static final int SPARE_BONUS = 5;
    private int id;
    private GameConfig gameConfig;
    private List<Player> players;
    private ScoreCard scoreCard;
    private int currentFrame;
    private Player winner;
    private BowlRoller bowlRoller;

    public Game(int id, List<Player> players, GameConfig gameConfig) {
        this.id = id;
        this.gameConfig = gameConfig;
        this.players = players;
        this.scoreCard = new ScoreCard(players, gameConfig.getNumFrames());
        this.currentFrame = 0;
        this.winner = null;
        this.bowlRoller = new BowlRoller();
    }

    public void play()
    {
        while (currentFrame < gameConfig.getNumFrames())
        {
            for (Player player : players)
            {
                int numChances = gameConfig.getChances();
                int initialStandingPins = gameConfig.getStandingPins();
                Frame frame = new Frame(initialStandingPins, 0, numChances);
                while (frame.getStandingPins() != 0 && frame.getChancesRemaining() != 0)
                {
                    int pinsCleared = bowlRoller.roll(frame.getStandingPins());
                    frame.setChancesRemaining(frame.getChancesRemaining() - 1);
                    frame.setStandingPins(frame.getStandingPins() - pinsCleared);
                    if (frame.getStandingPins() == 0 && frame.getChancesRemaining() == 1)
                    {
                        frame.setScore(initialStandingPins + STRIKE_BONUS);
                        break;
                    }
                    if (frame.getStandingPins() == 0)
                    {
                        frame.setScore(initialStandingPins + SPARE_BONUS);
                    }
                    else
                    {
                        frame.setScore(initialStandingPins - frame.getStandingPins());
                    }
                }
                scoreCard.updateScoreForPlayer(player, currentFrame, frame.getScore());
            }
            ++currentFrame;
        }
    }

    public void displayWinner()
    {
        String winner = scoreCard.getWinner();
        System.out.println("The winner is : " + winner);
    }
}
