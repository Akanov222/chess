package edu.iv.javacourse.move;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Color;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.board.fen.GameState;
import edu.iv.javacourse.piece.Piece;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Set;

@Slf4j
public class MoveService {
    private final MoveGeneratorFactory factory = new MoveGeneratorFactory();

    public MoveResult movePiece(GameState gameState, Coordinates coordinatesFrom, Coordinates coordinatesTo) {
        Board board = gameState.getBoard();
        Color colorToMove = "w".equals(gameState.getTurn()) ? Color.WHITE : Color.BLACK;
        Optional<Piece> piece = board.getPiece(coordinatesFrom);
        if (piece.isEmpty()) {
            log.info("Movement impossible: no piece at {}", coordinatesFrom);
            return MoveResult.error("Сначала выберите фигуру для хода");
        }

        if (piece.get().getColor() != colorToMove) {
            log.info("Movement impossible: it's {}'s turn, but {} piece selected", colorToMove, piece.get().getColor());
            return MoveResult.error("Сейчас ход другого игрока");
        }

        PieceMoveGenerator generator = factory.getGenerator(piece.getClass());

        Set<Coordinates> availableMove;
        try {
            availableMove = generator.getAvailableMoveSquares(coordinatesFrom, board);
        } catch (Exception e) {
            throw new RuntimeException("Generator error", e);
        }

        if (!availableMove.contains(coordinatesTo)) {
            log.debug("Movement impossible: {} cannot move from {} to {}",
                    piece.getClass().getSimpleName(), coordinatesFrom, coordinatesTo);
            return MoveResult.error("");
        }

        Optional<Piece> capturedPiece = board.getPiece(coordinatesTo);

        board.removePiece(coordinatesFrom);
        board.setPiece(coordinatesTo, piece.orElse(null));

        gameState.setTurn(colorToMove == Color.WHITE ? "b" : "w");

        if ("p".equalsIgnoreCase(piece.getClass().getSimpleName().substring(0, 1)) || capturedPiece.isPresent()) {
            gameState.setHalfMove(0);
        } else {
            gameState.setHalfMove(gameState.getHalfMove() + 1);
        }

        if (colorToMove == Color.BLACK) {
            gameState.setFullMove(gameState.getFullMove() + 1);
        }

        // TODO: В будущем здесь же обновлять gameState.setEnPassant() и gameState.setCastling()
        // при ходах королей/ладей или двойных ходах пешек.

        log.info("Successfully moved {} from {} to {}. Captured {}",
                piece.getClass().getSimpleName(), coordinatesFrom, coordinatesTo,
                capturedPiece.isPresent() ? capturedPiece.getClass().getSimpleName() : "none.");
        return MoveResult.success(capturedPiece.orElse(null));
    }
}
