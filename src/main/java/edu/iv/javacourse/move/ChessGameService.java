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

    public MoveResult makeMove(GameState gameState, Move move) {
        MDC.put("gameId", gameState.getGameId());
        try {
            Board board = gameState.getBoard();
            Color colorToMove = "w".equals(gameState.getTurn()) ? Color.WHITE : Color.BLACK;

            // 1. Есть ли фигура на старте?
            Optional<Piece> pieceOptional = board.getPiece(move.getCoordinatesFrom());
            if (pieceOptional.isEmpty()) {
                log.debug("Movement impossible: no piece at {}", move.getCoordinatesFrom());
                return MoveResult.error("На выбранной клетке нет фигуры");
            }

            Piece piece = pieceOptional.get();

            // 2. Очерёдность хода
            if (piece.getColor() != colorToMove) {
                log.debug("Move rejected: invalid turn color. Expected: {}, Got: {}", colorToMove, piece.getColor());
                return MoveResult.error("Сейчас ход другой стороны");
            }

            // 3. УРОВЕНЬ 1: псевдолегальность (геометрия)
            PieceMoveGenerator moveGenerator = moveGeneratorFactory.getGenerator(piece.getPieceType());
            Set<Coordinates> availableMoves =
                    moveGenerator.getAvailableMoveSquares(move.getCoordinatesFrom(), gameState);

            if (!availableMoves.contains(move.getCoordinatesTo())) {
                log.debug("Move rejected: invalid move to {}", move.getCoordinatesTo());
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
