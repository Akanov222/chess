package edu.iv.javacourse;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.piece.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class BoardConsoleRenderer {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";
    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";
    public static final String IDEOGRAPHIC_SPACE = "\u3000";
    public static final String NORMAL_SPACE = "\u2001";
    public final Map<Class<? extends Piece>, String> pieceSprites = Map.of(
            Pawn.class, "♙",
            Knight.class, "♘",
            Bishop.class, "♗",
            Rook.class, "♖",
            Queen.class, "♕",
            King.class, "♔"
    );

    public void render(Board board) {
        log.debug("Starting board rendering");
        for (int rank = 8; rank >= 1; rank--) {
            StringBuilder line = new StringBuilder();
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);

                line.append(board.isSquareEmpty(coordinates)? getSpriteForEmptySquare(new Coordinates(file, rank)) :
                        getPieceSprite(board.getPiece(coordinates), coordinates));
            }

            line.append(ANSI_RESET);
            System.out.println(line);
            log.debug("Rank {} rendered", rank);
        }
        log.debug("Finished board rendering");
    }

    private StringBuilder colorizeSprite(StringBuilder sprite, Color pieceColor, boolean isSquareDark, Coordinates coordinates) {
        // format = background color + font color + text
        log.debug("Colorizing sprite at {}: color={},isDark={}", coordinates, pieceColor, isSquareDark);

        StringBuilder result = new StringBuilder();
        result.append(isSquareDark ? ANSI_BLACK_SQUARE_BACKGROUND : ANSI_WHITE_SQUARE_BACKGROUND);
        result.append(pieceColor == Color.WHITE ? ANSI_WHITE_PIECE_COLOR : ANSI_BLACK_PIECE_COLOR);
        result.append(sprite);

        return result;
    }

    private StringBuilder getSpriteForEmptySquare(Coordinates coordinates) {
        return colorizeSprite(new StringBuilder(String.format("%3s", IDEOGRAPHIC_SPACE)),
                Color.WHITE, Board.isSquareDark(coordinates), coordinates);
    }

    private StringBuilder getPieceSprite(Piece piece, Coordinates coordinates) {
        return colorizeSprite(
                new StringBuilder(NORMAL_SPACE)
                        .append(selectUnicodeSpriteForPiece(piece))
                        .append(NORMAL_SPACE),
                piece.color, Board.isSquareDark(coordinates), coordinates);
    }

    private StringBuilder selectUnicodeSpriteForPiece(Piece piece) {
        return new StringBuilder(pieceSprites.get(piece.getClass()));
    }
}



    /*
    public static final StringBuilder ANSI_RESET = new StringBuilder("\u001B[0m");
    public static final StringBuilder ANSI_WHITE_PIECE_COLOR = new StringBuilder("\u001B[97m");
    public static final StringBuilder ANSI_BLACK_PIECE_COLOR = new StringBuilder("\u001B[30m");
    public static final StringBuilder ANSI_WHITE_SQUARE_BACKGROUND = new StringBuilder("\u001B[47m");
    public static final StringBuilder ANSI_BLACK_SQUARE_BACKGROUND = new StringBuilder("\u001B[0;100m");
    public static final StringBuilder IDEOGRAPHIC_SPACE = new StringBuilder("\u3000");
    public static final StringBuilder NORMAL_SPACE = new StringBuilder("\u2001");
    public final Map<Class<? extends Piece>, StringBuilder> pieceSprites = Map.of(
            Pawn.class, new StringBuilder("♙"),
            Knight.class, new StringBuilder("♘"),
            Bishop.class, new StringBuilder("♗"),
            Rook.class, new StringBuilder("♖"),
            Queen.class, new StringBuilder("♕"),
            King.class, new StringBuilder("♔")
    );

    public void render(Board board) {
        for (int rank = 8; rank >= 1; rank--) {
            StringBuilder line = new StringBuilder();
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                if (board.isSquareEmpty(coordinates)) {
                    line.append(getSpriteForEmptySquare(board, new Coordinates(file, rank)));
                }
                else {
                    line.append(getPieceSprite(board.getPiece(coordinates), coordinates));
                }
            }
            line.append(ANSI_RESET);
            System.out.println(line);
        }
    }

    private StringBuilder colorizeSprite(StringBuilder sprite, Color pieceColor, boolean isSquareDark) {
        // format = background color + font color + text
        StringBuilder result = sprite;

        if (pieceColor == Color.WHITE) {
            result.append(ANSI_WHITE_PIECE_COLOR);
        } else {
            result.append(ANSI_BLACK_PIECE_COLOR);
        }

        if (isSquareDark) {
            result.append(ANSI_BLACK_SQUARE_BACKGROUND);
        } else {
            result.append(ANSI_WHITE_SQUARE_BACKGROUND);
        }

        return result;
    }

    private StringBuilder getSpriteForEmptySquare(Board board, Coordinates coordinates) {
        return colorizeSprite(new StringBuilder(String.format("%3s", IDEOGRAPHIC_SPACE)),
                Color.WHITE, board.isSquareDark(coordinates));
    }

    private StringBuilder getPieceSprite(Piece piece, Coordinates coordinates) {
        return colorizeSprite(NORMAL_SPACE.append(selectUnicodeSpriteForPiece(piece)).append(NORMAL_SPACE),
                piece.color, Board.isSquareDark(coordinates));
    }

    private StringBuilder selectUnicodeSpriteForPiece(Piece piece) {
        return pieceSprites.get(piece.getClass());
    }
}
    */


