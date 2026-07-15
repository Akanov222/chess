package edu.iv.javacourse.piece;

import edu.iv.javacourse.board.Color;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }
}
