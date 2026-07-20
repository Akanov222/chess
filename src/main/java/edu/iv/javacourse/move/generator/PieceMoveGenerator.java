package edu.iv.javacourse.move.generator;

import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.game.GameState;

import java.util.Set;

public interface PieceMoveGenerator {
    Set<Coordinates> getAvailableMoveSquares(Coordinates coordinatesFrom, GameState gameState)
            throws InstantiationException, IllegalAccessException;

}
