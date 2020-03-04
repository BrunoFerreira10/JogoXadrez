package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];

		Position auxPosition = new Position(0, 0);
		if (getColor() == Color.WHITE) {
			// 1x above
			auxPosition.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
				// 2x above
				if (getMoveCount() == 0) {
					auxPosition.setValues(position.getRow() + 2, position.getColumn());
					if (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition))
						mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
				}
			}

			// above-left
			auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition))
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

			// above-right
			auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition))
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		} else {
			// 1x bellow
			auxPosition.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
				// 2x bellow
				if (getMoveCount() == 0) {
					auxPosition.setValues(position.getRow() - 2, position.getColumn());
					if (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition))
						mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
				}
			}

			// bellow-left
			auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition))
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

			// bellow-right
			auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition))
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;

		}
		
		return mat;
	}

}
