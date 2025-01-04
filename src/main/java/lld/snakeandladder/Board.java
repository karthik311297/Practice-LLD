package lld.snakeandladder;

import java.util.List;

public class Board {
    private final Cell[] cells;

    public Board(int boardSize, List<Snake> snakes, List<Ladder> ladders) {
        cells = new Cell[boardSize];
        for (int i = 0; i < boardSize; i++)
        {
            cells[i] = new NormalCell(i);
        }
        initializeBoardWithSnakesAndLadders(snakes, ladders);
    }

    private void initializeBoardWithSnakesAndLadders(List<Snake> snakes, List<Ladder> ladders) {
        for(Snake s : snakes)
        {
            cells[s.getStart()] = s;
        }
        for(Ladder l : ladders)
        {
            cells[l.getStart()] = l;
        }
    }

    public Cell getCell(int position)
    {
        return cells[position];
    }
}
