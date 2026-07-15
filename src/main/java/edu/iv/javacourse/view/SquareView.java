package edu.iv.javacourse.view;

import edu.iv.javacourse.board.Color;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.piece.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SquareView {
    private final Coordinates coordinates;
    private final Piece piece;
    private final boolean dark;
    private final String unicodeSymbol;
}
