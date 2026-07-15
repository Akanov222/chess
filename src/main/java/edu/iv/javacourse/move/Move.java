package edu.iv.javacourse.move;

import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.piece.PieceType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Move {
    public enum MoveType {
        NORMAL,
        CASTLING_SHORT,
        CASTLING_LONG,
        EN_PASSANT,
        PROMOTION,
        PROMOTION_WITH_CAPTURE
    }

    private final Coordinates coordinatesFrom;
    private final Coordinates coordinatesTo;
    private final MoveType moveType;
    private final PieceType promotionTo; // null если нет превращения

    public Move(Coordinates coordinatesFrom, Coordinates coordinatesTo, MoveType moveType, PieceType promotionTo) {
        if (coordinatesFrom == null || coordinatesTo == null) {
            throw new IllegalArgumentException("Coordinates from and to should be not null");
        }
        if (moveType == null) {
            throw new IllegalArgumentException("Type should be not null");
        }
        if ((moveType == MoveType.PROMOTION || moveType == MoveType.PROMOTION_WITH_CAPTURE) && promotionTo == null) {
            throw new IllegalArgumentException("The promotion figure type should be not null");
        }

        this.coordinatesFrom = coordinatesFrom;
        this.coordinatesTo = coordinatesTo;
        this.moveType = moveType;
        this.promotionTo = promotionTo;
    }

    public static Move normal(Coordinates from, Coordinates to) {
        return new Move(from, to, MoveType.NORMAL, null);
    }

    public static Move castlingShort(Coordinates from, Coordinates to) {
        return new Move(from, to, MoveType.CASTLING_SHORT, null);
    }

    public static Move castlingLong(Coordinates from, Coordinates to) {
        return new Move(from, to, MoveType.CASTLING_LONG, null);
    }

    public static Move enPassant(Coordinates from, Coordinates to) {
        return new Move(from, to, MoveType.EN_PASSANT, null);
    }

    public static Move promotion(Coordinates from, Coordinates to, PieceType promotionTo) {
        return new Move(from, to, MoveType.PROMOTION, promotionTo);
    }

    public static Move promotionWithCapture(Coordinates from, Coordinates to, PieceType promotionTo) {
        return new Move(from, to, MoveType.PROMOTION_WITH_CAPTURE, promotionTo);
    }
}
