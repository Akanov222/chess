package edu.iv.javacourse.piece;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class PieceTypeTest {

    @Test
    void shouldHaveSixFiles() {
        assertThat(PieceType.values())
                .hasSize(6)
                .contains(PieceType.KING, PieceType.QUEEN, PieceType.ROOK,
                        PieceType.KNIGHT, PieceType.BISHOP, PieceType.PAWN);
    }

    @ParameterizedTest
    @CsvSource({
            "KING, K",
            "QUEEN, Q",
            "ROOK, R",
            "KNIGHT, N",
            "BISHOP, B",
            "PAWN, P"
    })
    void shouldReturnCorrectPieceTypeCode(PieceType pieceType, String expectedPieceTypeCode) {
        assertThat(pieceType.getPieceTypeCode()).isEqualTo(expectedPieceTypeCode);
    }
}
