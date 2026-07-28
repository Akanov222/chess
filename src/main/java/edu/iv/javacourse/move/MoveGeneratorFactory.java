package edu.iv.javacourse.move;

import edu.iv.javacourse.move.generator.*;
import edu.iv.javacourse.piece.PieceType;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
public class MoveGeneratorFactory {
    private final Map<PieceType, PieceMoveGenerator> generators = new EnumMap<>(PieceType.class);

    public MoveGeneratorFactory() {
        generators.put(PieceType.BISHOP, new BishopMoveGenerator());
        generators.put(PieceType.KING, new KingMoveGenerator());
        generators.put(PieceType.KNIGHT, new KnightMoveGenerator());
        generators.put(PieceType.PAWN, new PawnMoveGenerator());
        generators.put(PieceType.QUEEN, new QueenMoveGenerator());
        generators.put(PieceType.ROOK, new RookMoveGenerator());
    }

    public PieceMoveGenerator getGenerator(PieceType pieceType) {
        PieceMoveGenerator generator = generators.get(pieceType);

        if (generator == null) {
            throw new IllegalStateException("No generator found for piece");
        }

        return generator;
    }
}
