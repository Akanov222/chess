package edu.iv.javacourse.board.fen;

import edu.iv.javacourse.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GameState {
    private final String gameId;
    private Board board;
    private String turn;
    private String castling;
    private String enPassant;
    int halfMove;
    int fullMove;

    public GameState(String gameId) {
        this.gameId = UUID.randomUUID().toString().substring(0, 8);
    }
}
