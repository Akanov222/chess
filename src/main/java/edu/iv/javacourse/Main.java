package edu.iv.javacourse;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.HashMapBoard;
import edu.iv.javacourse.board.BoardConsoleRenderer;
import edu.iv.javacourse.board.BoardFactory;

public class Main {
    public static void main(String[] args) {
        Board board = new HashMapBoard();
        new BoardFactory().setupDefaultPiecesPositions(board);

        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
        renderer.render(board);

        int a = 123;
    }
}
