package edu.iv.javacourse.piece;

import edu.iv.javacourse.Color;
import edu.iv.javacourse.Coordinates;
import edu.iv.javacourse.board.Board;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
abstract public class Piece {
    public final Color color;
    public Coordinates coordinates;

    Set<Coordinates> getAvailableMoveSquares(Board board) {
        return null;
    }

//    protected abstract Set<CoordinatesShift> getPieceMoves();
}
