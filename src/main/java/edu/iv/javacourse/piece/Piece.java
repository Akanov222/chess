package edu.iv.javacourse.piece;

import edu.iv.javacourse.Color;
import lombok.AllArgsConstructor;

//@Getter
@AllArgsConstructor
abstract public class Piece {
    public final Color color;
}

    /*
    public Set<Coordinates> getAvailableMoveSquares(Board board) {
        Set<Coordinates> coordinatesSet = new HashSet<>();
        for (CoordinatesShift shift : getPieceMoves()) {
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);
                if (isSquareAvailableForMove(newCoordinates, board)) {
                    coordinatesSet.add(newCoordinates);
                }
            }

        }
        return coordinatesSet;
    }

    private boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        return board.isSquareEmpty(coordinates) || !board.getPiece(coordinates).getColor().equals(color);
    }

    protected abstract Set<CoordinatesShift> getPieceMoves();
*/
