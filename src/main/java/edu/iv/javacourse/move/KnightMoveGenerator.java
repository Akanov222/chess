package edu.iv.javacourse.move;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Color;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.board.CoordinatesShift;
import edu.iv.javacourse.piece.Knight;
import edu.iv.javacourse.piece.Piece;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class KnightMoveGenerator implements PieceMoveGenerator{
    @Override
    public Set<Coordinates> getAvailableMoveSquares(Coordinates coordinatesFrom, Board board) {
        Set<Coordinates> result = new HashSet<>();
        Piece knight = board.getPiece(coordinatesFrom);
        for (CoordinatesShift shift : getKnightsShift()) {
            coordinatesFrom.shift(shift).ifPresent(coordinatesTo -> {
                if (board.isSquareEmpty(coordinatesTo) ||
                        (board.getPiece(coordinatesTo).color != knight.color)) {
                    result.add(coordinatesTo);
                }
            });
        }

        return result;
    }

    private Set<CoordinatesShift> getKnightsShift() {
        return Set.of(
                new CoordinatesShift(1, 2), new CoordinatesShift(2, 1),
                new CoordinatesShift(2, -1), new CoordinatesShift(1, -2),
                new CoordinatesShift(-1, -2), new CoordinatesShift(-2, -1),
                new CoordinatesShift(-2, 1), new CoordinatesShift(-1, 2)
        );
    }
}
