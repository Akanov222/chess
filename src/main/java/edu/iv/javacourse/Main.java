package edu.iv.javacourse;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.BoardFactory;

public class Main {
    public static void main(String[] args) {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.setupDefaultPiecesPositions();

        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
        renderer.render(board);

        int a = 123;
    }
}
