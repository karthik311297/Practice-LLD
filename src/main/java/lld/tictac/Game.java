package lld.tictac;

import java.util.*;

import static lld.tictac.GameUtil.*;

public class Game {

    private char[][] board;

    private Deque<Player> players;

    private Map<Character, Player> symbolMap;

    public Game() {
        board = new char[][]{{STAR, STAR, STAR}, {STAR, STAR, STAR}, {STAR, STAR, STAR}};
        players = new LinkedList<>();
        symbolMap = new HashMap<>();
    }

    public void join(Player p)
    {
        char symbol = p.getSymbol();
        symbolMap.put(symbol, p);
        players.addLast(p);
    }

    public void makeMove(int x, int y) throws NotEnoughPlayersException
    {
        if(players.size() < 2) throw new NotEnoughPlayersException();
        Player p = players.pollFirst();
        players.addLast(p);
        char symbol = p.getSymbol();
        board[x][y] = symbol;
        System.out.println("Move made by player: " + p.getName() + ", with symbol: " + symbol + ", at (" + x + "," + y + ")");
        GameUtil.printBoard(board);
    }

    public boolean isDrawn()
    {
        return GameUtil.isGameDrawn(board);
    }

    public Optional<Player> getWinner()
    {
        Optional<Character> c =  GameUtil.getWinnerSymbol(board);
        if(c.isPresent()) return Optional.of(symbolMap.get(c.get()));
        return Optional.empty();
    }

    public void clear()
    {
        board = new char[][]{{STAR, STAR, STAR}, {STAR, STAR, STAR}, {STAR, STAR, STAR}};
        players = new LinkedList<>();
        symbolMap = new HashMap<>();
    }
}
