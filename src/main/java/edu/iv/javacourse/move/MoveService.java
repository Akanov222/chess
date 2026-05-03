package edu.iv.javacourse.move;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.piece.Piece;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Set;

@Slf4j
public class MoveService {
    private final MoveGeneratorFactory factory = new MoveGeneratorFactory();

    public MoveResult movePiece(Board board, Coordinates coordinatesFrom, Coordinates coordinatesTo) {
        Piece piece = board.getPiece(coordinatesFrom);
        if (piece == null) {
            new MoveResult(false, null);
        }

        PieceMoveGenerator generator = factory.getGenerator(piece.getClass());

        Set<Coordinates> availableMove = null;
        try {
            availableMove = generator.getAvailableMoveSquares(coordinatesFrom, board);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        if (!availableMove.contains(coordinatesTo)) {
            log.debug("Movement impossible: {} color {} cannot move from {} to {}",
                    piece.getClass().getSimpleName(), piece.color, coordinatesFrom, coordinatesTo);
            return new MoveResult(false, null);
        }

        Piece capturedPiece = board.getPiece(coordinatesTo);

        board.removePiece(coordinatesFrom);
        board.setPiece(coordinatesTo, piece);

        log.info("Successfully moved {} color {} from {} to {}. Captured {}",
                piece.getClass().getSimpleName(), piece.color, coordinatesFrom, coordinatesTo,
                capturedPiece != null ? capturedPiece.getClass().getSimpleName() : "none.");
        return new MoveResult(true, capturedPiece);
    }
}
