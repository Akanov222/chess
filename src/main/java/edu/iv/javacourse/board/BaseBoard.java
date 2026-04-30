package edu.iv.javacourse.board;

import edu.iv.javacourse.Color;
import edu.iv.javacourse.Coordinates;
import edu.iv.javacourse.File;
import edu.iv.javacourse.exception.IllegalMoveException;
import edu.iv.javacourse.piece.*;

public abstract class BaseBoard implements Board {

    @Override
    public void movePiece(Coordinates coordinatesFrom, Coordinates coordinatesTo) {
        Piece piece = getPiece(coordinatesFrom);
        if (piece == null) {
            throw new IllegalMoveException("Haven't figure on these coordinates");
        }
        removePiece(coordinatesFrom);
        setPiece(coordinatesTo, piece);
    }

    @Override
    public boolean isSquareDark(Coordinates coordinates) {
        return ((coordinates.file.ordinal() + 1 + coordinates.rank) % 2 == 0);
    }

    @Override
    public String toFen() {
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
                String.valueOf(letter).toUpperCase() :
                String.valueOf(letter).toLowerCase();
    }
}
