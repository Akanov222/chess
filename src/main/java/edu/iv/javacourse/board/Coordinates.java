package edu.iv.javacourse.board;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.Optional;

@ToString
@EqualsAndHashCode
@Getter
public class Coordinates {
    private final File file;
    private final int rank;


    public Coordinates(File file, int rank) {
        this.file = Objects.requireNonNull(file, "File can't be null");
        if (rank < 1 || rank > 8) {
            throw new IllegalArgumentException("Rank can't be null");
        }
        this.rank = rank;
    }

    public Color getColorOfChessBoardSquare() {
        return (file.ordinal() + rank) % 2 == 0 ? Color.WHITE : Color.BLACK;
    }

    public Optional<Coordinates> shift(CoordinatesShift shift) {
        Objects.requireNonNull(shift, "Shift can't be null");
        int newFileIndex = file.ordinal() + shift.getFileShift();
        int newRankIndex = rank + shift.getRankShift();
        if (newFileIndex < 0 || newFileIndex >= File.CASHED_VALUES.length) {
            return Optional.empty();
        }

        return Optional.of(new Coordinates(File.getByIndex(newFileIndex), newRankIndex));
    }
}
