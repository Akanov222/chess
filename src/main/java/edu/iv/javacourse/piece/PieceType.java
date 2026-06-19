package edu.iv.javacourse.piece;

public enum PieceType {
    KING("K"),
    QUEEN("Q"),
    ROOK("R"),
    KNIGHT("N"),
    BISHOP("B"),
    PAWN("P");

    public final String pieceTypeCode;

    PieceType(String pieceTypeCode) {
        this.pieceTypeCode = pieceTypeCode;
    }

    public String getPieceTypeCode() {
        return pieceTypeCode;
    }
}
