package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.Piece;

public interface Board {
    Piece getPiece(Coordinates coordinates);
    void setPiece(Coordinates coordinates, Piece piece);
    void movePiece(Coordinates coordinatesFrom, Coordinates coordinatesTo);
    void removePiece(Coordinates coordinates);
    boolean isSquareEmpty(Coordinates coordinates);
}
