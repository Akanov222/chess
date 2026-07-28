package edu.iv.javacourse.move.generator;

import edu.iv.javacourse.board.CoordinatesShift;

import java.util.Set;

public class RookMoveGenerator extends LinearMoveGenerator {

    @Override
    protected Set<CoordinatesShift> getMovementVectors() {
        return ROOK_SHIFTS();
    }

    private static final Set<CoordinatesShift> ROOK_SHIFTS() {
        return Set.of(
                new CoordinatesShift(0, 1), new CoordinatesShift(1, 0),
                new CoordinatesShift(0, -1), new CoordinatesShift(-1, 0)
        );
    }
}
