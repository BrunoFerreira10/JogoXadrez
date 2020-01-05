package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().getPiece(position);
		return p == null || p.getColor() != getColor();
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

		return mat;
	}

}
