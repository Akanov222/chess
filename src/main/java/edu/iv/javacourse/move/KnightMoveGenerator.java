package edu.iv.javacourse.move;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.board.CoordinatesShift;
import edu.iv.javacourse.board.fen.GameState;
import edu.iv.javacourse.piece.Piece;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class KnightMoveGenerator implements PieceMoveGenerator{
    @Override
    public Set<Coordinates> getAvailableMoveSquares(Coordinates coordinatesFrom, GameState  gameState) {
        Set<Coordinates> result = new HashSet<>();
        Board board = gameState.getBoard();
        Piece knight = board.getPiece(coordinatesFrom).orElseThrow(
                () -> new IllegalStateException("На клетке " + coordinatesFrom + " нет фигуры"));
        for (CoordinatesShift shift : KNIGHTS_SHIFT()) {
            coordinatesFrom.shift(shift).ifPresent(coordinatesTo -> {
                if (board.isSquareEmpty(coordinatesTo)
                        || (board.getPiece(coordinatesTo).get().getColor() != knight.getColor())) {
                    result.add(coordinatesTo);
                }
            });
        }

        return result;
    }

    private static Set<CoordinatesShift> KNIGHTS_SHIFT() {
        return Set.of(
                new CoordinatesShift(1, 2), new CoordinatesShift(2, 1),
                new CoordinatesShift(2, -1), new CoordinatesShift(1, -2),
                new CoordinatesShift(-1, -2), new CoordinatesShift(-2, -1),
                new CoordinatesShift(-2, 1), new CoordinatesShift(-1, 2)
        );
    }
}
