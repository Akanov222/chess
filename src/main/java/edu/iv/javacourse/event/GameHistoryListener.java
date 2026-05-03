package edu.iv.javacourse.event;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameHistoryListener implements GameEventListener {

    @Override
    public void onMove(MoveEvent event) {
        String capturedInfo = event.capturedPiece != null ?
                String.format(" (captured %s)", event.capturedPiece.getClass().getSimpleName()) :
                "";

        log.info("{} moved {} -> {}{}",
                event.piece.getClass().getSimpleName(),
                event.fromCoordinates,
                event.toCoordinates,
                capturedInfo);
    }
}
