import java.util.*;
import java.util.regex.Pattern;

public class Pawn extends Piece {
    public Pawn(Color c) { 
        this.color = c;
    }
    // implement appropriate methods

    public String toString() {
        return color == Color.WHITE ? "wp" : "bp";
    }


    public List<String> moves(Board b, String loc) {
        Character x = loc.charAt(0);
        Integer y = Integer.parseInt(String.valueOf(loc.charAt(1)));
        List<String> possibleList = new ArrayList<>();
	    if (color == Color.WHITE) {
            if (Pattern.matches("[a-h]", String.valueOf((char)(x))) && y+2 <= 8 && y+2 >= 1) {
                String homeMoveLoc = String.valueOf((char)(x)) + String.valueOf(y+2);
                if (y == 2 && b.getPiece(homeMoveLoc) == null) {
                    possibleList.add(homeMoveLoc);
                }
            }
            if (Pattern.matches("[a-h]", String.valueOf((char)(x))) && y+1 <= 8 && y+1 >= 1) {
                String oneMoveLoc = String.valueOf((char)(x)) + String.valueOf(y+1);
                if (b.getPiece(oneMoveLoc) == null) {
                    possibleList.add(oneMoveLoc);
                }

            }
            if (Pattern.matches("[a-h]", String.valueOf((char)(x+1))) && y+1 <= 8 && y+1 >= 1) {
                String Diag1 = String.valueOf((char)(x + 1)) + String.valueOf(y+1);
                if (b.getPiece(Diag1) != null && b.getPiece(Diag1).color() != color) {
                    possibleList.add(Diag1);
                }
            }
            if (Pattern.matches("[a-h]", String.valueOf((char)(x-1))) && y+1 <= 8 && y+1 >= 1) {
                String Diag2 = String.valueOf((char)(x - 1)) + String.valueOf(y+1);
                if (b.getPiece(Diag2) != null && b.getPiece(Diag2).color() != color) {
                    possibleList.add(Diag2);
                }
            }
        }
        if (color == Color.BLACK) {
            if (Pattern.matches("[a-h]", String.valueOf((char)(x))) && y-2 <= 8 && y-2 >= 1) {
                String homeMoveLoc = String.valueOf((char)(x)) + String.valueOf(y-2);
                if (y == 7 && b.getPiece(homeMoveLoc) == null) {
                    possibleList.add(homeMoveLoc);
                }
            }
            if (Pattern.matches("[a-h]", String.valueOf((char)(x))) && y-1 <= 8 && y-1 >= 1) {
                String oneMoveLoc = String.valueOf((char)(x)) + String.valueOf(y-1);
                if (b.getPiece(oneMoveLoc) == null) {
                    possibleList.add(oneMoveLoc);
                }
            }
            if (Pattern.matches("[a-h]", String.valueOf((char)(x+1))) && y-1 <= 8 && y-1 >= 1) {
                String Diag1 = String.valueOf((char)(x + 1))+ String.valueOf(y-1);
                if (b.getPiece(Diag1) != null && b.getPiece(Diag1).color() != color) {
                    possibleList.add(Diag1);
                }
            }
            if (Pattern.matches("[a-h]", String.valueOf((char)(x-1))) && y-1 <= 8 && y-1 >= 1) {
                String Diag2 = String.valueOf((char)(x - 1))+ String.valueOf(y-1);
                if (b.getPiece(Diag2) != null && b.getPiece(Diag2).color() != color) {
                    possibleList.add(Diag2);
                }
            }
        }
        return possibleList;
    }
}