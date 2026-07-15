package edu.iv.javacourse.view;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Color;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.board.File;
import edu.iv.javacourse.piece.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BoardHtmlRenderer implements BoardRenderer<List<List<SquareView>>> {
    private final Map<Class<? extends Piece>, String> pieceSprites = Map.of(
            Pawn.class, "♙",
            Knight.class, "♘",
            Bishop.class, "♗",
            Rook.class, "♖",
            Queen.class, "♕",
            King.class, "♔"
    );

    @Override
    public List<List<SquareView>> render(Board board) {
        return getBoardView(board);
    }

    public List<List<SquareView>> getBoardView(Board board) {
        List<List<SquareView>> rows = new ArrayList<>();
        for (int rank = 8; rank >= 1; rank--) {
            List<SquareView> row = new ArrayList<>();
            for (File file : File.CASHED_VALUES) {
                Coordinates coordinates = new Coordinates(file, rank);
                Optional<Piece> optionalPiece = board.getPiece(coordinates);
                Piece piece = optionalPiece.orElse(null);
                String sprite = getUnicodeSprite(optionalPiece);
                boolean dark = coordinates.getColorOfChessBoardSquare() == Color.BLACK;
                row.add(new SquareView(
                        coordinates, piece,
                        dark,
                        sprite));
            }
            rows.add(row);
        }
        return rows;
    }

    private String getUnicodeSprite(Optional<Piece> optionalPiece) {
        return optionalPiece.map(piece -> pieceSprites.getOrDefault(piece.getClass(), "?"))
                .orElse("");
    }
}
