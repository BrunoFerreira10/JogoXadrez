package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Horse;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkmate;

	private List<Piece> piecesOnTheBoard = new ArrayList<Piece>();
	private List<Piece> capturedPieces = new ArrayList<Piece>();

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean isCheck() {
		return check;
	}

	public boolean isCheckmate() {
		return checkmate;
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

	public boolean[][] possibleMoves(ChessPosition sourceChessPosition) {
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

		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You cannot put yourself in check!!! Want to lose?!");
		}

		check = testCheck(opponent(currentPlayer));
		checkmate = testCheckmate(opponent(currentPlayer));

		if (!checkmate)
			nextTurn();

		return (ChessPiece) capturedPiece;
	}

	private void validateSourcePosition(Position sourcePosition) {
		if (!board.thereIsAPiece(sourcePosition))
			throw new ChessException("There is no piece o source positon!");

		if (currentPlayer != ((ChessPiece) board.getPiece(sourcePosition)).getColor())
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
		((ChessPiece) piece).increaseMoveCount();
		Piece capturedPiece = board.removePiece(targetPosition);
		board.placePiece(piece, targetPosition);

		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}

		// Castling King Side Rook
		if (piece instanceof King && targetPosition.getColumn() == sourcePosition.getColumn() + 2) {
			Position sourceRook = new Position(sourcePosition.getRow(), sourcePosition.getColumn() + 3);
			Position targetRook = new Position(sourcePosition.getRow(), sourcePosition.getColumn() + 1);
			Piece rook = board.removePiece(sourceRook);
			board.placePiece(rook, targetRook);
			((ChessPiece) rook).increaseMoveCount();
		}

		// Castling Queen Side Rook
		if (piece instanceof King && targetPosition.getColumn() == sourcePosition.getColumn() - 2) {
			Position sourceRook = new Position(sourcePosition.getRow(), sourcePosition.getColumn() - 4);
			Position targetRook = new Position(sourcePosition.getRow(), sourcePosition.getColumn() - 1);
			Piece rook = board.removePiece(sourceRook);
			board.placePiece(rook,targetRook);
			((ChessPiece) rook).increaseMoveCount();
		}

		return capturedPiece;
	}

	private void undoMove(Position sourcePosition, Position targetPosition, Piece capturedPiece) {
		Piece piece = board.removePiece(targetPosition);
		((ChessPiece) piece).decreaseMoveCount();
		board.placePiece(piece, sourcePosition);

		if (capturedPiece != null) {
			board.placePiece(capturedPiece, targetPosition);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}

		// Castling King Side Rook
		if (piece instanceof King && targetPosition.getColumn() == sourcePosition.getColumn() + 2) {
			Position sourceRook = new Position(sourcePosition.getRow(), sourcePosition.getColumn() + 3);
			Position targetRook = new Position(sourcePosition.getRow(), sourcePosition.getColumn() + 1);
			Piece rook = board.removePiece(targetRook);
			board.placePiece(rook, sourceRook);
			((ChessPiece) rook).decreaseMoveCount();
		}

		// Castling Queen Side Rook
		if (piece instanceof King && targetPosition.getColumn() == sourcePosition.getColumn() - 2) {
			Position sourceRook = new Position(sourcePosition.getRow(), sourcePosition.getColumn() - 4);
			Position targetRook = new Position(sourcePosition.getRow(), sourcePosition.getColumn() - 1);
			Piece rook = board.removePiece(targetRook);
			board.placePiece(rook, sourceRook);
			((ChessPiece) rook).decreaseMoveCount();
		}
	}

	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(p -> ((ChessPiece) p).getColor() == color)
				.collect(Collectors.toList());
		list = list.stream().filter(p -> p instanceof King).collect(Collectors.toList());

		if (list.size() == 0)
			throw new IllegalStateException("Thes is no " + color + " King on the board !!!!!!!");

		return (ChessPiece) list.get(0);
	}

	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(p -> ((ChessPiece) p).getColor() == opponent(color)).collect(Collectors.toList());

		for (Piece piece : opponentPieces) {
			if (piece.possibleMoves()[kingPosition.getRow()][kingPosition.getColumn()])
				return true;
		}
		return false;
	}

	private boolean testCheckmate(Color color) {
		if (!testCheck(color))
			return false;

		List<Piece> pieces = piecesOnTheBoard.stream().filter(p -> ((ChessPiece) p).getColor() == color)
				.collect(Collectors.toList());

		for (Piece piece : pieces) {
			boolean[][] mat = piece.possibleMoves();
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[i].length; j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece) piece).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						if (!testCheck(color)) {
							undoMove(source, target, capturedPiece);
							return false;
						}
						undoMove(source, target, capturedPiece);
					}
				}
			}
		}

		return true;
	}

	private void placeNewChessPiece(char column, int row, ChessPiece chessPiece) {
		board.placePiece(chessPiece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(chessPiece);
	}

	public void initialSetup() {
		placeNewChessPiece('a', 1, new Rook(board, Color.WHITE));
		//placeNewChessPiece('b', 1, new Horse(board, Color.WHITE));
		//placeNewChessPiece('c', 1, new Bishop(board, Color.WHITE));
		//placeNewChessPiece('d', 1, new Queen(board, Color.WHITE));
		placeNewChessPiece('e', 1, new King(board, Color.WHITE, this));
		//placeNewChessPiece('f', 1, new Bishop(board, Color.WHITE));
		//placeNewChessPiece('g', 1, new Horse(board, Color.WHITE));
		placeNewChessPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewChessPiece('a', 2, new Pawn(board, Color.WHITE));
		placeNewChessPiece('b', 2, new Pawn(board, Color.WHITE));
		placeNewChessPiece('c', 2, new Pawn(board, Color.WHITE));
		placeNewChessPiece('d', 2, new Pawn(board, Color.WHITE));
		placeNewChessPiece('e', 2, new Pawn(board, Color.WHITE));
		placeNewChessPiece('f', 2, new Pawn(board, Color.WHITE));
		placeNewChessPiece('g', 2, new Pawn(board, Color.WHITE));
		placeNewChessPiece('h', 2, new Pawn(board, Color.WHITE));

		placeNewChessPiece('a', 8, new Rook(board, Color.BLACK));
		//placeNewChessPiece('b', 8, new Horse(board, Color.BLACK));
		//placeNewChessPiece('c', 8, new Bishop(board, Color.BLACK));
		//placeNewChessPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewChessPiece('e', 8, new King(board, Color.BLACK, this));
		//placeNewChessPiece('f', 8, new Bishop(board, Color.BLACK));
		//placeNewChessPiece('g', 8, new Horse(board, Color.BLACK));
		placeNewChessPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewChessPiece('a', 7, new Pawn(board, Color.BLACK));
		placeNewChessPiece('b', 7, new Pawn(board, Color.BLACK));
		placeNewChessPiece('c', 7, new Pawn(board, Color.BLACK));
		placeNewChessPiece('d', 7, new Pawn(board, Color.BLACK));
		placeNewChessPiece('e', 7, new Pawn(board, Color.BLACK));
		placeNewChessPiece('f', 7, new Pawn(board, Color.BLACK));
		placeNewChessPiece('g', 7, new Pawn(board, Color.BLACK));
		placeNewChessPiece('h', 7, new Pawn(board, Color.BLACK));

	}

}
