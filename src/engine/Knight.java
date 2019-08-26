package engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {
    public Knight(Alliance alliance) {
        super(alliance, PieceType.KNIGHT);

    }


    @Override
    public Collection<Move> calculateLegalMoves(Board board, int x, int y) {
        System.out.println("Koń");
        final ArrayList<Move> legalMoves = new ArrayList<>();
        Tile[][] tiles = board.getTiles();
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

