package edu.iv.javacourse.event;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameLoggingListener implements GameEventListener {

    @Override
    public void onMove(MoveEvent event) {
        log.info("Player moved {} from {} to {}",
                event.piece.getClass().getSimpleName(), event.fromCoordinates, event.toCoordinates);
    }
}
