package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.Piece;
import edu.iv.javacourse.piece.PieceType;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class HashMapBoard implements Board {
    private final Map<Coordinates, Piece> pieces = new HashMap<>();

    @Override
    public Optional<Piece> getPiece(Coordinates coordinates) {
        Objects.requireNonNull(coordinates, "Coordinates can't be null");
        return Optional.ofNullable(pieces.get(coordinates));
    }

    @Override
    public void setPiece(Coordinates coordinates, Piece piece) {
        Objects.requireNonNull(coordinates, "Coordinates can't be null");
        Objects.requireNonNull(piece, "Cannot set a null piece.");
        pieces.put(coordinates, piece);
    }

    @Override
    public void removePiece(Coordinates coordinates) {
        pieces.remove(coordinates);
    }

    @Override
    public List<Coordinates> findAllPieces(PieceType pieceType, Color colorToMove) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().getPieceType().getPieceTypeCode()
                        .equals(pieceType.getPieceTypeCode()) &&
                        entry.getValue().getColor() == colorToMove)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Coordinates> findKing(Color colorToMove) {
        List<Coordinates> kings = findAllPieces(PieceType.KING, colorToMove);
        return kings.isEmpty() ? Optional.empty() : Optional.of(kings.get(0));
    }

    @Override
    public Collection<Coordinates> getPiecesCoordinatesByColor(Color color) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().getColor() == color)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }

    @Override
    public Board makeCopy() {
        HashMapBoard copy = new HashMapBoard();
        copy.pieces.putAll(this.pieces);
        return copy;
    }
}
