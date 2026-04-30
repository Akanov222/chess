package edu.iv.javacourse.board;

import edu.iv.javacourse.Color;
import edu.iv.javacourse.Coordinates;
import edu.iv.javacourse.File;
import edu.iv.javacourse.piece.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardFactory {

    public Board setupDefaultPiecesPositions(Board board) {
        log.debug("Starting default board setup");

        // set pawns
        for (File file : File.values()) {
            board.setPiece(new Coordinates(file, 2), new Pawn(Color.WHITE));
            board.setPiece(new Coordinates(file, 7), new Pawn(Color.BLACk));
        }

        setupPieces(board);
        log.debug("Board setup completed");
        return board;
    }

    private void setupPieces(Board board) {
        // set white pieces
        board.setPiece(new Coordinates(File.A, 1), new Rook(Color.WHITE));
        board.setPiece(new Coordinates(File.B, 1), new Knight(Color.WHITE));
        board.setPiece(new Coordinates(File.C, 1), new Bishop(Color.WHITE));
        board.setPiece(new Coordinates(File.D, 1), new Queen(Color.WHITE));
        board.setPiece(new Coordinates(File.E, 1), new King(Color.WHITE));
        board.setPiece(new Coordinates(File.F, 1), new Bishop(Color.WHITE));
        board.setPiece(new Coordinates(File.G, 1), new Knight(Color.WHITE));
        board.setPiece(new Coordinates(File.H, 1), new Rook(Color.WHITE));

        // set black pieces
        board.setPiece(new Coordinates(File.A, 8), new Rook(Color.BLACk));
        board.setPiece(new Coordinates(File.B, 8), new Knight(Color.BLACk));
        board.setPiece(new Coordinates(File.C, 8), new Bishop(Color.BLACk));
        board.setPiece(new Coordinates(File.D, 8), new Queen(Color.BLACk));
        board.setPiece(new Coordinates(File.E, 8), new King(Color.BLACk));
        board.setPiece(new Coordinates(File.F, 8), new Bishop(Color.BLACk));
        board.setPiece(new Coordinates(File.G, 8), new Knight(Color.BLACk));
        board.setPiece(new Coordinates(File.H, 8), new Rook(Color.BLACk));
    }
}



