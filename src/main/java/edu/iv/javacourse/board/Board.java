package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.Piece;

import java.util.Optional;

public interface Board {
    Optional<Piece> getPiece(Coordinates coordinates);
    void setPiece(Coordinates coordinates, Piece piece);
    void movePiece(Coordinates coordinatesFrom, Coordinates coordinatesTo);
    void removePiece(Coordinates coordinates);
    boolean isSquareEmpty(Coordinates coordinates);
}
