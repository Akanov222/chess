package edu.iv.javacourse.move;

import edu.iv.javacourse.move.generator.KnightMoveGenerator;
import edu.iv.javacourse.move.generator.PieceMoveGenerator;
import edu.iv.javacourse.piece.PieceType;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
public class MoveGeneratorFactory {
    private final Map<PieceType, PieceMoveGenerator> generators = new EnumMap<>(PieceType.class);

    public MoveGeneratorFactory() {
        generators.put(PieceType.KNIGHT, new KnightMoveGenerator());
    }

    public PieceMoveGenerator getGenerator(PieceType pieceType) {
        PieceMoveGenerator generator = generators.get(pieceType);

        if (generator == null) {
            log.debug("No generator found for {}", pieceType);
            throw new IllegalStateException("Не предусмотрены ходы для фигуры " + pieceType);
        }

        return generator;
    }
}
