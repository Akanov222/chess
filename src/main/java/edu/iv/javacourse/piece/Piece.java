package edu.iv.javacourse.piece;

import edu.iv.javacourse.Color;
import edu.iv.javacourse.Coordinates;
import edu.iv.javacourse.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
abstract public class Piece {
    public final Color color;
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
}
