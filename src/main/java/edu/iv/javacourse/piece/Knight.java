package edu.iv.javacourse.piece;

import edu.iv.javacourse.Color;
import edu.iv.javacourse.Coordinates;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }
/*    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return new HashSet<>(Arrays.asList(
                new CoordinatesShift(1, 2),
                new CoordinatesShift(2, 1),

                new CoordinatesShift(2, -1),
                new CoordinatesShift(1, -2),

                new CoordinatesShift(-1, -2),
                new CoordinatesShift(-2, -1),

                new CoordinatesShift(-2, 1),
                new CoordinatesShift(-1, 2)
        ));
    }*/
}
