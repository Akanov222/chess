package edu.iv.javacourse.piece;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
public class CoordinatesShift {
    public final int fileShift;
    public final int rankShift;

    public CoordinatesShift(int fileShift, int rankShift) {
        this.fileShift = fileShift;
        this.rankShift = rankShift;
    }
}

