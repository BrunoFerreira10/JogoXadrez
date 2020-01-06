package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	 

	/*public static final String ANSI_RESET = "";
	public static final String ANSI_BLACK = "";
	public static final String ANSI_RED = "";
	public static final String ANSI_GREEN = "";
	public static final String ANSI_YELLOW = "";
	public static final String ANSI_BLUE = "";
	public static final String ANSI_PURPLE = "";
	public static final String ANSI_CYAN = "";
	public static final String ANSI_WHITE = "";

	public static final String ANSI_BLACK_BACKGROUND = "";
	public static final String ANSI_RED_BACKGROUND = "";
	public static final String ANSI_GREEN_BACKGROUND = "";
	public static final String ANSI_YELLOW_BACKGROUND = "";
	public static final String ANSI_BLUE_BACKGROUND = "";
	public static final String ANSI_PURPLE_BACKGROUND = "";
	public static final String ANSI_CYAN_BACKGROUND = "";
	public static final String ANSI_WHITE_BACKGROUND = "";*/

	public static ChessPosition readChessPosition(Scanner sc) {

		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading chess position!");
		}
	}

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> capturedChessPieces) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturePieces(capturedChessPieces);
		System.out.println("TURN :  " + chessMatch.getTurn());
		
		if(!chessMatch.isCheckmate()) {
		
			System.out.println("WAITING PLAYER:  " + chessMatch.getCurrentPlayer());
			if (chessMatch.isCheck())
				System.out.println("CHECK !!!");
		} else {
			System.out.println("CHECK MATE !!!");
			System.out.println(""+chessMatch.getCurrentPlayer()+" WIN!!");
		}

	}

	public static void printBoard(ChessPiece[][] chessPieces) {
		for (int i = chessPieces.length-1; i >= 0; i--) {
			System.out.print((1 + i) + " ");
			for (int j = 0; j < chessPieces[i].length; j++) {
				printPiece(chessPieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	public static void printBoard(ChessPiece[][] chessPieces, boolean[][] possibleMoves) {
		for (int i = chessPieces.length-1; i >= 0; i--) {
			System.out.print((1 + i) + " ");
			for (int j = 0; j < chessPieces[i].length; j++) {
				printPiece(chessPieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}

		/*
		 * for (int i = 0; i < chessPieces.length; i++) { System.out.print((8 - i) +
		 * " "); for (int j = 0; j < chessPieces[i].length; j++) {
		 * printPiece(chessPieces[i][j], possibleMoves[i][j]); } System.out.println(); }
		 */
		System.out.println("  a b c d e f g h");
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private static void printPiece(ChessPiece piece, boolean background) {

		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

	private static void printCapturePieces(List<ChessPiece> capturedPieces) {

		List<ChessPiece> whiteList = capturedPieces.stream().filter(p -> p.getColor() == Color.WHITE)
				.collect(Collectors.toList());
		List<ChessPiece> blackList = capturedPieces.stream().filter(p -> p.getColor() == Color.BLACK)
				.collect(Collectors.toList());

		System.out.println("CAPTURED PIECES");
		System.out.print("  White:  ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(whiteList.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print("  Black:  ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(blackList.toArray()));
		System.out.print(ANSI_RESET);
		System.out.println();

	}

}
