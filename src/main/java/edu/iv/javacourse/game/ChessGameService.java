package edu.iv.javacourse.game;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Color;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.event.GameEventPublisher;
import edu.iv.javacourse.event.MoveEvent;
import edu.iv.javacourse.event.listener.GameEventListener;
import edu.iv.javacourse.move.Move;
import edu.iv.javacourse.move.MoveGeneratorFactory;
import edu.iv.javacourse.move.MoveResult;
import edu.iv.javacourse.move.generator.PieceMoveGenerator;
import edu.iv.javacourse.piece.Piece;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.*;

@Slf4j
@Getter
public class ChessGameService {
    private final MoveGeneratorFactory moveGeneratorFactory = new MoveGeneratorFactory();
    private final GameEventPublisher publisher;

    public ChessGameService(GameEventPublisher publisher) {
        this.publisher = publisher;
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
            Set<Coordinates> pseudoLegalMoves =
                    moveGenerator.getAvailableMoveSquares(move.getCoordinatesFrom(), gameState);
            if (!pseudoLegalMoves.contains(move.getCoordinatesTo())) {
                log.debug("Move rejected: invalid move to {}", move.getCoordinatesTo());
                return MoveResult.error("Фигура так ходить не может");
            }

/*            // 4. УРОВЕНЬ 2: не подставляем ли своего короля
            GameState simulatedGameState = gameState.copyForSimulation(gameState.getGameId());
            applyMoveOnBoard(simulatedGameState.getBoard(), move);
            if (isKingUnderAttack(simulatedGameState, colorToMove)) {
                log.debug("Move rejected: own king would be under attack");
                return MoveResult.error("Ход невозможен, король оказывается по шахом");
            }*/

            // 5. Применяем настоящий ход
            Piece pieceCaptured = board.getPiece(move.getCoordinatesTo()).orElse(null);
            applyMoveOnBoard(board, move);
            log.debug("Successfully moved {} from {} to {}. Captured {}",
                    piece.getPieceType().getPieceTypeCode(), move.getCoordinatesFrom(), move.getCoordinatesTo(),
                    pieceCaptured != null ? pieceCaptured.getPieceType().getPieceTypeCode() : "none.");

            MoveEvent moveEvent = new MoveEvent(piece, move.getCoordinatesFrom(), move.getCoordinatesTo(),
                    pieceCaptured);
            notifyMove(moveEvent);
            return MoveResult.success(pieceCaptured);
            // TODO: обновить turn, halfMove/fullMove, права на рокировку, enPassant

        } catch (Exception e) {
            log.error("Critical error during move execution", e);
            return MoveResult.error("Внутренняя ошибка сервера при обработке хода");
        } finally {
            MDC.remove("gameId");
        }
    }

    /**
     * Применяет ход к переданной доске. Пока умеет только NORMAL.
     * Сюда позже добавим CASTLING / EN_PASSANT / PROMOTION.
     */

    private void applyMoveOnBoard(Board board, Move move) {
        Piece piece = board.getPiece(move.getCoordinatesFrom())
                .orElseThrow(() -> new IllegalArgumentException("Figure is not found"));
        board.removePiece(move.getCoordinatesFrom());
        board.setPiece(move.getCoordinatesTo(), piece);
    }

    @SneakyThrows
    private boolean isKingUnderAttack(GameState simulatedGameState, Color colorToMove) {
        Board board = simulatedGameState.getBoard();

        Optional<Coordinates> optionalKingCoordinates = Optional.ofNullable(board.findKing(colorToMove)
                .orElseThrow(() -> new RuntimeException()));
        Color opponentColor = (colorToMove == Color.WHITE) ? Color.BLACK : Color.WHITE;
        Collection<Coordinates> opponentPiecesCoordinates = board.getPiecesCoordinatesByColor(opponentColor);

        for (Coordinates opponentCoordinate : opponentPiecesCoordinates) {
            Piece opponentPiece = board.getPiece(opponentCoordinate)
                    .orElseThrow(() -> new IllegalArgumentException("Date error on coordinates"));
            PieceMoveGenerator moveGenerator = moveGeneratorFactory.getGenerator(opponentPiece.getPieceType());
            Set<Coordinates> attackedSquares = moveGenerator.getAvailableMoveSquares(opponentCoordinate, simulatedGameState);

            Coordinates kingingCoordinates = optionalKingCoordinates.get();
            if (attackedSquares.contains(kingingCoordinates)) {
                return true;
            }
        }

        return false;
    }

    private void notifyMove(MoveEvent moveEvent) {
        for (GameEventListener listener : publisher.getListeners()) {
            try {
                listener.onMove(moveEvent);
            } catch (Exception e) {
                log.error("Listener failed on onMove", e);
            }
        }
    }
}
