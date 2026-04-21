package edu.iv.javacourse;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.BoardFactory;
import edu.iv.javacourse.piece.CoordinatesShift;
import edu.iv.javacourse.piece.Knight;
import edu.iv.javacourse.piece.Pawn;
import edu.iv.javacourse.piece.Piece;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.setupDefaultPiecesPositions();

        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
        renderer.render(board);

        Piece piece = board.getPiece(new Coordinates(File.B, 8));
        Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
//        Knight knight = new Knight(Color.WHITE, new Coordinates(File.G, 7));
//        board.setPiece(new Coordinates(File.G, 7), knight);
//        Set<Coordinates> availableMoveSquares2 = knight.getAvailableMoveSquares(board);

        int a = 123;
    }
}
