package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.Piece;
import edu.iv.javacourse.piece.PieceType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Board {
    Optional<Piece> getPiece(Coordinates coordinates);
    void setPiece(Coordinates coordinates, Piece piece);
//    void movePiece(Coordinates coordinatesFrom, Coordinates coordinatesTo);
    void removePiece(Coordinates coordinates);
    List<Coordinates> findAllPieces(PieceType pieceType, Color colorToMove);
    Optional<Coordinates> findKing(Color colorToMove);
    Collection<Coordinates> getPiecesCoordinatesByColor(Color color);
    boolean isSquareEmpty(Coordinates coordinates);
    Board makeCopy();
}
