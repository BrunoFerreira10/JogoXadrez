package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().getPiece(position);
		return p == null || p.getColor() != getColor();
	}

	private boolean testRookCastling(Position position) {
		ChessPiece p = ((ChessPiece) getBoard().getPiece(position));
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];

		Position auxPosition = new Position(0, 0);

		// above
		auxPosition.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// left
		auxPosition.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// right
		auxPosition.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// below
		auxPosition.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// above-left
		auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// above-right
		auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// below-left
		auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// below-right
		auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// Castling
		if (getMoveCount() == 0 && !chessMatch.isCheck()) {
			// King side Rook
			Position rookKingPosition = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(rookKingPosition)) {
				Position auxPosition1 = new Position(position.getRow(), position.getColumn() + 1);
				Position auxPosition2 = new Position(position.getRow(), position.getColumn() + 2);

				if (!getBoard().thereIsAPiece(auxPosition1) && !getBoard().thereIsAPiece(auxPosition2)) {
					mat[auxPosition2.getRow()][auxPosition2.getColumn()] = true;
				}
			}

			// Queen side Rook
			Position rookQueenPosition = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(rookQueenPosition)) {
				Position auxPosition1 = new Position(position.getRow(), position.getColumn() - 1);
				Position auxPosition2 = new Position(position.getRow(), position.getColumn() - 2);
				Position auxPosition3 = new Position(position.getRow(), position.getColumn() - 3);

				if (!getBoard().thereIsAPiece(auxPosition1) && !getBoard().thereIsAPiece(auxPosition2) && !getBoard().thereIsAPiece(auxPosition3)) {
					mat[auxPosition2.getRow()][auxPosition2.getColumn()] = true;
				}
			}
		}

		return mat;
	}

}
