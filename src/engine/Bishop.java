package engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Alliance alliance) {
        super(alliance, PieceType.BISHOP);

    }


    @Override
    public Collection<Move> calculateLegalMoves(Board board, int x, int y) {

        System.out.println("Entered rook");
        final ArrayList<Move> legalMoves = new ArrayList<>();
        Tile[][] tiles = board.getTiles();
        Tile tile = tiles[x][y];
        System.out.println("My tile: " + x + " " + y);
        int i, j;
        i = x + 1;
        j = y + 1;
        while (i < 8 && j < 8) {
              
            if (check(tiles, i, j, tile, legalMoves) == 1) break;
            i++;
            j++;
        }
        i = x - 1;
        j = y - 1;
        while (i >= 0 && j >= 0) {
              
            if (check(tiles, i, j, tile, legalMoves) == 1) break;
            i--;
            j--;
        }
        i = x + 1;
        j = y - 1;
        while (i < 8 && j >= 0) {
              
            if (check(tiles, i, j, tile, legalMoves) == 1) break;
            i++;
            j--;
        }
        i = x - 1;
        j = y + 1;
        while (i >= 0 && j < 8) {
              
            if (check(tiles, i, j, tile, legalMoves) == 1) break;
            i--;
            j++;
        }
        this.setPossibleMoves(legalMoves);
        return legalMoves;

    }

}
