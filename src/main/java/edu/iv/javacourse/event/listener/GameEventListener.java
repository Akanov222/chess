package edu.iv.javacourse.event.listener;

import edu.iv.javacourse.event.MoveEvent;

public interface GameEventListener {
    void onGameStarted();
    void onMove(MoveEvent event);
    void onGameFinished();
}
