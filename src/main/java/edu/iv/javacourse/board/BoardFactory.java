package edu.iv.javacourse.board;

import edu.iv.javacourse.board.fen.FenService;
import edu.iv.javacourse.piece.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BoardFactory {

    private final FenService fenService;

    public void setupDefaultPiecesPositions(Board board) {
        fenService.setupDefaultPiecesPositionsService(board);
    }
}



