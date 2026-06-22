package edu.iv.javacourse.view;

import edu.iv.javacourse.board.Board;

public interface BoardRenderer<T> {
    T render(Board board);
}
