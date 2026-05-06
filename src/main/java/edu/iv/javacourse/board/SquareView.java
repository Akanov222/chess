package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SquareView {
    private final Coordinates coordinates;
    private final Piece piece;
    private final boolean isDark;
    private final String unicodeSymbol;
}
