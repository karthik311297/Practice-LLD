package lld.snake;

public class Game {

    private Board board;

    private SnakeController snakeController;

    private boolean gameOver = false;

    public Game(Board board, Snake snake) {
        this.board = board;
        this.snakeController = new SnakeController(snake, this);
    }

    public Board getBoard() {
        return board;
    }

    public void makeMove(DIRECTION direction) {
        System.out.println("Making move in direction : " + direction);
        try {
            switch (direction) {
                case UP -> snakeController.moveUp();
                case RIGHT -> snakeController.moveRight();
                case LEFT -> snakeController.moveLeft();
                case DOWN -> snakeController.moveDown();
            }
        } catch (GameOverException e) {
            System.out.println(e.getMessage());
            gameOver = true;

        }
    }
}
