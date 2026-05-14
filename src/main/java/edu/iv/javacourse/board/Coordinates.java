package edu.iv.javacourse.board;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class Coordinates {
    private final File file;
    private final int rank;

    public Color getColor() {
        return (file.ordinal() + rank) % 2 == 0 ? Color.WHITE : Color.BLACK;
    }

    public Optional<Coordinates> shift(CoordinatesShift shift) {
        int newFileIndex = file.ordinal() + shift.fileShift;
        int newRankIndex = rank + shift.rankShift;
        if (newFileIndex >= 0 && newFileIndex < 8 && newRankIndex >= 1 && newRankIndex <=8) {
            return Optional.of(new Coordinates(File.getByIndex(newFileIndex), newRankIndex));
        }
        return Optional.empty();
    }
}
