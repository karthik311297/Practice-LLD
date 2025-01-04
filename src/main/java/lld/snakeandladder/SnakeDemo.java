package lld.snakeandladder;

import java.util.Arrays;
import java.util.List;

public class SnakeDemo {
    /*
        Board
            Cell[]
            getCell(int pos)

         Cell - start, end
         /
        Normal, Snake, ladder

        Game
            Board
            Map<Player, boardsize> playerPos
            Player[]
            Dice[]
            playNextMove()
            {
                number on dice = roll dice;
                getPlayerPosition(current position, number on dice)
            }
            hasWinner()

        Player
            name

        Dice - maxNumber
            throw()

     */

    public static void main(String[] args) {
        List<Snake> snakes = Arrays.asList(new Snake(12, 5), new Snake(40, 19), new Snake(70, 13), new Snake(83, 24), new Snake(95, 1));
        List<Ladder> ladders = Arrays.asList(new Ladder(6, 18), new Ladder(33, 55), new Ladder(21, 62), new Ladder(59, 86), new Ladder(84, 99));
        Game game = new Game(101, 2, 6, Arrays.asList(new Player("p1"), new Player("p2"), new Player("p3")), snakes, ladders);
        while (!game.hasWinner()) {
            game.doNextMove();
        }
        System.out.println("--------------------");
        System.out.println("The WINNER is : " + game.getWinnerPlayer());
    }
}
