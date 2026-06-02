package edu.iv.javacourse.board;

import edu.iv.javacourse.board.fen.FenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public abstract class BaseBoard implements Board {

    private final FenService fenService;

    @Override
    public String toFen() {
        fenService.toFenService();
    }
}
