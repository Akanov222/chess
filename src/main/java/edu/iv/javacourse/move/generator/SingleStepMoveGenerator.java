package edu.iv.javacourse.move.generator;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.board.CoordinatesShift;
import edu.iv.javacourse.game.GameState;
import edu.iv.javacourse.piece.Piece;

import java.util.HashSet;
import java.util.Set;

public abstract class SingleStepMoveGenerator implements PieceMoveGenerator {

    protected abstract Set<CoordinatesShift> getShifts();

    public Set<Coordinates> getAvailableMoveSquares(Coordinates coordinatesFrom, GameState gameState)
            /*throws InstantiationException, IllegalAccessException*/ {
        Board board = gameState.getBoard();
        Piece piece = board.getPiece(coordinatesFrom).orElseThrow(
                () -> new IllegalStateException("On coordinates " + coordinatesFrom + " should be figure"));

        Set<Coordinates> result = new HashSet<>();
        for (CoordinatesShift shift : getShifts()) {
            coordinatesFrom.shift(shift).ifPresent(coordinatesTo -> {
                if (board.isSquareEmpty(coordinatesTo)
                        || (board.getPiece(coordinatesTo).get().getColor() != piece.getColor())) {
                    result.add(coordinatesTo);
                }
            });
        }
        return result;
    }
}
