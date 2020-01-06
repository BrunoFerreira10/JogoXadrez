package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];

		Position auxPosition = new Position(0, 0);

		// above - left
		auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setValues(auxPosition.getRow() + 1, auxPosition.getColumn() - 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// above - right
		auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setValues(auxPosition.getRow() + 1, auxPosition.getColumn() + 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// below - left
		auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setValues(auxPosition.getRow()- 1, auxPosition.getColumn() - 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// bellow - right
		auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setValues(auxPosition.getRow() - 1, auxPosition.getColumn() + 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		return mat;
	}

}
