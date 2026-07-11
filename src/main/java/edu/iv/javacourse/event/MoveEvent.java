package edu.iv.javacourse.event;

import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.piece.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MoveEvent {
    private Piece piece;
    private Coordinates fromCoordinates;
    private Coordinates toCoordinates;
    private Piece capturedPiece;

}
