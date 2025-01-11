package lld.snake;

import java.util.Deque;
import java.util.LinkedList;

public class Snake {
    private Deque<Cell> snakeBodyCells;
    // TODO : Implement this usecase
    private Deque<Cell> outOfBound;

    public Snake(Cell startingCell) {
        snakeBodyCells = new LinkedList<>();
        outOfBound = new LinkedList<>();
        snakeBodyCells.addFirst(startingCell);
    }

    public Cell getHead() {
        return snakeBodyCells.peekFirst();
    }

    public Cell moveToCell(Cell cell) {
        snakeBodyCells.addFirst(cell);
        Cell cellNoMoreOccupied = snakeBodyCells.pollLast();
        return cellNoMoreOccupied;
    }

    public void eatFood(Cell cell) {
        snakeBodyCells.addFirst(cell);
    }
}
