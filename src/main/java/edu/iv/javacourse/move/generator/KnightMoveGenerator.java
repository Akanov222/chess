package edu.iv.javacourse.move.generator;

import edu.iv.javacourse.board.CoordinatesShift;

import java.util.Set;

public class KnightMoveGenerator extends SingleStepMoveGenerator {
    @Override
    protected Set<CoordinatesShift> getShifts() {
        return KNIGHTS_SHIFTS();
    }

    private static final Set<CoordinatesShift> KNIGHTS_SHIFTS() {
        return Set.of(
                new CoordinatesShift(1, 2), new CoordinatesShift(2, 1),
                new CoordinatesShift(2, -1), new CoordinatesShift(1, -2),
                new CoordinatesShift(-1, -2), new CoordinatesShift(-2, -1),
                new CoordinatesShift(-2, 1), new CoordinatesShift(-1, 2)
        );
    }
}
