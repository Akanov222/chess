package edu.iv.javacourse.piece;

import edu.iv.javacourse.board.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract public class Piece {
    private final Color color;

    public String getCode() {
        String code = String.valueOf(this.getClass().getSimpleName().charAt(0));
        return color == Color.WHITE ? code.toUpperCase() : code.toLowerCase();
    }
}

