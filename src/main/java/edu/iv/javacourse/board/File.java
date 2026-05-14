package edu.iv.javacourse.board;

import lombok.Getter;

//@ToString
@Getter
public enum File {
    A, B, C, D, E, F, G, H;

    public static final File[] CASHED_VALUES = File.values();

    public static File getByIndex(int index) {
        return CASHED_VALUES[index];
    }
}
