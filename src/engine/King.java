package engine;

import java.util.ArrayList;
import java.util.Collection;

public class King extends Piece {
    public King(Alliance alliance) {
        super(alliance, PieceType.KING);

    }



    @Override
    public Collection<Move> calculateLegalMoves(Board board, int x, int y) {
        final ArrayList<Move> legalMoves = new ArrayList<>();
        Tile[][] tiles = board.getTiles();
        Tile tile = tiles[x][y];
        int i, j;
        i = x;
        j = y;
        if (i + 1 < 8 && j + 1 < 8) {
            check(tiles, i + 1, j + 1, tile, legalMoves);
        }
        if (i + 1 < 8 && j - 1 >= 0) {
            check(tiles, i + 1, j - 1, tile, legalMoves);
        }
        if (i - 1 >= 0 && j + 1 < 8) {
            check(tiles, i - 1, j + 1, tile, legalMoves);
        }
        if (i - 1 >= 0 && j - 1 >= 0) {
            check(tiles, i - 1, j - 1, tile, legalMoves);
        }
        if (j + 1 < 8) {
            check(tiles, i, j + 1, tile, legalMoves);
        }
        if (j - 1 >= 0) {
            check(tiles, i, j - 1, tile, legalMoves);
        }
        if (i + 1 < 8 ) {
            check(tiles, i + 1, j, tile, legalMoves);
        }
        if (i - 1 >= 0 ) {
            check(tiles, i - 1, j , tile, legalMoves);
        }
        this.setPossibleMoves(legalMoves);
        return legalMoves;
    }

}