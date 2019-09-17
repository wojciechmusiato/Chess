package engine;

import sample.Model;

import java.util.ArrayList;
import java.util.Collection;

public class Bishop extends Piece {
    public Bishop(Alliance alliance,int x,int y) {
        super(alliance, PieceType.BISHOP,x,y);

    }


    @Override
    public Collection<Move> calculateLegalMoves(Model model, boolean withCastling) {

        final ArrayList<Move> legalMoves = new ArrayList<>();
        Tile[][] tiles = model.board.getTiles();
        Tile tile = tiles[x][y];
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
