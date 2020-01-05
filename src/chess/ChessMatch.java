package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		this.board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColunms()];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				mat[i][j] = (ChessPiece) board.getPiece(i, j);
			}
		}
		return mat;
	}
	
	public void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(0,0));
		board.placePiece(new Rook(board, Color.WHITE), new Position(0,7));
		board.placePiece(new King(board, Color.WHITE), new Position(0,4));
		
		board.placePiece(new Rook(board, Color.BLACK), new Position(7,0));
		board.placePiece(new Rook(board, Color.BLACK), new Position(7,7));
		board.placePiece(new King(board, Color.BLACK), new Position(7,4));
		
	}
	
	
}
