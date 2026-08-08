package edu.iv.javacourse.game;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.board.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Optional;
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

    public Optional<Coordinates> getEnPassantCoordinate() {
        if (enPassant == null || enPassant.isBlank() || enPassant.equals("-")) {
            return Optional.empty();
        }

        if (enPassant.length() != 2) {
            return Optional.empty();
        }

        String filePartEnPassant = enPassant.substring(0, 1).toLowerCase();
        Optional<File> matchedFile = Arrays.stream(File.CASHED_VALUES)
                .filter(f -> f.getFileCode().equalsIgnoreCase(filePartEnPassant)).findFirst();

        if (matchedFile.isEmpty()) {
            return Optional.empty();
        }

        String rankPartEnPassant = enPassant.substring(1, 2).toLowerCase();
        char rankChar = rankPartEnPassant.charAt(0);
        int enPassantTargetRank = "w".equals(turn) ? 3 : 6;
        if (Character.isDigit(rankChar)) {
            int matchedRank = Character.getNumericValue(rankChar);
            if (matchedRank == enPassantTargetRank) {
                return Optional.of(new Coordinates(matchedFile.get(), matchedRank));
            }
        } else {
            return Optional.empty();
        }
        return Optional.empty();
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
