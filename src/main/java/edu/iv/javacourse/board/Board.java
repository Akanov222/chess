package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.Piece;

public interface Board {
    void setPiece(Coordinates coordinates, Piece piece);
    void removePiece(Coordinates coordinates);
    void movePiece(Coordinates coordinatesFrom, Coordinates coordinatesTo);
    boolean isSquareEmpty(Coordinates coordinates);
    Piece getPiece(Coordinates coordinates);
    boolean isSquareDark(Coordinates coordinates);
    String toFen();
}
