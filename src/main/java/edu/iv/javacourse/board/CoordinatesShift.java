package edu.iv.javacourse.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CoordinatesShift {
    private final int fileShift;
    private final int rankShift;
}
