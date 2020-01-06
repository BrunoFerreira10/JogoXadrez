package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int  moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	protected void increaseMoveCount() {
		moveCount++;
	}
	
	protected void decreaseMoveCount() {
		moveCount--;
	}
	
	public int getMoveCount() {
		return moveCount;
	}

	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece chessPiece = (ChessPiece) getBoard().getPiece(position);
		return chessPiece != null && this.color != chessPiece.getColor();
	}
	
	public ChessPosition getChessPosition() {		
		return ChessPosition.fromPosition(position);
	}
}
