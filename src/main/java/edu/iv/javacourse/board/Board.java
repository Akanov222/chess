package edu.iv.javacourse.board;

import edu.iv.javacourse.Color;
import edu.iv.javacourse.Coordinates;
import edu.iv.javacourse.File;
import edu.iv.javacourse.exception.IllegalMoveException;
import edu.iv.javacourse.piece.Pawn;
import edu.iv.javacourse.piece.Piece;

import java.util.HashMap;

public class Board {
    HashMap<Coordinates, Piece> pieces = new HashMap<>();

    public void setPiece(Coordinates coordinates, Piece piece) {
        pieces.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates) {
        pieces.remove(coordinates);
    }

    public void movePiece(Coordinates coordinatesFrom, Coordinates coordinatesTo) {
        if (!pieces.containsKey(coordinatesFrom)) {
            throw new IllegalMoveException("Haven't figure on these coordinates");
        }
        Piece piece = getPiece(coordinatesFrom);
        removePiece(coordinatesFrom);
        setPiece(coordinatesTo, piece);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }

    public Piece getPiece(Coordinates coordinates) {
        return pieces.get(coordinates);
    }

    public static boolean isSquareDark(Coordinates coordinates) {
        return ((coordinates.file.ordinal() + 1 + coordinates.rank) % 2 == 0);
    }
}
