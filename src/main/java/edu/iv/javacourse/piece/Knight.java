package edu.iv.javacourse.piece;

import edu.iv.javacourse.board.Color;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }
}
