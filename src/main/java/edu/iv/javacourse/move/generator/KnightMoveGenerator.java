package edu.iv.javacourse.move.generator;

import edu.iv.javacourse.board.CoordinatesShift;

import java.util.Set;

public class KnightMoveGenerator extends SingleStepMoveGenerator {
    @Override
    protected Set<CoordinatesShift> getShifts() {
        return KNIGHTS_SHIFTS();
    }

    private static final Set<CoordinatesShift> KNIGHTS_SHIFTS() {
        return Set.of(
                new CoordinatesShift(1, 2), new CoordinatesShift(2, 1),
                new CoordinatesShift(2, -1), new CoordinatesShift(1, -2),
                new CoordinatesShift(-1, -2), new CoordinatesShift(-2, -1),
                new CoordinatesShift(-2, 1), new CoordinatesShift(-1, 2)
        );
    }
}

/*   @Override
       public Set<Coordinates> getAvailableMoveSquares(Coordinates coordinatesFrom, GameState gameState) {
           Board board = gameState.getBoard();
           Piece knight = board.getPiece(coordinatesFrom).orElseThrow(
                   () -> new IllegalStateException("On coordinates " + coordinatesFrom + " should be figure"));

           Set<Coordinates> result = new HashSet<>();
           for (CoordinatesShift shift : KNIGHTS_SHIFT()) {
               coordinatesFrom.shift(shift).ifPresent(coordinatesTo -> {
                   if (board.isSquareEmpty(coordinatesTo)
                           || (board.getPiece(coordinatesTo).get().getColor() != knight.getColor())) {
                       result.add(coordinatesTo);
                   }
               });
           }
           return result;
       }*/
