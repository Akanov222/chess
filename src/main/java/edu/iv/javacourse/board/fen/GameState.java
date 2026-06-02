package edu.iv.javacourse.board.fen;

import edu.iv.javacourse.board.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class GameState {
    private Map<Coordinates, Character> pieces;
    private String turn = "w";
    private String castling = "KQkq";
    private String enPassant = "-";
    int halfMove = 0;
    int fullMove = 1;
}
