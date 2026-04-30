package edu.iv.javacourse.board;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Optional;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Coordinates {
    public final File file;
    public final int rank;

    public Optional<Coordinates> shift(CoordinatesShift shift) {
        int newFileIndex = file.ordinal() + shift.fileShift;
        int newRankIndex = rank + shift.rankShift;
        if (newFileIndex >= 0 && newFileIndex < 8 && newRankIndex >= 1 && newRankIndex <=8) {
            return Optional.of(new Coordinates(File.values()[newFileIndex], newRankIndex));
        }
        return Optional.empty();
    }
}
