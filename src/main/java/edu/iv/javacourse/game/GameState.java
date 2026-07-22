package edu.iv.javacourse.game;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GameState {
    @Setter
    private final String gameId;
    @Setter
    private Board board;
    @Setter
    private String turn;
    @Setter
    private String castling;
    private String enPassant;
    @Setter
    int halfMove;
    @Setter
    int fullMove;

    public GameState() {
        this.gameId = UUID.randomUUID().toString().substring(0, 8);
    }

    public GameState(String gameId) {
        this.gameId = gameId;
    }

    public GameState copyForSimulation(String gameId) {
        GameState copyGameState = new GameState(gameId);
        copyGameState.setBoard(this.board.makeCopy());
        copyGameState.setTurn(this.turn);
        copyGameState.setCastling(this.castling);
        copyGameState.setEnPassant(this.enPassant);
        copyGameState.setHalfMove(this.halfMove);
        copyGameState.setFullMove(this.fullMove);
        return copyGameState;
    }

    public void setEnPassant(Coordinates enPassant) {
        this.enPassant = (enPassant == null) ? "-" : enPassant.toNotation();
    }

    public void setEnPassant(String enPassant) {
        this.enPassant = enPassant;
    }

    public void clearEnPassant() {
        if (!"-".equals(enPassant)) {
            enPassant = "-";
        }
    }
}
