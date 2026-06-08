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
    public Piece getPiece(Coordinates coordinates) {
        return pieces.get(coordinates);
    }

    @Override
    public void setPiece(Coordinates coordinates, Piece piece) {
        pieces.put(coordinates, piece);
    }

    @Override
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

    @Override
    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }

}
