package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.Piece;

public interface Board {
    void setPiece(Coordinates coordinates, Piece piece);
    void removePiece(Coordinates coordinates);
    boolean isSquareEmpty(Coordinates coordinates);
    Piece getPiece(Coordinates coordinates);
    String toFen();
}
