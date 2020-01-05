package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];

		Position auxPosition = new Position(0, 0);

		// above
		auxPosition.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setValues(auxPosition.getRow() + 1, auxPosition.getColumn());
		}
		if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// left
		auxPosition.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setValues(auxPosition.getRow(), auxPosition.getColumn() - 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// right
		auxPosition.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setValues(auxPosition.getRow(), auxPosition.getColumn() + 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		// bellow
		auxPosition.setValues(position.getRow() - 1, position.getColumn());
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setValues(auxPosition.getRow() - 1, auxPosition.getColumn());
		}
		if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition))
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		return mat;
	}

}
