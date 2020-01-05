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
	
	private void placeNewChessPiece(char column, int row, ChessPiece chessPiece) {
		board.placePiece(chessPiece, new ChessPosition(column, row).toPosition());
	}
	
	public void initialSetup() {
		placeNewChessPiece('a', 1, new Rook(board, Color.BLACK));
		placeNewChessPiece('h', 1, new Rook(board, Color.BLACK));
		placeNewChessPiece('e', 1, new King (board, Color.BLACK));
		
		placeNewChessPiece('b', 6, new King (board, Color.BLACK));
		
	}
	
	
}
