package edu.iv.javacourse.piece;

import edu.iv.javacourse.board.Color;
import lombok.Getter;

import java.util.Objects;

@Getter
abstract public class Piece {
    private final Color color;

    protected Piece(Color color) {
        this.color = Objects.requireNonNull(color, "Color can't be null");
    }

    public abstract PieceType getPieceType();

    public String getCode() {
        String code = getPieceType().getPieceTypeCode();
        return color == Color.WHITE ? code.toUpperCase() : code.toLowerCase();
    }
}
