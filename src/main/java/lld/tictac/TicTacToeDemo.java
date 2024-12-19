package lld.tictac;

import static lld.tictac.GameUtil.CROSS;
import static lld.tictac.GameUtil.ZERO;

public class TicTacToeDemo {

    public static void main(String[] args) throws NotEnoughPlayersException {
        Player p1 = new Player("p1", CROSS);
        Player p2 = new Player("p2", ZERO);
        Game game = new Game();
        game.join(p1);
        game.join(p2);

        game.makeMove(0, 0);
        game.makeMove(2, 0);
        game.makeMove(0, 1);
        game.makeMove(0, 2);
        game.makeMove(2, 2);
        game.makeMove(1, 1);

        if (!game.isDrawn() && game.getWinner().isPresent()) {
            System.out.println("\nThe winner is: " + game.getWinner().get().getName() + " with symbol: " + game.getWinner().get().getSymbol());
        }
        game.clear();
    }
}
