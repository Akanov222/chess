package edu.iv.javacourse.board;

import edu.iv.javacourse.piece.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardFactory {

    private String turn = "w";
    private String castling = "KQkq";
    private String enPassant = "-";
    int halfMove = 0;
    int fullMove = 1;

    public void setupDefaultPiecesPositions(Board board) {
        log.debug("Starting default board setup");
        fromFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", board);
    }

    public void fromFen(String fen, Board board) {
        // FEN читается сверху вниз: от 8-й горизонтали до 1-й
        String[] parts = fen.split("\\s+");
        String position = parts[0];
        turn = parts[1];
        castling = parts[2];
        enPassant = parts[3];
        halfMove = Integer.valueOf(parts[4]);
        fullMove = Integer.valueOf(parts[5]);

        fenParser(position, board);
    }
        private void fenParser(String position, Board board) {
            String[] ranks = position.split("/");
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



