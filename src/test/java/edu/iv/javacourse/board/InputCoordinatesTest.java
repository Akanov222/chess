package edu.iv.javacourse.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class InputCoordinatesTest {

    @Test @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void shouldPastCorrectInput() {
        InputStream inputStream = new ByteArrayInputStream("a1\n".getBytes());
        InputCoordinates inputCoordinates = new InputCoordinates(inputStream);

        Coordinates result = inputCoordinates.input();
        assertThat(result.getFile()).isEqualTo(File.A);
        assertThat(result.getRank()).isEqualTo(1);
    }
}
