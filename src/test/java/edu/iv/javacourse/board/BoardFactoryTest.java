package edu.iv.javacourse.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardFactoryTest {

    @Test
    @DisplayName("Check default setup pieces")
    void isValidSetupDefaultPiecesPositions() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.setupDefaultPiecesPositions();
        String expectedFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        assertThat(board.toFen()).as("Check valid default setup pieces")
                .isEqualTo(expectedFen);
    }
}
