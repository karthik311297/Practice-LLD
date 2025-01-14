package lld.chess;

public class ChessDemo {
    /*
        Board

        Player

        Cell
            x, y
            Piece

        abstract Piece
            color
            currentPosition
            abstract canMove(x, y)
            move(x, y) - 1. kill opponent piece if any, and update board cell at x, y from null.
                         2. mark current position cell in board with null piece.
                         3. update piece current position to x, y

        Rook extend Piece
            canMove(x, y) - horizontal, vertical (both dir, total possible 4 moves)

        Knight extend Piece
            canMove(x, y) - L shape (column, row wise, total possible 8 moves)

        Bishop extend Piece
            canMove(x, y) - diagonal(both dir, total possible 4 moves)

        Queen extend Piece
            canMove(x, y) - diagonal, horizontal, vertical any number of units (both dir, total possible 8 moves)

        Pawn
            canMove() - 1. handle case when opponent piece in right/left diagonal (front dir) (total possible 2 moves).
                        2. handle first move 2 or 1 cells front. (total possible 2 moves)
                        3. allow only one cell front after first move (total possible 1 move)

        King
            canMove(x, y) - Diagonal, horizontal, vertical only one unit (both dir, total possible 8 moves)


        PieceSafetyChecker
            Board
            isPieceSafe(Piece, x, y) - check if the piece is safe from opponent if it moves to x, y

        CheckMateChecker
            Board
            isKingSafe(King) - check safety for all possible 8 moves of a king

     */
}
