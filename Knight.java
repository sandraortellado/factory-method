import java.util.*;

public class Knight extends Piece {
    public Knight(Color c) { 
        this.color = c;
    }
    // implement appropriate methods

    public String toString() {
        return color == Color.WHITE ? "wn" : "bn";
    }

    public List<String> moves(Board b, String loc) {
        Character x = loc.charAt(0);
        Integer y = Integer.parseInt(String.valueOf(loc.charAt(1)));
        List<String> possibleList = new ArrayList<>(List.of(
            String.valueOf((char)(x+1)) + String.valueOf(y+2),
            String.valueOf((char)(x-1)) + String.valueOf(y-2),
            String.valueOf((char)(x-1)) + String.valueOf(y+2),
            String.valueOf((char)(x+1)) + String.valueOf(y-2),
            String.valueOf((char)(x+2)) + String.valueOf(y+1),
            String.valueOf((char)(x-2)) + String.valueOf(y-1),
            String.valueOf((char)(x-2)) + String.valueOf(y+1),
            String.valueOf((char)(x+2)) + String.valueOf(y-1)));
        for (String item : possibleList) {
            System.out.println(item);
        }
        return eligibleFilter(possibleList, b);
    }

}