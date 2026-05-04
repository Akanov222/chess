package edu.iv.javacourse;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.HashMapBoard;
import edu.iv.javacourse.board.BoardConsoleRenderer;
import edu.iv.javacourse.board.BoardFactory;
import edu.iv.javacourse.event.GameHistoryListener;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Board board = new HashMapBoard();
        new BoardFactory().setupDefaultPiecesPositions(board);

        Game game = new Game(board, new BoardConsoleRenderer());
        game.addListener(new GameHistoryListener());
        game.gameLoop();
//        int a = 123;
    }
}
