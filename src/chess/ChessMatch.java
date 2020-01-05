package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private int turn;
	private Color currentPlayer;
	
	public int getTurn() {
		return turn;
	}	
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public ChessMatch() {
		this.board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		
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
	
	public boolean[][] possibleMoves(ChessPosition sourceChessPosition){
		Position sourcePosition = sourceChessPosition.toPosition();
		validateSourcePosition(sourcePosition);
		return board.getPiece(sourcePosition).possibleMoves();
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece) capturedPiece;
	}

	private void validateSourcePosition(Position sourcePosition) {
		if (!board.thereIsAPiece(sourcePosition))
			throw new ChessException("There is no piece o source positon!");

		if(currentPlayer !=  ((ChessPiece)board.getPiece(sourcePosition)).getColor())
			throw new ChessException("This piece is not yours !!!");
		
		if (!board.getPiece(sourcePosition).isThrereAnyPossibleMove())
			throw new ChessException("There is no possible moves for source piece!");
	}

	private void validateTargetPosition(Position sourcePosition, Position targetPosition) {
		if (!board.getPiece(sourcePosition).possibleMove(targetPosition)) {
			throw new ChessException("Piece cannot move to target position!");
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
	}

	private Piece makeMove(Position sourcePosition, Position targetPosition) {
		Piece piece = board.removePiece(sourcePosition);
		Piece capturedPiece = board.removePiece(targetPosition);
		board.placePiece(piece, targetPosition);
		return capturedPiece;
	}

	private void placeNewChessPiece(char column, int row, ChessPiece chessPiece) {
		board.placePiece(chessPiece, new ChessPosition(column, row).toPosition());
	}

	public void initialSetup() {
		placeNewChessPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewChessPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewChessPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewChessPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewChessPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewChessPiece('d', 1, new King(board, Color.WHITE));

		placeNewChessPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewChessPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewChessPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewChessPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewChessPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewChessPiece('d', 8, new King(board, Color.BLACK));

	}

}
