package edu.iv.javacourse.game;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GameRegistry {
    // Пока храним игры в мапе которую грузим в init, позже переедем в postgres
    private final ConcurrentMap<String, GameState> games = new ConcurrentHashMap<>();

    public void registerGame(GameState gameState) {
        games.put(gameState.getGameId(), gameState);
    }

    public Optional<GameState> getGame(String gameId) {
        return Optional.ofNullable(games.get(gameId));
    }

    public void removeGame(String gameId) {
        games.remove(gameId);
    }
}
