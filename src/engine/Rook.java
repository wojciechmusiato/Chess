package engine;

import sample.Model;

import java.util.ArrayList;
import java.util.Collection;

public class Rook extends Piece {
    public Rook(Alliance alliance, int x, int y) {
        super(alliance, PieceType.ROOK,x,y);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Model model, boolean withCastling) {
        final ArrayList<Move> legalMoves = new ArrayList<>();

        Tile[][] tiles = model.board.getTiles();
        Tile tile = tiles[x][y];
        int i;
        for (i = x + 1; i < 8; i++) {
            if (i == 8) break;
            if(check(tiles, i, y, tile, legalMoves)==1)break;
        }
        for (i = x - 1; i >= 0; i--) {
            if (i < 0) break;
            if(check(tiles, i, y, tile, legalMoves)==1)break;
        }
        for (i = y + 1; i < 8; i++) {
            if (i == 8) break;
            if(check(tiles, x, i, tile, legalMoves)==1)break;
        }
        for (i = y - 1; i >= 0; i--) {
            if (i <0) break;
            if(check(tiles, x, i, tile, legalMoves)==1)break;
        }
        this.setPossibleMoves(legalMoves);

        return legalMoves;
    }
}
