package edu.iv.javacourse.move;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Color;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.board.fen.GameState;
import edu.iv.javacourse.event.GameEventListener;
import edu.iv.javacourse.event.GameHistoryListener;
import edu.iv.javacourse.piece.Piece;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class ChessGameService {
    private final MoveGeneratorFactory moveGeneratorFactory = new MoveGeneratorFactory();
    private final List<GameEventListener> listeners = new ArrayList<>();

    public ChessGameService() {
        this.listeners.add(new GameHistoryListener());
    }

    public void addListener(GameEventListener listener) {
        this.listeners.add(listener);
    }

    public MoveResult makeMove(GameState gameState, Coordinates coordinatesFrom, Coordinates coordinatesTo, String gameId) {
        MDC.put("gameId", gameId);
        try {
            Board board = gameState.getBoard();
            Color colorToMove = "w".equals(gameState.getTurn()) ? Color.WHITE : Color.BLACK;
            Optional<Piece> pieceOptional = board.getPiece(coordinatesFrom);
            if (pieceOptional.isEmpty()) {
                log.debug("Movement impossible: no piece at {}", coordinatesFrom);
                return MoveResult.error("На выбранной клетке нет фигуры");
            }

            Piece piece = pieceOptional.get();

            if (piece.getColor() != colorToMove) {
                log.debug("Move rejected: invalid turn color. Expected: {}, Got: {}", colorToMove, piece.getColor());
                return MoveResult.error("Сейчас ход другой стороны");
            }

            PieceMoveGenerator moveGenerator = moveGeneratorFactory
                    .getGenerator((Class<? extends Optional<Piece>>) pieceOptional.getClass());
            Set<Coordinates> availableMoves = moveGenerator.getAvailableMoveSquares(coordinatesFrom, board);

            if (!availableMoves.contains(coordinatesTo)) {
                log.debug("");
                return MoveResult.error("Фигура так ходить не может");
            }

        } catch (Exception e) {
            log.error("Critical error during move execution", e);
            return MoveResult.error("Внутренняя ошибка сервера при обработке хода");
        } finally {
            MDC.remove(gameId);
        }

    }
}
