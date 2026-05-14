package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardHtmlRenderer {
    private final Map<Class<? extends Piece>, String> pieceSprites = Map.of(
            Pawn.class, "♙",
            Knight.class, "♘",
            Bishop.class, "♗",
            Rook.class, "♖",
            Queen.class, "♕",
            King.class, "♔"
    );

    public List<List<SquareView>> getBoardView(Board board) {
        List<List<SquareView>> rows = new ArrayList<>();
        for (int rank = 8; rank >= 1; rank--) {
            List<SquareView> row = new ArrayList<>();
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                Piece piece = board.getPiece(coordinates);
                row.add(new SquareView(coordinates, piece, coordinates.getColor(),
                        getUnicodeSprite(piece)));
            }
            rows.add(row);
        }
        return rows;
    }

    private String getUnicodeSprite(Piece piece) {
        if (piece == null) {
            return "";
        }
        return pieceSprites.getOrDefault(piece.getClass(), "?");
    }
}
