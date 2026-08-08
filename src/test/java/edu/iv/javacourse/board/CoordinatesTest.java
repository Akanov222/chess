package edu.iv.javacourse.board;

import jdk.jfr.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class CoordinatesTest {

    // --- ТЕСТЫ КОНСТРУКТОРА ---

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

    // TODO: Изменить сообщение задаче CHESS-4
    @Disabled("Disabled until CHESS-4 is fixed")
    @ParameterizedTest
    @CsvSource({
            "-1", "0", "9"
    })
    void shouldCoordinateExceptionWhenRankIsInvalid(int rank) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Coordinates(File.A, rank))
                .withMessage("Rank must be between 1 and 8");
    }

    // TODO: Удалить тест по задаче CHESS-4
    @ParameterizedTest
    @CsvSource({
            "-1", "0", "9"
    })
    void shouldCoordinateExceptionWhenRankIsInvalid2(int rank) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Coordinates(File.A, rank))
                .withMessage("Rank can't be null");
    }

    // --- ТЕСТЫ СМЕЩЕНИЯ (shift) ---

    @Test
    void shiftCantBeNull() {
        Coordinates coordinates = new Coordinates(File.A, 1);
        assertThatNullPointerException().isThrownBy(() -> coordinates.shift(null))
                .withMessage("Shift can't be null");
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

    // --- ТЕСТЫ ЦВЕТА КЛЕТКИ (getColorOfChessBoardSquare) ---

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

    // --- ТЕСТЫ НОТАЦИИ (toNotation) ---

    @Test
    void shouldReturnCorrectChessNotation() {
        Coordinates coordinates = new Coordinates(File.A, 1);
        assertThat(coordinates.toNotation()).isEqualTo("a1");
    }

    // --- ТЕСТЫ LOMBOK CONTRACTS (Equals, HashCode, ToString) ---

    @Test
    void shouldBeEqualAndHashCodeContracts() {
        Coordinates coordinates1 = new Coordinates(File.A, 1);
        Coordinates coordinates2 = new Coordinates(File.A, 1);
        Coordinates coordinates3 = new Coordinates(File.C, 3);

        assertThat(coordinates1).isEqualTo(coordinates2);
        assertThat(coordinates1).hasSameHashCodeAs(coordinates2);

        assertThat(coordinates1).isNotEqualTo(coordinates3);
    }

    @Test
    void shouldHaveCorrectToStringFormat() {
        Coordinates coordinates = new Coordinates(File.E, 4);
        assertThat(coordinates.toString())
                .contains("file=E")
                .contains("rank=4")
                .startsWith("Coordinates(");
    }
}
