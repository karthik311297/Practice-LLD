package lld.snake;

import java.util.Arrays;

public class GameDemo {
    /*
        Game
            Board
            SnakeController

        Board
            int rowSize
            int colSize
            Cell[] cells
            print()
            updateCell()

        Cell
            int x
            int y
            CellEntity ce

        Direction
            UP
            DOWN
            LEFT
            RIGHT

        enum CellEntity
            SnakeBody
            Food

        Snake
            Deque<Cell> snakeBodyCells
            Dequeue<Cell> snakeOutofBoundQueue

        SnakeController
            Snake
            move(Direction d)

     */

    public static void main(String[] args) {
        Board board = new Board(6, 6, Arrays.asList(new int[]{0, 4}, new int[]{4, 4}, new int[]{2, 3}));
        Snake snake = new Snake(new Cell(0, 0));
        Game game = new Game(board, snake);
        game.getBoard().updateCell(0, 0, CellEntity.SNAKE_BODY);
        game.getBoard().print();
        game.makeMove(DIRECTION.RIGHT);
        game.getBoard().print();
        game.makeMove(DIRECTION.RIGHT);
        game.getBoard().print();
        game.makeMove(DIRECTION.RIGHT);
        game.getBoard().print();
        game.makeMove(DIRECTION.RIGHT);
        game.getBoard().print();
        game.makeMove(DIRECTION.DOWN);
        game.getBoard().print();
        game.makeMove(DIRECTION.DOWN);
        game.getBoard().print();
        game.makeMove(DIRECTION.LEFT);
        game.getBoard().print();
        game.makeMove(DIRECTION.LEFT);
        game.getBoard().print();

    }
}
