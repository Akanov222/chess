package edu.iv.javacourse.piece;

import edu.iv.javacourse.Color;
import edu.iv.javacourse.Coordinates;

import java.util.Set;

public class Pawn extends Piece {

    public Pawn(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return Set.of();
    }
}
