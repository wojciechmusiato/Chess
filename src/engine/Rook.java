package engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {
    public Rook(Alliance alliance) {
        super(alliance, PieceType.ROOK);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board, int x, int y) {
        final ArrayList<Move> legalMoves = new ArrayList<>();

        Tile[][] tiles = board.getTiles();
        Tile tile = tiles[x][y];
        System.out.println("My tile: " + x + " " + y);
        int i;
        for (i = x + 1; i < 8; i++) {
            System.out.println("Checking... "+ i + " " + y);
            if (i == 8) break;
            if(check(tiles, i, y, tile, legalMoves)==1)break;
        }
        for (i = x - 1; i >= 0; i--) {
            System.out.println("Checking... "+ i + " " + y);
            if (i < 0) break;
            if(check(tiles, i, y, tile, legalMoves)==1)break;
        }
        for (i = y + 1; i < 8; i++) {
            System.out.println("Checking... "+ x + " " + i);
            if (i == 8) break;
            if(check(tiles, x, i, tile, legalMoves)==1)break;
        }
        for (i = y - 1; i >= 0; i--) {
            System.out.println("Checking... "+ x + " " + i);
            if (i <0) break;
            if(check(tiles, x, i, tile, legalMoves)==1)break;
        }
        this.setPossibleMoves(legalMoves);

        return legalMoves;
    }
}
