package edu.iv.javacourse.board;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BoardConsoleRendererTest {
/*
    private Board board = new HashMapBoard();
    private BoardConsoleRenderer renderer;
    private List<String> renderedRows;

    @BeforeEach
    void setUp() {
        new BoardFactory().setupDefaultPiecesPositions(board);
        renderer = new BoardConsoleRenderer();
        renderedRows = renderer.renderToRows(board);
    }

    @Test
    @Description("Проверка что количество горизонталей равно 8")
    void renderRowsShouldReturn8Rows() {
        assertThat(renderedRows).as("The number of horizontals")
                .hasSize(8);
    }

    @ParameterizedTest(name = "Строка {0} должна содержать {1}")
    @Description("Проверка фигур и раскраски на горизонталях")
    @CsvSource({
            "0, ♖", // 8-я горизонталь, Ладья
            "0, \u001B[", // 8-я горизонталь, ANSI
            "6, ♙"  // 2-я горизонталь, Пешка
    })
    void renderRowsShouldContainsPieceAndPainting(int rowIndex, String expectedContent) {
        assertThat(renderedRows.get(rowIndex))
                .as("Check row %d", rowIndex)
                .contains(expectedContent);
    }*/
}
