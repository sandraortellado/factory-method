import java.util.List;

public class Test {

    // Run "java -ea Test" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)

    public static void test1() {
	Board b = Board.theBoard();
	Piece.registerPiece(new PawnFactory());
	Piece p = Piece.createPiece("bp");
	b.addPiece(p, "a3");
	assert b.getPiece("a3") == p;
    }

    public static void testKingEdge() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece p = Piece.createPiece("bk");
        b.addPiece(p, "a3");
        List<String> movesList = p.moves(b, "a3");

        assert movesList.contains("a4");
        assert movesList.contains("a2");
        assert movesList.contains("b3");
        assert movesList.contains("b4");
        assert movesList.contains("b2");
        assert movesList.size() == 5;
    }


    //too many black/white, more black/white, too many on type of piece

    public static void testPawnAdvance() {
        Board b = Board.theBoard();
        Piece.registerPiece(new PawnFactory());
        Piece p = Piece.createPiece("wp");
        b.addPiece(p, "h2");
        b.movePiece("h2", "h3");
    }
    
    public static void main(String[] args) {
	test1();
    testKingEdge();
    testPawnAdvance();
    }

    

}