package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.Piece;

import java.util.Optional;

public interface Board {
    void setPiece(Coordinates coordinates, Piece piece);
    void removePiece(Coordinates coordinates);
    boolean isSquareEmpty(Coordinates coordinates);
    Piece getPiece(Coordinates coordinates);
    boolean isSquareDark(Coordinates coordinates);
    String toFen();
}
