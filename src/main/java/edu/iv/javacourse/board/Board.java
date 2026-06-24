package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.Piece;

import java.util.Map;
import java.util.Optional;

public interface Board {
    Optional<Piece> getPiece(Coordinates coordinates);
    void setPiece(Coordinates coordinates, Piece piece);
//    void movePiece(Coordinates coordinatesFrom, Coordinates coordinatesTo);
    void removePiece(Coordinates coordinates);
    Map<Coordinates, Piece> getPiecesMap();
    boolean isSquareEmpty(Coordinates coordinates);
    Board makeCopy();
}
