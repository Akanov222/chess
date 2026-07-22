package edu.iv.javacourse.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

//@ToString
@Getter
@AllArgsConstructor
public enum File {
    A("A"), B("B"), C("C"), D("D"),
    E("E"), F("F"), G("G"), H("H");

    public final String fileCode;

    public static final File[] CASHED_VALUES = File.values();

    public static final String[] CASHED_FILES_CODES = Arrays.stream(CASHED_VALUES)
            .map(File::getFileCode).toArray(String[]::new);

    public static File getByIndex(int index) {
        return CASHED_VALUES[index];
    }

    public static String getStringByIndex(int index) {
        return CASHED_FILES_CODES[index];
    }

}
