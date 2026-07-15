package edu.iv.javacourse.piece;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PieceType {
    KING("K"),
    QUEEN("Q"),
    ROOK("R"),
    KNIGHT("N"),
    BISHOP("B"),
    PAWN("P");

    public final String pieceTypeCode;
}
