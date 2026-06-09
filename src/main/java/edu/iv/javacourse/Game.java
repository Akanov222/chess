package edu.iv.javacourse;

import edu.iv.javacourse.board.*;
import edu.iv.javacourse.event.GameEventListener;
import edu.iv.javacourse.event.MoveEvent;
import edu.iv.javacourse.move.MoveResult;
import edu.iv.javacourse.move.MoveService;
import edu.iv.javacourse.piece.Piece;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class Game {

    private final String gameId;
    private final Board board;
    private final List<GameEventListener> listeners = new ArrayList<>();
    private final MoveService moveService = new MoveService();
//    private final BoardConsoleRenderer renderer = new BoardConsoleRenderer();

    @Getter
    private Color colorToMove = Color.WHITE;

    public Game(Board board) {
        this.board = board;
        this.gameId = UUID.randomUUID().toString().substring(0, 8);
    }
/*
    public boolean makeMove(Coordinates from, Coordinates to) {
        MDC.put("gameId", gameId);
        try {
            Piece piece = board.getPiece(from);
            MoveResult result = moveService.movePiece(board, from, to, colorToMove);

            if (result.isSuccess()) {
                MoveEvent event = new MoveEvent(piece, from, to, result.getCapturedPiece());
                listeners.forEach(l -> l.onMove(event));
                colorToMove = (colorToMove == Color.WHITE) ? Color.BLACK : Color.WHITE;
                return true;
            }
            return false;
        } finally {
            MDC.remove("gameId");
        }
    }

    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public void consoleGameLoop(BoardConsoleRenderer renderer) {
        boolean isWhiteToMove = true;
        int i = 0;
        while (i != 1) {
            // render
            // input
            // make move
            // pass move
            renderer.render(board);
            isWhiteToMove = !isWhiteToMove;
            i++;
        }
    }*/
}
