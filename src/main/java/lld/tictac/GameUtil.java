package lld.tictac;

import java.util.Optional;

public class GameUtil {

    public static final char STAR = '*';
    public static final char CROSS = 'X';
    public static final char ZERO = '0';

    public static void printBoard(char[][] gameBoard)
    {
        for(int i=0;i<gameBoard.length;i++)
        {
            for(int j=0;j<gameBoard.length;j++)
            {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isGameDrawn(char[][] gameBoard)
    {
        for(int i=0;i<gameBoard.length;i++)
        {
            for(int j=0;i<gameBoard.length;i++)
            {
                if(gameBoard[i][j] == STAR) return false;
            }
        }
        Optional<Character> winner = getWinnerSymbol(gameBoard);
        return winner.isEmpty();
    }

    public static Optional<Character> getWinnerSymbol(char[][] gameBoard)
    {
        for(int i = 0;i<gameBoard.length;i++)
        {
            char symbol1 = gameBoard[i][0];
            char symbol2 = gameBoard[i][1];
            char symbol3 = gameBoard[i][2];
            if(symbol1 == symbol2 && symbol2 == symbol3 && symbol1 != STAR) return Optional.of(symbol1);
        }

        for(int i = 0;i<gameBoard.length;i++)
        {
            char symbol1 = gameBoard[0][i];
            char symbol2 = gameBoard[1][i];
            char symbol3 = gameBoard[2][i];
            if(symbol1 == symbol2 && symbol2 == symbol3 && symbol1 != STAR) return Optional.of(symbol1);
        }

        char symbol1 = gameBoard[0][0];
        char symbol2 = gameBoard[1][1];
        char symbol3 = gameBoard[2][2];
        if(symbol1 == symbol2 && symbol2 == symbol3 && symbol1 != STAR) return Optional.of(symbol1);

        symbol1 = gameBoard[0][2];
        symbol2 = gameBoard[1][1];
        symbol3 = gameBoard[2][0];
        if(symbol1 == symbol2 && symbol2 == symbol3 && symbol1 != STAR) return Optional.of(symbol1);

        return Optional.empty();
    }
}
