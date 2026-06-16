package edu.iv.javacourse.board;

import edu.iv.javacourse.board.fen.FenService;
import edu.iv.javacourse.exception.IllegalMoveException;
import edu.iv.javacourse.piece.Piece;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class HashMapBoard implements Board {
    private final Map<Coordinates, Piece> pieces = new HashMap<>();

    @Override
    public Optional<Piece> getPiece(Coordinates coordinates) {
        return Optional.ofNullable(pieces.get(coordinates));
    }

    @Override
    public void setPiece(Coordinates coordinates, Piece piece) {
        if (pieces == null) {
            throw new IllegalArgumentException("Cannot set a null piece. Use removePiece instead.");
        }
        pieces.put(coordinates, piece);
    }

    @Override
    public void removePiece(Coordinates coordinates) {
        pieces.remove(coordinates);
    }

    public void movePiece(Coordinates coordinatesFrom, Coordinates coordinatesTo) {
        Piece piece = getPiece(coordinatesFrom).orElseThrow(() ->
                new IllegalMoveException("Haven't figure on these coordinates"));
        removePiece(coordinatesFrom);
        setPiece(coordinatesTo, piece);
    }

    @Override
    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }
}
