package edu.iv.javacourse;

import edu.iv.javacourse.board.Board;

public class Game {

    private final Board board;
    private BoardConsoleRenderer renderer = new BoardConsoleRenderer();

    public Game(Board board) {
        this.board = board;
    }

    public void gameLoop() {
        boolean isWhiteToMove = true;
        while (true) {
            // render
            // input
            // make move
            // pass move
            renderer.render(board);
            isWhiteToMove =! isWhiteToMove;
        }
    }
}
