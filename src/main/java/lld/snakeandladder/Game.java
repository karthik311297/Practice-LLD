package lld.snakeandladder;

import java.util.*;

public class Game {
    private final int boardSize;
    private final int numDice;
    private final int maxDiceNumber;
    private final Board board;
    private Deque<Player> playerQueue;
    private Map<String, Integer> playerPosition;
    private Dice[] dices;
    private String winnerPlayerName = null;

    public Game(int boardSize, int numDice, int maxDiceNumber, List<Player> players, List<Snake> snakes, List<Ladder> ladders) {
        this.boardSize = boardSize;
        this.numDice = numDice;
        this.maxDiceNumber = maxDiceNumber;
        this.board = new Board(boardSize, snakes, ladders);
        initializeDices();
        initializePlayers(players);
    }

    private void initializeDices() {
        this.dices = new Dice[numDice];
        for (int i = 0; i < numDice; i++) {
            this.dices[i] = new Dice(i, maxDiceNumber);
        }
    }

    private void initializePlayers(List<Player> players) {
        playerPosition = new HashMap<>();
        playerQueue = new LinkedList<>();
        playerQueue.addAll(players);
        for (Player p : players) {
            playerPosition.put(p.getName(), 0);
        }
    }

    public boolean hasWinner() {
        for (Map.Entry<String, Integer> entry : playerPosition.entrySet()) {
            if (entry.getValue() == boardSize - 1) {
                winnerPlayerName = entry.getKey();
                return true;
            }
        }
        return false;
    }

    public String getWinnerPlayer() {
        return winnerPlayerName;
    }

    public void doNextMove() {
        Player player = playerQueue.pollFirst();
        String playerName = player.getName();
        System.out.println("----------------------");
        System.out.println("Move being made by player : " + playerName);
        System.out.println("Current position of player is : " + playerPosition.get(playerName));
        playerQueue.addLast(player);
        int totalNumberOnDices = 0;
        for (Dice d : dices) {
            int numberOnDice = d.roll();
            System.out.println("Player gets " + numberOnDice + " on Dice " + d.getId());
            totalNumberOnDices += numberOnDice;

        }
        System.out.println("Total on dices : " + totalNumberOnDices);
        playerPosition.put(playerName, getPosition(playerPosition.get(playerName), totalNumberOnDices));
        System.out.println("Player moves to position : " + playerPosition.get(playerName));

    }

    private int getPosition(int currentPosition, int totalNumberOnDices) {
        if (currentPosition + totalNumberOnDices > boardSize - 1) {
            System.out.println("Player needs to get exact number to win");
            return currentPosition;
        }
        Cell cell = board.getCell(currentPosition + totalNumberOnDices);
        if (cell.getCellType() == CellType.SNAKE) System.out.println("Player bitten by snake");
        else if (cell.getCellType() == CellType.LADDER) System.out.println("Player climbs the ladder");
        return cell.getEnd();
    }
}
