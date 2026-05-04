package edu.iv.javacourse.event;

import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.piece.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoveEvent {
    public Piece piece;
    public Coordinates fromCoordinates;
    public Coordinates toCoordinates;
    public Piece capturedPiece;
}
