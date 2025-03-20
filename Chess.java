
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Chess {
    public static void main(String[] args) {
	
	if (args.length != 2) {
	    System.out.println("Usage: java Chess layout moves");
	}
	Piece.registerPiece(new KingFactory());
	Piece.registerPiece(new QueenFactory());
	Piece.registerPiece(new KnightFactory());
	Piece.registerPiece(new BishopFactory());
	Piece.registerPiece(new RookFactory());
	Piece.registerPiece(new PawnFactory());
	BoardListener logger = new Logger();
	Board.theBoard().registerListener(logger);
	// args[0] is the layout file name
	// args[1] is the moves file name
	// Put your code to read the layout file and moves files
	// here.
	Board gameBoard = Board.theBoard();

	try {
		File layout = new File(args[0]);
		Scanner layoutFileReader = new Scanner(layout);
		while (layoutFileReader.hasNextLine()) {
			String data = layoutFileReader.nextLine();
			if (data.charAt(0) == '#') {
				continue;
			}
			if (data.length() > 5) {
				layoutFileReader.close();
				throw new IllegalArgumentException("Only 5 characters expected per non-comment line.");
			}
			if (!Pattern.matches("[a-h]", String.valueOf(data.charAt(0)))) {
				layoutFileReader.close();
				throw new IllegalArgumentException("The first character of a line must be between 'a' and 'h'.");
			}
			if (!Pattern.matches("[1-8]", String.valueOf(data.charAt(1)))) {
				layoutFileReader.close();
				throw new IllegalArgumentException("The second character of a line must be between '1' and '8'.");
			}
			if (data.charAt(2) != '=') {
				layoutFileReader.close();
				throw new IllegalArgumentException("The third character of a line must be =.");
			}
			if (data.charAt(3) != 'b' && data.charAt(3) != 'w') {
				layoutFileReader.close();
				throw new IllegalArgumentException("The fourth character of a line must be 'b' or 'w'.");
			}
			List<Character> pList = new ArrayList<>(Arrays.asList('k', 'q', 'n', 'b', 'r', 'p'));
			if (!pList.contains(data.charAt(4))) {
				layoutFileReader.close();
				throw new IllegalArgumentException("The fifth character of a line must be the symbol for a piece.");
			}
			// TODO: more characters at the end? Disallow whitespaces? 
			if (gameBoard.getPiece(data.substring(0,2)) != null) {
				layoutFileReader.close();
				throw new IllegalArgumentException("Two pieces cannot be assigned to the same position.");
			}
			Piece newPiece = Piece.createPiece(data.substring(3,5));
			gameBoard.addPiece(newPiece, data.substring(0,2));
		}
		layoutFileReader.close();
	} catch (FileNotFoundException e) {
		System.out.println("No file found");
		e.printStackTrace();
	}
	Board.theBoard().iterate(new BoardPrinter());

	try {
		File moves = new File(args[1]);
		Scanner movesFileReader = new Scanner(moves);
		while (movesFileReader.hasNextLine()) {
			String move = movesFileReader.nextLine();
			if (move.charAt(0) == '#') {
				continue;
			}
			if (move.length() > 5) {
				movesFileReader.close();
				throw new IllegalArgumentException("Only 5 characters expected per non-comment line.");
			}
			if (!Pattern.matches("[a-h]", String.valueOf(move.charAt(0)))) {
				movesFileReader.close();
				throw new IllegalArgumentException("The first character of a line must be between 'a' and 'h'.");
			}
			if (!Pattern.matches("[1-8]", String.valueOf(move.charAt(1)))) {
				movesFileReader.close();
				throw new IllegalArgumentException("The second character of a line must be between '1' and '8'.");
			}
			if (move.charAt(2) != '-') {
				movesFileReader.close();
				throw new IllegalArgumentException("The third character of a line must be =.");
			}
			if (!Pattern.matches("[a-h]", String.valueOf(move.charAt(3)))) {
				movesFileReader.close();
				throw new IllegalArgumentException("The fourth character of a line must be between 'a' and 'h'.");
			}
			if (!Pattern.matches("[1-8]", String.valueOf(move.charAt(4)))) {
				movesFileReader.close();
				throw new IllegalArgumentException("The fifth character of a line must be between '1' and '8'.");
			}
			String from = move.substring(0,2);
			String to = move.substring(3,5);
			gameBoard.movePiece(from, to);
		}
		movesFileReader.close();
	} catch (FileNotFoundException e) {
		System.out.println("No file found");
		e.printStackTrace();
	}

	// Leave the following code at the end of the simulation:
	System.out.println("Final board:");
	Board.theBoard().iterate(new BoardPrinter());
    }
}