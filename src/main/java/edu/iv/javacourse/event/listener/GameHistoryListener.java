package edu.iv.javacourse.event.listener;

import edu.iv.javacourse.event.MoveEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameHistoryListener implements GameEventListener {

    @Override
    public void onGameStarted() {
        log.info("================ NEW GAME STARTED ================");
    }

    @Override
    public void onMove(MoveEvent event) {
        String capturedInfo = event.getCapturedPiece() != null ?
                String.format(" (captured %s)", event.getCapturedPiece().getClass().getSimpleName()) :
                "";

        log.info("{} moved {} -> {}{}",
                event.getPiece().getClass().getSimpleName(), event.getFromCoordinates(),
                event.getToCoordinates(), capturedInfo);
    }

    @Override
    public void onGameFinished() {
        log.info("================ GAME FINISHED ================");
    }
}
