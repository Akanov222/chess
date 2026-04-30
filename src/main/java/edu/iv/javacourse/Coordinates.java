package edu.iv.javacourse;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Coordinates {
    public final File file;
    public final Integer rank;

    public Coordinates shift(CoordinatesShift shift) {
        return new Coordinates(
                        File.values()[file.ordinal() + shift.fileShift],
                this.rank + shift.rankShift
        );
    }

    public boolean canShift(CoordinatesShift shift) {
        int f = file.ordinal() + shift.fileShift;
        int r = rank + shift.rankShift;

        return f >= 0 && f < 8 && r >= 1 && r <=8;
    }
}
