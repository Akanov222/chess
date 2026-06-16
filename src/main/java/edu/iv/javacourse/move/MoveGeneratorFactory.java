package edu.iv.javacourse.move;

import edu.iv.javacourse.piece.Knight;
import edu.iv.javacourse.piece.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MoveGeneratorFactory {
    private final Map<Class<? extends Piece>, PieceMoveGenerator> generators = new HashMap<>();

    public MoveGeneratorFactory() {
        this.generators.put(Knight.class, new KnightMoveGenerator());
    }

    public PieceMoveGenerator getGenerator(Class<? extends Optional> pieceClass) {
        if (generators.containsKey(pieceClass)) {
            return generators.get(pieceClass);
        }
        else {
            throw new RuntimeException("No generator found for" + pieceClass.getSimpleName());
        }
    }
}
