package edu.iv.javacourse.move;

import edu.iv.javacourse.piece.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoveResult {
    private final boolean success;
    private final Piece capturedPiece;
    private final String message;

    public static MoveResult success(Piece capturedPiece) {
        return new MoveResult(true, capturedPiece, null);
    }

    public static MoveResult error(String message) {
        return new MoveResult(false, null, message);
    }
}
