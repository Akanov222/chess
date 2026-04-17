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

}
