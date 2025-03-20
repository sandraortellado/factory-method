import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Board {

    private static Board theBoard;
    
    private Piece[][] pieces = new Piece[8][8];

    private List<BoardListener> boardListeners = new ArrayList<>();

    private Board() { }
    
    public static Board theBoard() {
        if (theBoard == null) {
            theBoard = new Board();
        }
        return theBoard;
    }

    public List<Integer> stringConverter(String loc) {
        //what happens when coordinates are not valid
        char x = loc.charAt(0);
        Integer y = Integer.parseInt(String.valueOf(loc.charAt(1))) - 1;
        if (!(Pattern.matches("[a-h]", String.valueOf(x)) && y <= 7 && y >= 0)) {
            throw new IllegalArgumentException("Location not valid");
        }
        Integer xCoord = (int)x - (int)'a';
        List<Integer> coords = new ArrayList<>();
        coords.add(xCoord);
        coords.add(y);
        return coords;
    }

    // Returns piece at given loc or null if no such piece
    // exists
    public Piece getPiece(String loc) {
        //should I just check if coordinates are empty?
        List<Integer> coords = stringConverter(loc);
        return pieces[coords.get(0)][coords.get(1)];
    }

    public void addPiece(Piece p, String loc) {
        List<Integer> coords = stringConverter(loc);
        if (getPiece(loc) == null) {
            pieces[coords.get(0)][coords.get(1)] = p;
        } else {
            throw new IllegalArgumentException("Piece already exists there!");
        }
    }

    public void movePiece(String from, String to) {
	    List<Integer> fromCoords = stringConverter(from);
        List<Integer> toCoords = stringConverter(to);
        Piece p = pieces[fromCoords.get(0)][fromCoords.get(1)];
        if (pieces[fromCoords.get(0)][fromCoords.get(1)] == null) {
            throw new IllegalArgumentException("No piece to move");
        }
        if (p.moves(theBoard, from).contains(to)) {
            pieces[fromCoords.get(0)][fromCoords.get(1)] = null;
            for (BoardListener bl : boardListeners) {
                bl.onMove(from, to, p);
            }
            if (pieces[toCoords.get(0)][toCoords.get(1)] != null) {
                Piece captured = pieces[toCoords.get(0)][toCoords.get(1)];
                pieces[toCoords.get(0)][toCoords.get(1)] = null;
                for (BoardListener bl : boardListeners) {
                    bl.onCapture(p, captured);
                }
            }
            pieces[toCoords.get(0)][toCoords.get(1)] = p;
        } else {throw new IllegalArgumentException("Invalid move");}
    }

    public void clear() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j= 0; j < pieces[i].length; j++) {
                pieces[i][j] = null;
            }
        }
    }

    public void registerListener(BoardListener bl) {
        boardListeners.add(bl);
    }

    public void removeListener(BoardListener bl) {
        for (BoardListener savedListener : boardListeners) {
            if (savedListener.equals(bl)) {
                boardListeners.remove(bl);
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public void removeAllListeners() {
        boardListeners.clear();	    
    }

    public void iterate(BoardInternalIterator bi) {
        for (int i = 0; i < pieces.length; i++) {
            char letter = (char) ('a' + i);
            for (int j = 7; j >= 0; j--) {
                String loc = String.valueOf(letter) + String.valueOf(j+1);
                Piece p = pieces[i][j];
                bi.visit(loc, p);
            }
        }
    }
}