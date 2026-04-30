package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardFactory {

    public void setupDefaultPiecesPositions(Board board) {
        log.debug("Starting default board setup");
        fromFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
    }

    public void fromFen(String fen, Board board) {
        // FEN читается сверху вниз: от 8-й горизонтали до 1-й
        String[] ranks = fen.split("/");
        if (ranks.length != 8) {
            throw new IllegalArgumentException("Invalid FEN: should have 8 ranks");
        }

        for (int i = 0; i < 8; i++) {
            int rank = 8 - i; // Индекс в массиве 0 -> 8 горизонталь
            String rankText = ranks[i];
            int fileIndex = 0;

            for (char c : rankText.toCharArray()) {
                if (Character.isDigit(c)) {
                    // Если цифра — пропускаем пустые клетки
                    fileIndex += Character.getNumericValue(c);
                } else {
                    // Если буква — создаем фигуру
                    File file = File.values()[fileIndex];
                    Coordinates coords = new Coordinates(file, rank);
                    board.setPiece(coords, createPieceByChar(c));
                    fileIndex++;
                }
            }
        }
        log.debug("Board setup completed");
//        return board;
    }

    private Piece createPieceByChar(char c) {
        Color color = Character.isUpperCase(c) ? Color.WHITE : Color.BLACK;
        char symbol = Character.toLowerCase(c);

        return switch (symbol) {
            case 'p' -> new Pawn(color);
            case 'n' -> new Knight(color);
            case 'b' -> new Bishop(color);
            case 'r' -> new Rook(color);
            case 'q' -> new Queen(color);
            case 'k' -> new King(color);
            default -> throw new IllegalArgumentException("Unknown FEN piece: " + c);
        };
    }
}



