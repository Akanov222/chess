package edu.iv.javacourse;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.board.BoardConsoleRenderer;
import edu.iv.javacourse.event.GameEventListener;
import edu.iv.javacourse.event.MoveEvent;
import edu.iv.javacourse.piece.Piece;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class Game {

    private final String gameId;
    private final Board board;
    private final BoardConsoleRenderer renderer;
    private final List<GameEventListener> listeners = new ArrayList<>();

    public Game(Board board, BoardConsoleRenderer renderer) {
        this.board = board;
        this.renderer = renderer;
        this.gameId = UUID.randomUUID().toString().substring(0, 8);
    }

    public void gameLoop() {
        MDC.put("gameId", gameId);
        try {
            log.info("Game started");
            boolean isWhiteToMove = true;
            int i = 0;
            while (true) {
                // render
                // input
                // make move
                // pass move

                renderer.render(board);
                isWhiteToMove =! isWhiteToMove;
                i++;
            }
        } finally {
            MDC.remove("gameId");
            log.info("Game finished");
        }
    }

    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public void makeMove(Coordinates from, Coordinates to) {
        Piece piece = board.getPiece(from);

        // TRACE пишем прямо тут
        log.trace("Internal: calculating move for piece at {}", from);

        // ... логика хода ...

        // Оповещаем мир о важном событии
        MoveEvent event = new MoveEvent(piece, from, to);
        listeners.forEach(l -> l.onMove(event));
    }
}
