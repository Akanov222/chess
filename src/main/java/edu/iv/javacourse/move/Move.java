package edu.iv.javacourse.move;

import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.piece.PieceType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Move {
    public enum Type {
        NORMAL,
        CASTLING_SHORT,
        CASTLING_LONG,
        EN_PASSANT,
        PROMOTION,
        PROMOTION_WITH_CAPTURE
    }

    private final Coordinates from;
    private final Coordinates to;
    private final Type type;
    private final PieceType promotionTo; // null если нет превращения

    public Move(Coordinates from, Coordinates to, Type type, PieceType promotionTo) {
        if (from == null || to == null) {
            log.debug("Coordinates from and to should be not null");
            throw new IllegalArgumentException("from/to не могут быть null");
        }
        if (type == null) {
            log.debug("Type should be not null");
            throw new IllegalArgumentException("type не может быть null");
        }
        if ((type == Type.PROMOTION || type == Type.PROMOTION_WITH_CAPTURE) && promotionTo == null) {
            log.debug("The promotion figure type should be not null");
            throw new IllegalArgumentException("Для превращения нужна фигура");
        }

        this.from = from;
        this.to = to;
        this.type = type;
        this.promotionTo = promotionTo;
    }

    public static Move normal(Coordinates from, Coordinates to) {
        return new Move(from, to, Type.NORMAL, null);
    }

    public static Move castlingShort(Coordinates from, Coordinates to) {
        return new Move(from, to, Type.CASTLING_SHORT, null);
    }

    public static Move castlingLong(Coordinates from, Coordinates to) {
        return new Move(from, to, Type.CASTLING_LONG, null);
    }

    public static Move enPassant(Coordinates from, Coordinates to) {
        return new Move(from, to, Type.EN_PASSANT, null);
    }

    public static Move promotion(Coordinates from, Coordinates to, PieceType promotionTo) {
        return new Move(from, to, Type.PROMOTION, promotionTo);
    }

    public static Move promotionWithCapture(Coordinates from, Coordinates to, PieceType promotionTo) {
        return new Move(from, to, Type.PROMOTION_WITH_CAPTURE, promotionTo);
    }
}
