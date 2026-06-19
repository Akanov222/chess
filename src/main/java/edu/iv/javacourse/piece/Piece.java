package edu.iv.javacourse.piece;

import edu.iv.javacourse.board.Color;
import lombok.Getter;

@Getter
abstract public class Piece {
    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract PieceType getPieceType();

    public String getCode() {
        String code = getPieceType().getPieceTypeCode();
        return color == Color.WHITE ? code.toUpperCase() : code.toLowerCase();
    }
}
