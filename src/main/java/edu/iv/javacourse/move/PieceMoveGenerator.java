package edu.iv.javacourse.move;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Coordinates;

import java.util.Set;

public interface PieceMoveGenerator {
    Set<Coordinates> getAvailableMoveSquares(Coordinates coordinatesFrom, Board board) throws InstantiationException, IllegalAccessException;
}
