import java.util.*;
import java.util.regex.Pattern;

abstract public class Piece {
    Color color;
    static Dictionary<String, PieceFactory> pieceMap = new Hashtable<>();
    public static void registerPiece(PieceFactory pf) {
        pieceMap.put(String.valueOf(pf.symbol()), pf);
    }

    public static Piece createPiece(String name) {
        PieceFactory pf = pieceMap.get(String.valueOf(name.charAt(1)));
        String pieceColor = String.valueOf(name.charAt(0));
        if (pieceColor.equals("w")) {
            Color newPieceColor = Color.WHITE;
            return pf.create(newPieceColor);
        }
        else if (pieceColor.equals("b")) {
            Color newPieceColor = Color.BLACK;
            return pf.create(newPieceColor);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Color color() {
	// You should write code here and just inherit it in
	// subclasses. For this to work, you should know
	// that subclasses can access superclass fields.
	    return color;
    }

    abstract public String toString();

    abstract public List<String> moves(Board b, String loc);

    List<String> eligibleFilter(List<String> possibleList, Board gameBoard) {
        List<String> validMoves = new ArrayList<>();
        for (String loc : possibleList) {
            if (isValid(loc, gameBoard)) {
                validMoves.add(loc);
            }
        }
        return validMoves;
    }

    boolean isValid(String loc, Board b) {
        if (loc.length() != 2) {
            return false;
        }
        String x = String.valueOf(loc.charAt(0));
        Integer y = Character.getNumericValue(loc.charAt(1));
        // if (Character.isDigit(loc.charAt(1))) {
        //     // If it's a digit, parse it directly
        //     y = Character.getNumericValue(loc.charAt(1));
        // } else if (loc.charAt(1) == '-') {
        //     // If it's '-', it indicates a negative number; parse accordingly
        //     y = Integer.parseInt(loc.substring(1));
        // } else {
        //     // Handle any other invalid cases or throw an exception
        //     throw new IllegalArgumentException("Invalid input format");
        // }
        if (Pattern.matches("[a-h]", x) && y <= 8 && y >= 1) {
            if (b.getPiece(loc) == null || b.getPiece(loc).color() != color) {
                return true;
            }
        }
        return false;
    }



}