package lld.bowling;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreCard {

    private Map<String, Integer[]> playerFrameScores;

    public ScoreCard(List<Player> players, int numFrames) {
        playerFrameScores = new HashMap<>();
        for(Player p : players)
        {
            playerFrameScores.put(p.getName(), new Integer[numFrames]);
        }
    }

    public void updateScoreForPlayer(Player player, int frameNumber, int score)
    {
        playerFrameScores.get(player.getName())[frameNumber] = score;
    }

    public String getWinner()
    {
        String  winner = null;
        int maxScore = 0;
        for(Map.Entry<String, Integer[]> entry : playerFrameScores.entrySet())
        {
            Integer[] frameScores = entry.getValue();
            int score = Arrays.stream(frameScores).mapToInt(frameScore -> frameScore).sum();
            if(score > maxScore)
            {
                winner = entry.getKey();
                maxScore = score;
            }
        }
        return winner;
    }
}
