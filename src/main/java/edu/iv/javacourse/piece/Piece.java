package edu.iv.javacourse.piece;

import edu.iv.javacourse.board.Color;
import lombok.AllArgsConstructor;

//@Getter
@AllArgsConstructor
abstract public class Piece {
    public final Color color;

    public String getCode(Color color) {
        String code = String.valueOf(this.getClass().getSimpleName().charAt(0));
        return color == Color.WHITE ? code : code.toLowerCase();
    }
}

