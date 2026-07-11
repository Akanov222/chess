package edu.iv.javacourse.event;

import edu.iv.javacourse.event.listener.GameEventListener;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Getter
public class GameEventPublisher {
    private static final Logger log = LoggerFactory
            .getLogger(GameEventPublisher.class);
    private final List<GameEventListener> listeners = new ArrayList<>();

    public void addListener(GameEventListener listener) {
        this.listeners.add(listener);
    }

    public void publishGameStarted() {
        safe(GameEventListener::onGameStarted);
    }

    public void publishMove(MoveEvent event) {
        safe(l -> l.onMove(event));
    }

    public void publishGameFinished() {
        safe(GameEventListener::onGameFinished);
    }

    private void safe(Consumer<GameEventListener> action) {
        for (GameEventListener l : listeners) {
            try {
                action.accept(l);
            } catch (Exception exception) {
                log.error("Listener failed", exception);
            }
        }
    }
}
