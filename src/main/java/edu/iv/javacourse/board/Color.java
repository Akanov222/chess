package edu.iv.javacourse.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Color {
    WHITE ("WHITE"),
    BLACK ("BLACK");

    public final String colorCode;

}
