package edu.iv.javacourse.board;

import edu.iv.javacourse.Color;
import edu.iv.javacourse.Coordinates;
import edu.iv.javacourse.File;
import edu.iv.javacourse.event.GameEventListener;
import edu.iv.javacourse.event.MoveEvent;
import edu.iv.javacourse.exception.IllegalMoveException;
import edu.iv.javacourse.piece.Pawn;
import edu.iv.javacourse.piece.Piece;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class Board {
    HashMap<Coordinates, Piece> pieces = new HashMap<>();
    private final List<GameEventListener> listeners = new ArrayList<>();

    public void setPiece(Coordinates coordinates, Piece piece) {
        pieces.put(coordinates, piece);
    }

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

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }

    public Piece getPiece(Coordinates coordinates) {
        return pieces.get(coordinates);
    }

    public static boolean isSquareDark(Coordinates coordinates) {
        return ((coordinates.file.ordinal() + 1 + coordinates.rank) % 2 == 0);
    }

    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public void makeMove(Coordinates from, Coordinates to) {
        Piece piece = pieces.get(from);

        // TRACE пишем прямо тут
        log.trace("Internal: calculating move for piece at {}", from);

        // ... логика хода ...

        // Оповещаем мир о важном событии
        MoveEvent event = new MoveEvent(piece, from, to);
        listeners.forEach(l -> l.onMove(event));
    }
}
