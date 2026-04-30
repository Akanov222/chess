package edu.iv.javacourse.board;

import edu.iv.javacourse.Color;
import edu.iv.javacourse.Coordinates;
import edu.iv.javacourse.File;
import edu.iv.javacourse.piece.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class BoardConsoleRenderer implements BordRenderer{

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

    @Override
    public void render(Board board) {
        System.out.print("\033[H\033[2J");
        renderToRows(board).forEach(System.out::println);
        log.debug("Finished board rendering");
    }

    List<String> renderToRows(Board board) {
        log.debug("Generating board rows");
        List<String> rows = new ArrayList<>();
        for (int rank = 8; rank >= 1; rank--) {
            StringBuilder line = new StringBuilder();
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);

                line.append(board.isSquareEmpty(coordinates)?
                        getSpriteForEmptySquare(coordinates, board) :
                        getPieceSprite(board.getPiece(coordinates), coordinates, board));
            }

            line.append(ANSI_RESET);
            rows.add(line.toString());
        }
        return rows;
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

    private StringBuilder getSpriteForEmptySquare(Coordinates coordinates, Board board) {
        return colorizeSprite(new StringBuilder(String.format("%3s", IDEOGRAPHIC_SPACE)),
                Color.WHITE, board.isSquareDark(coordinates), coordinates);
    }

    private StringBuilder getPieceSprite(Piece piece, Coordinates coordinates, Board board) {
        return colorizeSprite(
                new StringBuilder(NORMAL_SPACE)
                        .append(selectUnicodeSpriteForPiece(piece))
                        .append(NORMAL_SPACE),
                piece.color, board.isSquareDark(coordinates), coordinates);
    }

    private StringBuilder selectUnicodeSpriteForPiece(Piece piece) {
        return new StringBuilder(pieceSprites.get(piece.getClass()));
    }
}
