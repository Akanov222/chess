package edu.iv.javacourse.move.generator;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.board.CoordinatesShift;
import edu.iv.javacourse.game.GameState;
import edu.iv.javacourse.piece.Piece;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PawnMoveGenerator implements PieceMoveGenerator {

    @Override
    public Set<Coordinates> getAvailableMoveSquares(Coordinates coordinatesFrom, GameState gameState) throws InstantiationException, IllegalAccessException {
        Board board = gameState.getBoard();
        Set<Coordinates> result = new HashSet<>();
//        Piece pawn = board.getPiece(coordinatesFrom).orElseThrow(
//                () -> new IllegalStateException("On coordinates " + coordinatesFrom + " should be figure"));

        // Ход простой, на одину или две клетки вперед
        int directionShift = "w".equals(gameState.getTurn()) ? 1 : -1;
        int startRow = "w".equals(gameState.getTurn()) ? 2 : 7;
        CoordinatesShift forwardOneShift = new CoordinatesShift(0, directionShift);
        CoordinatesShift forwardTwoShift = new CoordinatesShift(0, directionShift * 2);

        coordinatesFrom.shift(forwardOneShift).ifPresent(forwardOneCoordinate -> {
            if (board.isSquareEmpty(forwardOneCoordinate)) {
                result.add(forwardOneCoordinate);

                if (coordinatesFrom.getRank() == startRow) {
                    coordinatesFrom.shift(forwardTwoShift).ifPresent(forwardTwoCoordinate -> {
                        if (board.isSquareEmpty(forwardTwoCoordinate)) {
                            result.add(forwardTwoCoordinate);
                            gameState.setEnPassant(forwardOneCoordinate);
                        }
                    });
                }
            }
        });

        // Ход со взятием фигуры

        // Ход со взятием фигуры на проходе


        return result;
    }
}
