package edu.iv.javacourse.move;

import edu.iv.javacourse.piece.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoveResult {
    private final boolean success;
    private final Piece capturedPiece;
}
