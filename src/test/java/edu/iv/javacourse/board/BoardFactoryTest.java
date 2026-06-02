package edu.iv.javacourse.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardFactoryTest {

    @Test
    @DisplayName("Check default setup pieces")
    void isValidSetupDefaultPiecesPositions() {
        Board board = new HashMapBoard();
        new BoardFactory().setupDefaultPiecesPositions(board);
        String turn = "w";
        String castling = "KQkq";
        String enPassant = "-";
        int halfMove = 0;
        int fullMove = 1;
        String expectedFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        assertThat(board.toFen(turn, castling, enPassant, halfMove, fullMove))
                .as("Check valid default setup pieces")
                .isEqualTo(expectedFen);
    }
}
