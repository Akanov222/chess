package edu.iv.javacourse.piece;

import edu.iv.javacourse.board.Color;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public String getCode() {
        String code = String.valueOf(this.getClass().getSimpleName().charAt(1));
        return this.getColor() == Color.WHITE ? code.toUpperCase() : code.toLowerCase();
    }
}


