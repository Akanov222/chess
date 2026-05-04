package edu.iv.javacourse;

import edu.iv.javacourse.board.*;
import edu.iv.javacourse.event.GameEventListener;
import edu.iv.javacourse.event.MoveEvent;
import edu.iv.javacourse.move.MoveResult;
import edu.iv.javacourse.move.MoveService;
import edu.iv.javacourse.piece.Piece;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class Game {

    private final String gameId;
    private final Board board;
    private final BoardConsoleRenderer renderer;
    private final List<GameEventListener> listeners = new ArrayList<>();
    private final MoveService moveService = new MoveService();
    private final InputCoordinates inputCoordinates = new InputCoordinates(System.in);
    private Color colorToMove = Color.WHITE;

    public Game(Board board, BoardConsoleRenderer renderer) {
        this.board = board;
        this.renderer = renderer;
        this.gameId = UUID.randomUUID().toString().substring(0, 8);
    }

    public void gameLoop() {
        MDC.put("gameId", gameId);
        try {
            listeners.forEach(GameEventListener::onGameStarted);
            while (true) {
                // render
                // input
                // make move
                // pass move

                renderer.render(board);

                System.out.println("--- " + colorToMove + "'S TURN ---");

                System.out.println("Select piece to move:");
                Coordinates from = inputCoordinates.input();

                System.out.println("Select target square:");
                Coordinates to = inputCoordinates.input();

                boolean success = makeMove(from, to);
                if (success) {
                    colorToMove = (colorToMove == Color.WHITE) ? Color.BLACK : Color.WHITE;
                } else {
                    System.out.println("Invalid move, try again.");
                }
            }
        } finally {
            listeners.forEach(GameEventListener::onGameFinished);
            MDC.remove("gameId");
        }
    }

    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public boolean makeMove(Coordinates from, Coordinates to) {
        Piece piece = board.getPiece(from);

        MoveResult result = moveService.movePiece(board, from, to, colorToMove);

        if (result.isSuccess()) {
            MoveEvent event = new MoveEvent(piece, from, to, result.getCapturedPiece());
            listeners.forEach(l -> l.onMove(event));
            return true;
        } else {
            log.warn("Invalid move attempted from {} to {}", from, to);
            return false;
        }
    }
}
