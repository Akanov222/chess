package edu.iv.javacourse.board;

import edu.iv.javacourse.Color;
import edu.iv.javacourse.Coordinates;
import edu.iv.javacourse.File;
import edu.iv.javacourse.event.GameEventListener;
import edu.iv.javacourse.event.MoveEvent;
import edu.iv.javacourse.exception.IllegalMoveException;
import edu.iv.javacourse.piece.Knight;
import edu.iv.javacourse.piece.Pawn;
import edu.iv.javacourse.piece.Piece;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Getter
@Setter
public class Board {

    private final HashMap<Coordinates, Piece> pieces = new HashMap<>();

    public void setPiece(Coordinates coordinates, Piece piece) {
        pieces.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates) {
        pieces.remove(coordinates);
    }

    public void movePiece(Coordinates coordinatesFrom, Coordinates coordinatesTo) {
        if (!pieces.containsKey(coordinatesFrom)) {
            throw new IllegalMoveException("Haven't figure on these coordinates");
        }
        Piece piece = getPiece(coordinatesFrom);
        removePiece(coordinatesFrom);
        setPiece(coordinatesTo, piece);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }

    public Piece getPiece(Coordinates coordinates) {
        return pieces.get(coordinates);
    }

    public static boolean isSquareDark(Coordinates coordinates) {
        return ((coordinates.file.ordinal() + 1 + coordinates.rank) % 2 == 0);
    }

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
        // Нужно специальное условие для Коня (Knight -> N), так как King тоже на K
        char letter = piece.getClass().getSimpleName().charAt(0);
        if (piece instanceof Knight) {
            letter = 'N';
        }
        return piece.color == Color.WHITE ?
                String.valueOf(letter).toUpperCase() :
                String.valueOf(letter).toLowerCase();
    }
}
