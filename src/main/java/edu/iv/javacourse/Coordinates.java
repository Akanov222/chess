package edu.iv.javacourse;

import edu.iv.javacourse.piece.CoordinatesShift;
import lombok.*;

//@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Coordinates {
    public final File file;
    public final Integer rank;

    public Coordinates shift(CoordinatesShift shift) {
        return new Coordinates(
                        File.values()[(file.ordinal() + shift.fileShift) % File.values().length],
                (this.rank + shift.rankShift)
        );
    }

    public boolean canShift(CoordinatesShift shift) {
        int f = file.ordinal() + shift.fileShift;
        int r = rank + shift.rankShift;
        if ((f < 0) || (f > 7)) {
            return false;
        }
        if ((r < 1) || (r > 8)) {
            return false;
        }
        return true;
    }
}
