package engine;

import sample.Model;

import java.util.ArrayList;
import java.util.Collection;

public class Knight extends Piece {
    public Knight(Alliance alliance,int x,int y) {
        super(alliance, PieceType.KNIGHT,x,y);

    }


    @Override
    public Collection<Move> calculateLegalMoves(Model model, boolean withCastling) {
        final ArrayList<Move> legalMoves = new ArrayList<>();
        Tile[][] tiles = model.board.getTiles();
        Tile tile = tiles[x][y];
        int i, j;
        i = x;
        j = y;
        if (i + 2 < 8 && j + 1 < 8) {
            check(tiles, i + 2, j + 1, tile, legalMoves);
        }
        if (i + 2 < 8 && j - 1 >= 0) {
            check(tiles, i + 2, j - 1, tile, legalMoves);
        }
        if (i - 2 >= 0 && j + 1 < 8) {
            check(tiles, i - 2, j + 1, tile, legalMoves);
        }
        if (i - 2 >= 0 && j - 1 >= 0) {
            check(tiles, i - 2, j - 1, tile, legalMoves);
        }
        if (i + 1 < 8 && j + 2 < 8) {
            check(tiles, i + 1, j + 2, tile, legalMoves);
        }
        if (i + 1 < 8 && j - 2 >= 0) {
            check(tiles, i + 1, j - 2, tile, legalMoves);
        }
        if (i - 1 >= 0 && j + 2 < 8) {
            check(tiles, i - 1, j + 2, tile, legalMoves);
        }
        if (i - 1 >= 0 && j - 2 >= 0) {
            check(tiles, i - 1, j - 2, tile, legalMoves);
        }
        this.setPossibleMoves(legalMoves);
        return legalMoves;
    }


}

