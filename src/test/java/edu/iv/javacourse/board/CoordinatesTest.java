package edu.iv.javacourse.board;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinatesTest {
    @Test
    void shiftShouldBeCorrectly() {
        Coordinates start = new Coordinates(File.A, 1);
        CoordinatesShift goodShift = new CoordinatesShift(1, 2);
        Optional<Coordinates> result = start.shift(goodShift);

        assertThat(result).isPresent().hasValue(new Coordinates(File.B, 3));
    }

    @Test
    void shiftShouldBeBad() {
        Coordinates start = new Coordinates(File.A, 1);
        CoordinatesShift badShift = new CoordinatesShift(-1, 0);
        Optional<Coordinates> result = start.shift(badShift);

        assertThat(result).isEmpty();
    }
}
