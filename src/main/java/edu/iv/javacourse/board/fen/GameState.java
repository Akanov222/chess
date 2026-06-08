package edu.iv.javacourse.board.fen;

import edu.iv.javacourse.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameState {
    private Board board;
    private String turn;
    private String castling;
    private String enPassant;
    int halfMove;
    int fullMove;
}
