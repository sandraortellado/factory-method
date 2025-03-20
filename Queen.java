import java.util.*;

public class Queen extends Piece {
    public Queen(Color c) { 
        this.color = c;
    }
    // implement appropriate methods

    public String toString() {
        return color == Color.WHITE ? "wq" : "bq";
    }

    public List<String> moves(Board b, String loc) {
        Character x = loc.charAt(0);
        Integer y = Integer.parseInt(String.valueOf(loc.charAt(1)));
        List<String> possibleList = new ArrayList<>();
        // Horizontal and Vertical Moves
        for (int i = 1; i <= 7; i++) {
            possibleList.add(String.valueOf((char)(x + i)) + String.valueOf(y));  // Move right
            possibleList.add(String.valueOf((char)(x - i)) + String.valueOf(y));  // Move left
            possibleList.add(String.valueOf((char)(x)) + String.valueOf(y + i));  // Move up
            possibleList.add(String.valueOf((char)(x)) + String.valueOf(y - i));  // Move down
        }

        // Diagonal Moves
        for (int i = 1; i <= 7; i++) {
            possibleList.add(String.valueOf((char)(x + i)) + String.valueOf(y + i));  // Move diagonally up-right
            possibleList.add(String.valueOf((char)(x + i)) + String.valueOf(y - i));  // Move diagonally down-right
            possibleList.add(String.valueOf((char)(x - i)) + String.valueOf(y + i));  // Move diagonally up-left
            possibleList.add(String.valueOf((char)(x - i)) + String.valueOf(y - i));  // Move diagonally down-left
        }

       return eligibleFilter(possibleList, b);
    }

}