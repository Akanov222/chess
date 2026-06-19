package edu.iv.javacourse.move;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.board.fen.GameState;

import java.util.Set;

public interface PieceMoveGenerator {
    Set<Coordinates> getAvailableMoveSquares(Coordinates coordinatesFrom, GameState gameState)
            throws InstantiationException, IllegalAccessException;
}
