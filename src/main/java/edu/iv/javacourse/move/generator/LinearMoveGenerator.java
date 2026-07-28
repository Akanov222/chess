package edu.iv.javacourse.move.generator;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.board.CoordinatesShift;
import edu.iv.javacourse.game.GameState;
import edu.iv.javacourse.piece.Piece;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class LinearMoveGenerator implements PieceMoveGenerator {

    protected abstract Set<CoordinatesShift> getMovementVectors();

    public Set<Coordinates> getAvailableMoveSquares(Coordinates coordinatesFrom, GameState gameState)
        /*throws InstantiationException, IllegalAccessException*/ {
        Board board = gameState.getBoard();
        Piece piece = board.getPiece(coordinatesFrom).orElseThrow(
                () -> new IllegalStateException("On coordinates " + coordinatesFrom + " should be figure"));

        Set<Coordinates> result = new HashSet<>();
        for (CoordinatesShift vectorShift : getMovementVectors()) {
            Optional<Coordinates> currentCoordinate = coordinatesFrom.shift(vectorShift);

            while (currentCoordinate.isPresent()) {
                Coordinates targetCoordinates = currentCoordinate.get();
                if (board.isSquareEmpty(targetCoordinates)) {
                    result.add(targetCoordinates);
                } else if (board.getPiece(targetCoordinates).get().getColor() != piece.getColor()) {
                    result.add(targetCoordinates);
                } else {
                    break;
                }
                currentCoordinate = targetCoordinates.shift(vectorShift);
            }
        }
        return result;
    }
}
