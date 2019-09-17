package engine;

import sample.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {
    public boolean isChecked = false;

    public King(Alliance alliance, int x, int y) {
        super(alliance, PieceType.KING, x, y);

    }


    @Override
    public Collection<Move> calculateLegalMoves(Model model, boolean withCastling) {
        final ArrayList<Move> legalMoves = new ArrayList<>();
        Tile[][] tiles = model.board.getTiles();
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
        if (i + 1 < 8) {
            check(tiles, i + 1, j, tile, legalMoves);
        }
        if (i - 1 >= 0) {
            check(tiles, i - 1, j, tile, legalMoves);
        }
        if(withCastling) checkCastlingConditions(model, tile, tiles,  legalMoves);

        this.setPossibleMoves(legalMoves);
        return legalMoves;
    }

    public void checkCastlingConditions(Model model, Tile tile, Tile[][] tiles, List<Move> legalMoves) {
        int colorIndex;
        int i;
        int x, y;
        List<Move> moves;
        if (this.getAllianceType() == Alliance.WHITE) {
            colorIndex = 7;
            moves = model.blackPlayer.getMoves(model);
        } else {
            colorIndex = 0;
            moves = model.whitePlayer.getMoves(model);
        }
        if (this.firstMoved || this.isChecked) {
            System.out.println("ruszył sie lub check");
            return;
        }
        boolean add = true;
        if (tiles[colorIndex][0].isOccupied() && !tiles[colorIndex][0].getPiece().firstMoved) {
            if (tiles[colorIndex][1].isOccupied() || tiles[colorIndex][2].isOccupied() || tiles[colorIndex][3].isOccupied()) {
                System.out.println("occupied, cant make roszada :(");
                add=false;
            }
            for (i = 0; i < moves.size(); i++) {
                x = moves.get(i).getDestTile().x;
                y = moves.get(i).getDestTile().y;
                if (x == colorIndex & (y == 2 || y == 3)) {
                    System.out.println("zagrożonko");
                    add=false;
                }
            }
            if(add) legalMoves.add(new CastlingMove(tile, tiles[colorIndex][2], tiles[colorIndex][0], tiles[colorIndex][3]));
        }
        add=true;
        if (tiles[colorIndex][7].isOccupied() && !tiles[colorIndex][7].getPiece().firstMoved) {
            if (tiles[colorIndex][5].isOccupied() || tiles[colorIndex][6].isOccupied()){
                add=false;
                System.out.println("occupied, cant make roszada :(");
            }
            for (i = 0; i < moves.size(); i++) {
                x = moves.get(i).getDestTile().x;
                y = moves.get(i).getDestTile().y;
                if (x == colorIndex && (y == 5 || y == 6)){
                    System.out.println("zagrożonko");
                    add=false;
                }

            }
            if(add)
                legalMoves.add(new CastlingMove(tile, tiles[colorIndex][6], tiles[colorIndex][7], tiles[colorIndex][5]));
        }

    }
}