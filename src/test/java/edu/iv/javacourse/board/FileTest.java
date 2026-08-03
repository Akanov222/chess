package edu.iv.javacourse.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class FileTest {

    // --- ТЕСТЫ КОНСТАНТ И ОБЩЕЙ СТРУКТУРЫ ---

    @Test
    void shouldHaveExactlyEightFiles() {
        assertThat(File.values())
                .hasSize(8)
                .containsExactly(
                        File.A, File.B, File.C, File.D, File.E, File.F, File.G, File.H
                );
    }

    @Test
    void shouldHaveCorrectlyCashedValuesArray() {
        assertThat(File.CASHED_VALUES)
                .isNotNull()
                .containsExactly(File.values());
    }

    // --- ТЕСТЫ БИЗНЕС-ЛОГИКИ (getByIndex и getFileCode) ---

    @ParameterizedTest
    @CsvSource({
            "0, A, a",
            "1, B, b",
            "4, E, e",
            "7, H, h"
    })
    void shouldReturnCorrectFileAndCodeByIndex(int index, String expectedFileName, String expectedFileCode) {
        File file = File.getByIndex(index);

        assertThat(file).isEqualTo(File.valueOf(expectedFileName));
        assertThat(file.getFileCode()).isEqualTo(expectedFileCode);
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 8, 9, 100})
    void shouldThrowIndexOutOfBondsExceptionWhenIndexIsInvalid(int invalidIndex) {
        assertThatThrownBy(() -> File.getByIndex(invalidIndex))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }
}
