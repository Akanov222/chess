package edu.iv.javacourse.move.generator;

import edu.iv.javacourse.board.CoordinatesShift;

import java.util.Set;

public class BishopMoveGenerator extends LinearMoveGenerator {

    @Override
    protected Set<CoordinatesShift> getMovementVectors() {
        return BISHOP_SHIFTS();
    }

    private static final Set<CoordinatesShift> BISHOP_SHIFTS() {
        return Set.of(
                new CoordinatesShift(1, 1), new CoordinatesShift(1, -1),
                new CoordinatesShift(-1, -1), new CoordinatesShift(-1, 1)
        );
    }
}
