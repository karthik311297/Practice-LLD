package lld.snake;

public class SnakeController {
    private Snake snake;
    private Game game;

    public SnakeController(Snake snake, Game game) {
        this.snake = snake;
        this.game = game;
    }

    public void moveUp() throws GameOverException {
        Cell snakeHead = snake.getHead();
        Cell nextCell = game.getBoard().get(snakeHead.getRow() - 1, snakeHead.getCol());
        move(nextCell);
    }

    public void moveDown() throws GameOverException {
        Cell snakeHead = snake.getHead();
        Cell nextCell = game.getBoard().get(snakeHead.getRow() + 1, snakeHead.getCol());
        move(nextCell);
    }

    public void moveRight() throws GameOverException {
        Cell snakeHead = snake.getHead();
        Cell nextCell = game.getBoard().get(snakeHead.getRow(), snakeHead.getCol() + 1);
        move(nextCell);
    }

    public void moveLeft() throws GameOverException {
        Cell snakeHead = snake.getHead();
        Cell nextCell = game.getBoard().get(snakeHead.getRow(), snakeHead.getCol() - 1);
        move(nextCell);
    }


    private void move(Cell nextCell) throws GameOverException {

        if(nextCell.getCellEntity() == CellEntity.SNAKE_BODY) throw new GameOverException();
        else if(nextCell.getCellEntity() == CellEntity.FOOD)
        {
            snake.eatFood(nextCell);
            game.getBoard().updateCell(nextCell.getRow(), nextCell.getCol(), CellEntity.SNAKE_BODY);
        }
        else
        {
            Cell cellNoMoreOccupied = snake.moveToCell(nextCell);
            game.getBoard().updateCell(cellNoMoreOccupied.getRow(), cellNoMoreOccupied.getCol(), null);
            game.getBoard().updateCell(nextCell.getRow(), nextCell.getCol(), CellEntity.SNAKE_BODY);
        }
    }
}
