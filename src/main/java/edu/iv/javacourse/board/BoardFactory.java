package edu.iv.javacourse.board;

import edu.iv.javacourse.Color;
import edu.iv.javacourse.Coordinates;
import edu.iv.javacourse.File;
import edu.iv.javacourse.piece.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardFactory {
    public Board setupDefaultPiecesPositions() {
        log.debug("Starting default board setup");
        Board board = new Board();

        // set pawns
        for (File file : File.values()) {
            board.setPiece(new Coordinates(file, 2), new Pawn(Color.WHITE, new Coordinates(file, 2)));
            board.setPiece(new Coordinates(file, 7), new Pawn(Color.BLACk, new Coordinates(file, 7)));
        }
        log.debug("Pawns initialized");

        // set rooks
        board.setPiece(new Coordinates(File.A, 1), new Rook(Color.WHITE, new Coordinates(File.A, 1)));
        board.setPiece(new Coordinates(File.H, 1), new Rook(Color.WHITE, new Coordinates(File.H, 1)));
        board.setPiece(new Coordinates(File.A, 8), new Rook(Color.BLACk, new Coordinates(File.A, 8)));
        board.setPiece(new Coordinates(File.H, 8), new Rook(Color.BLACk, new Coordinates(File.H, 8)));
        log.debug("Rooks initialized");

        // set knights
        board.setPiece(new Coordinates(File.B, 1), new Knight(Color.WHITE, new Coordinates(File.B, 1)));
        board.setPiece(new Coordinates(File.G, 1), new Knight(Color.WHITE, new Coordinates(File.G, 1)));
        board.setPiece(new Coordinates(File.B, 8), new Knight(Color.BLACk, new Coordinates(File.B, 8)));
        board.setPiece(new Coordinates(File.G, 8), new Knight(Color.BLACk, new Coordinates(File.G, 8)));
        log.debug("Knights initialized");

        // set bishops
        board.setPiece(new Coordinates(File.C, 1), new Bishop(Color.WHITE, new Coordinates(File.C, 1)));
        board.setPiece(new Coordinates(File.F, 1), new Bishop(Color.WHITE, new Coordinates(File.F, 1)));
        board.setPiece(new Coordinates(File.C, 8), new Bishop(Color.BLACk, new Coordinates(File.C, 8)));
        board.setPiece(new Coordinates(File.F, 8), new Bishop(Color.BLACk, new Coordinates(File.F, 8)));
        log.debug("Bishops initialized");

        // set queens
        board.setPiece(new Coordinates(File.D, 1), new Queen(Color.WHITE, new Coordinates(File.D, 1)));
        board.setPiece(new Coordinates(File.D, 8), new Queen(Color.BLACk, new Coordinates(File.D, 8)));
        log.debug("Queens initialized");

        // set kings
        board.setPiece(new Coordinates(File.E, 1), new King(Color.WHITE, new Coordinates(File.E, 1)));
        board.setPiece(new Coordinates(File.E, 8), new King(Color.BLACk, new Coordinates(File.E, 8)));
        log.debug("King initialized");

        log.debug("Board setup completed. Total pieces: {}", board.pieces.size());
        return board;
    }
}



