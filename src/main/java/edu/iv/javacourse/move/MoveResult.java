package edu.iv.javacourse.move;

import edu.iv.javacourse.piece.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoveResult {
    private final boolean success;
    private final Piece capturedPiece;

    public static MoveResult success(Piece captured) {
        return new MoveResult(true, captured);
    }

    public static MoveResult error() {
        return new MoveResult(false, null);
    }
}
