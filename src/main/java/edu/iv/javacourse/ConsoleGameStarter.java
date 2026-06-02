package edu.iv.javacourse;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.BoardFactory;
import edu.iv.javacourse.board.HashMapBoard;
import edu.iv.javacourse.event.GameHistoryListener;

public class ConsoleGameStarter {
    public static void main(String[] args) {
        Board board = new HashMapBoard();
        new BoardFactory().setupDefaultPiecesPositions(board);
        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
        Game game = new Game(board);
        game.addListener(new GameHistoryListener());
        game.consoleGameLoop(renderer);
    }
}
