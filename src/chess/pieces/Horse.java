package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Horse extends ChessPiece {

	public Horse(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().getPiece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];

		Position auxPosition = new Position(0, 0);

		// left-up
		auxPosition.setValues(position.getRow() + 2, position.getColumn() - 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// up-left
		auxPosition.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// right-up
		auxPosition.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// up-right
		auxPosition.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// left-down
		auxPosition.setValues(position.getRow() - 2, position.getColumn() - 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// down-left
		auxPosition.setValues(position.getRow() - 1, position.getColumn() - 2);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// right-down
		auxPosition.setValues(position.getRow() - 2, position.getColumn() + 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// down-right
		auxPosition.setValues(position.getRow() - 1, position.getColumn() + 2);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		return mat;
	}

}
