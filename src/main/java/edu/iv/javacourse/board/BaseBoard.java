package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseBoard implements Board {

    private String turn = "w";
    private String castling = "KQkq";
    private String enPassant = "-";
    private int halfMove = 0;
    private int fullMove = 1;

    @Override
    public String toFen(String turn, String castling,
                        String enPassant, int halfMove, int fullMove) {

        StringBuilder fen = new StringBuilder();

        for (int rank = 8; rank >= 1; rank--) {
            int emptySquares = 0;
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                if (isSquareEmpty(coordinates)) {
                    emptySquares++;
                } else {
                    if (emptySquares > 0) {
                        fen.append(emptySquares);
                        emptySquares = 0;
                    }
                    Piece piece = getPiece(coordinates);
                    fen.append(piece.getCode(piece.color));
                }
            }
            if (emptySquares > 0) {
                fen.append(emptySquares);
            }
            if (rank > 1) {
                fen.append("/");
            }
        }

        fen.append(" ").append(turn.equals("w") ? "w" : "b");
        fen.append(" ").append(castling);
        fen.append(" ").append(enPassant);
        fen.append(" ").append(halfMove);
        fen.append(" ").append(fullMove);
        log.trace("Board to FEN is finished");
        return fen.toString();
    }
}
