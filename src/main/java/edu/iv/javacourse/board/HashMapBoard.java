package edu.iv.javacourse.board;

import edu.iv.javacourse.Coordinates;
import edu.iv.javacourse.piece.Piece;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HashMapBoard extends BaseBoard {
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

    @Override
    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }

}
