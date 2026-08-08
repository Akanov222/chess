package edu.iv.javacourse.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum File {
    A("a"), B("b"), C("c"), D("d"),
    E("e"), F("f"), G("g"), H("h");

    public final String fileCode;

    public static final File[] CASHED_VALUES = File.values();

    public static File getByIndex(int index) {
        return CASHED_VALUES[index];
    }

}
