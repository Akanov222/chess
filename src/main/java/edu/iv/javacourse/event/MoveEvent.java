package edu.iv.javacourse.event;

import edu.iv.javacourse.Coordinates;
import edu.iv.javacourse.piece.Piece;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MoveEvent {
    public Piece piece;
    public Coordinates fromCoordinates;
    public Coordinates toCoordinates;

}
