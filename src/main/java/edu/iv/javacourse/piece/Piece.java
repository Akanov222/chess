package edu.iv.javacourse.piece;

import edu.iv.javacourse.Color;
import edu.iv.javacourse.Coordinates;
import lombok.AllArgsConstructor;

@AllArgsConstructor
abstract public class Piece {
    public final Color color;
    public Coordinates coordinates;
}
