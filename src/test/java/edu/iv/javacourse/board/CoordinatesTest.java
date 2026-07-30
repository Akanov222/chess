package edu.iv.javacourse.board;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class CoordinatesTest {
    @Test
    void shouldCreateCoordinatesSuccessfully() {
        Coordinates coordinates = new Coordinates(File.A, 1);
        assertThat(coordinates.getFile()).isEqualTo(File.A);
        assertThat(coordinates.getRank()).isEqualTo(1);
    }

    @Test
    void shouldThrowExceptionWhenFileIsNull() {
        assertThatNullPointerException().isThrownBy(() -> new Coordinates(null, 1))
                .withMessage("File can't be null");
    }

    @Test
    void shouldCoordinateExceptionWhenRankIsInvalid() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Coordinates(File.A, 9))
                .withMessage("Rank can't be null"); // TODO: Изменить сообщение по вашей задаче CHESS-4
    }

    @Test
    void shiftShouldBeCorrectly() {
        Coordinates startCoordinates = new Coordinates(File.A, 1);
        CoordinatesShift goodShift = new CoordinatesShift(1, 2);
        Optional<Coordinates> resultCoordinates = startCoordinates.shift(goodShift);

        assertThat(resultCoordinates).isPresent().hasValue(new Coordinates(File.B, 3));
    }

    @Test
    void shiftShouldBeBad() {
        Coordinates start = new Coordinates(File.A, 1);
        CoordinatesShift badShift = new CoordinatesShift(-1, 0);
        Optional<Coordinates> result = start.shift(badShift);

        assertThat(result).isEmpty();
    }

    @ParameterizedTest(name = "Square {0} {1} should have color {2}")
    @CsvSource({
            "A, 1, BLACK",
            "H, 1, WHITE",
            "A, 8, WHITE",
            "H, 8, BLACK",
            "E, 4, WHITE",
            "D, 4, BLACK",
    })
    @Description("Should return correct color for standard chessBoard")
    void getColorOfChessBoardSquareCorrect(File file, int rank, Color expectedColor) {
        Coordinates coordinates = new Coordinates(file, rank);
        Color actualColor = coordinates.getColorOfChessBoardSquare();
        assertThat(actualColor)
                .as("Check Checking the domain color for a coordinate %s%d", file, rank)
                .isEqualTo(expectedColor);
    }
}
