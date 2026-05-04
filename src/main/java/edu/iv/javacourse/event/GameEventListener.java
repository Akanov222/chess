package edu.iv.javacourse.event;

public interface GameEventListener {
    void onGameStarted();
    void onMove(MoveEvent event);
    void onGameFinished();
}
