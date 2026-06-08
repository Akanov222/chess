package edu.iv.javacourse.board;

import edu.iv.javacourse.board.fen.FenService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BoardFactory {
    private final String defaultFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    public void setupDefaultPiecesPositions(FenService fenService) {
        log.debug("Starting default board setup");
        fenService.fromFen(defaultFen.toString());
    }
}



