package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.*;

public abstract class BaseBoard implements Board {

    private final String turn = "w";
    private final String castling = "KQkq";
    private final String enPassant = "-";
    private int halfMove = 0;
    private int fullMove = 1;

    @Override
    public boolean isSquareDark(Coordinates coordinates) {
        return ((coordinates.file.ordinal() + 1 + coordinates.rank) % 2 == 0);
    }

    @Override
    public String toFen(String turn, String castling,
                        String enPassant, int halfMove, int fullMove) {
        StringBuilder fen = new StringBuilder();

        for (int rank = 8; rank >= 1 ; rank--) {
            int emptySquares = 0;
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                if (isSquareEmpty(coordinates)) { emptySquares++; }
                else {
                    if (emptySquares > 0) {
                        fen.append(emptySquares);
                        emptySquares = 0;
                    }
                    fen.append(getPieceLetter(getPiece(coordinates)));
                }
            }
            if (emptySquares > 0) { fen.append(emptySquares); }
            if (rank > 1) { fen.append("/"); }
        }

        fen.append(" ").append(turn == "w" ? "w" : "b");
        fen.append(" ").append(castling);
        fen.append(" ").append(enPassant);
        fen.append(" ").append(halfMove);
        fen.append(" ").append(fullMove);

        return fen.toString();
    }

    private String getPieceLetter(Piece piece) {

        // В FEN: белые — заглавные (P, N, B...), черные — строчные (p, n, b...)
        char letter;
        if (piece instanceof Pawn) letter = 'P';
        else if (piece instanceof Rook) letter = 'R';
        else if (piece instanceof Knight) letter = 'N';
        else if (piece instanceof Bishop) letter = 'B';
        else if (piece instanceof Queen) letter = 'Q';
        else if (piece instanceof King) letter = 'K';
        else letter = '?';

        return piece.color == Color.WHITE ?
                String.valueOf(letter) :
                String.valueOf(letter).toLowerCase();
    }
}
