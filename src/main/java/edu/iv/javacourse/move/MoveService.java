package edu.iv.javacourse.move;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Color;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.piece.Piece;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class MoveService {
    private final MoveGeneratorFactory factory = new MoveGeneratorFactory();

    public MoveResult movePiece(Board board, Coordinates coordinatesFrom, Coordinates coordinatesTo, Color colorToMove) {
        Piece piece = board.getPiece(coordinatesFrom);
        if (piece == null) {
            log.info("Movement impossible: no piece at {}", coordinatesFrom);
            new MoveResult(false, null);
        }

        if (piece.color != colorToMove) {
            log.info("Movement impossible: it's {}'s turn, but {} piece selected", colorToMove, piece.color);
            new MoveResult(false, null);
        }

        PieceMoveGenerator generator = factory.getGenerator(piece.getClass());

        Set<Coordinates> availableMove = null;
        try {
            availableMove = generator.getAvailableMoveSquares(coordinatesFrom, board);
        } catch (Exception e) {
            throw new RuntimeException("Generator error", e);
        }

        if (!availableMove.contains(coordinatesTo)) {
            log.debug("Movement impossible: {} cannot move from {} to {}",
                    piece.getClass().getSimpleName(), coordinatesFrom, coordinatesTo);
            return new MoveResult(false, null);
        }

        Piece capturedPiece = board.getPiece(coordinatesTo);

        board.removePiece(coordinatesFrom);
        board.setPiece(coordinatesTo, piece);

        log.info("Successfully moved {} from {} to {}. Captured {}",
                piece.getClass().getSimpleName(), coordinatesFrom, coordinatesTo,
                capturedPiece != null ? capturedPiece.getClass().getSimpleName() : "none.");
        return new MoveResult(true, capturedPiece);
    }
}
