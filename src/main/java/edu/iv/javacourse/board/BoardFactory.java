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
            board.setPiece(new Coordinates(file, 2), new Pawn(Color.WHITE));
            board.setPiece(new Coordinates(file, 7), new Pawn(Color.BLACk));
        }
        log.debug("Pawns initialized");

        // set rooks
        board.setPiece(new Coordinates(File.A, 1), new Rook(Color.WHITE));
        board.setPiece(new Coordinates(File.H, 1), new Rook(Color.WHITE));
        board.setPiece(new Coordinates(File.A, 8), new Rook(Color.BLACk));
        board.setPiece(new Coordinates(File.H, 8), new Rook(Color.BLACk));
        log.debug("Rooks initialized");

        // set knights
        board.setPiece(new Coordinates(File.B, 1), new Knight(Color.WHITE));
        board.setPiece(new Coordinates(File.G, 1), new Knight(Color.WHITE));
        board.setPiece(new Coordinates(File.B, 8), new Knight(Color.BLACk));
        board.setPiece(new Coordinates(File.G, 8), new Knight(Color.BLACk));
        log.debug("Knights initialized");

        // set bishops
        board.setPiece(new Coordinates(File.C, 1), new Bishop(Color.WHITE));
        board.setPiece(new Coordinates(File.F, 1), new Bishop(Color.WHITE));
        board.setPiece(new Coordinates(File.C, 8), new Bishop(Color.BLACk));
        board.setPiece(new Coordinates(File.F, 8), new Bishop(Color.BLACk));
        log.debug("Bishops initialized");

        // set queens
        board.setPiece(new Coordinates(File.D, 1), new Queen(Color.WHITE));
        board.setPiece(new Coordinates(File.D, 8), new Queen(Color.BLACk));
        log.debug("Queens initialized");

        // set kings
        board.setPiece(new Coordinates(File.E, 1), new King(Color.WHITE));
        board.setPiece(new Coordinates(File.E, 8), new King(Color.BLACk));
        log.debug("King initialized");

        log.debug("Board setup completed. Total pieces: {}", board.pieces.size());
        return board;
    }
}



