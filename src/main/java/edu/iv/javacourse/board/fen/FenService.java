package edu.iv.javacourse.board.fen;

import edu.iv.javacourse.board.Board;
import edu.iv.javacourse.board.Color;
import edu.iv.javacourse.board.Coordinates;
import edu.iv.javacourse.board.File;
import edu.iv.javacourse.piece.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class FenService {
    private final GameState gameState;

    public void setupDefaultPiecesPositionsService(Board board) {
        log.debug("Starting default board setup");
        fromFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", board);
    }

    public void fromFen(String fen, Board board) {
        // FEN читается сверху вниз: от 8-й горизонтали до 1-й
        String[] parts = fen.split("\\s+");
        String position = parts[0];
        gameState.setTurn(parts[1]);
        gameState.setCastling(parts[2]);
        gameState.setEnPassant(parts[3]);
        gameState.setHalfMove(Integer.valueOf(parts[4]));
        gameState.setFullMove(Integer.valueOf(parts[5]));

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
                    File file = File.getByIndex(fileIndex);
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


    public String toFenService() {

        StringBuilder fen = new StringBuilder();

        for (int rank = 8; rank >= 1; rank--) {
            int emptySquares = 0;
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                if (isSquareEmpty(coordinates)) {
                    emptySquares++;
                } else {
                    if (emptySquares > 0) {
                        fen.append(emptySquares);
                        emptySquares = 0;
                    }
                    Piece piece = getPiece(coordinates);
                    fen.append(piece.getCode(piece.color));
                }
            }
            if (emptySquares > 0) {
                fen.append(emptySquares);
            }
            if (rank > 1) {
                fen.append("/");
            }
        }

        fen.append(" ").append(gameState.getTurn().equals("w") ? "w" : "b");
        fen.append(" ").append(gameState.getCastling());
        fen.append(" ").append(gameState.getEnPassant());
        fen.append(" ").append(gameState.getHalfMove());
        fen.append(" ").append(gameState.getFullMove());
        log.trace("Board to FEN is finished");
        return fen.toString();
    }
}
