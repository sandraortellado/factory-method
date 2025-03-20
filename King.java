import java.util.*;
import java.util.regex.Pattern;

public class King extends Piece {
    public King(Color c) { 
        this.color = c;
    }
    // implement appropriate methods

    public String toString() {
        return color == Color.WHITE ? "wk" : "bk";
    }

    public List<String> moves(Board b, String loc) {
        
        char x = loc.charAt(0);
        Integer y = Integer.parseInt(String.valueOf(loc.charAt(1)));
        
        List<String> possibleList = new ArrayList<>(List.of(
            String.valueOf((char)(x+1)) + String.valueOf(y),
            String.valueOf((char)(x+1)) + String.valueOf(y+1),
            String.valueOf((char)(x+1)) + String.valueOf(y-1),
            String.valueOf((char)(x)) + String.valueOf(y+1),
            String.valueOf((char)(x)) + String.valueOf(y-1),
            String.valueOf((char)(x-1)) + String.valueOf(y),
            String.valueOf((char)(x-1)) + String.valueOf(y+1),
            String.valueOf((char)(x-1)) + String.valueOf(y-1)));

       return eligibleFilter(possibleList, b);
        
    }

}