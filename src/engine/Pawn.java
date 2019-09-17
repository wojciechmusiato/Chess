package engine;

import sample.Model;

import java.util.ArrayList;
import java.util.Collection;

public class Pawn extends Piece {

    public Pawn(Alliance alliance,int x,int y) {
        super(alliance, PieceType.PAWN,x,y);

    }


    @Override
    public Collection<Move> calculateLegalMoves(Model model, boolean withCastling) {

        final ArrayList<Move> legalMoves = new ArrayList<>();
        Tile[][] tiles = model.board.getTiles();
        Tile tile = tiles[x][y];
        int i, j;
        i = x;
        j = y;
        if (this.getAllianceType() == Alliance.WHITE) {
            getPawnMoves(tile, tiles, i, j, legalMoves, -1);
        } else if (this.getAllianceType() == Alliance.BLACK) {
            getPawnMoves(tile, tiles, i, j, legalMoves, 1);
        }

        return legalMoves;
    }

    private void getPawnMoves(Tile tile, Tile[][] tiles, int i, int j, ArrayList<Move> legalMoves, int direction) {
        if ((i + direction) < 8 && (i + direction) >= 0) {

            if ((i + direction) < 8 && (i + direction) >= 0 && !tiles[i + direction][j].isOccupied()) {
                legalMoves.add(new RegularMove(tile, tiles[i + direction][j]));
                if (!firstMoved && !tiles[i + 2 * direction][j].isOccupied()) {
                    legalMoves.add(new RegularMove(tile, tiles[i + 2 * direction][j]));
                }
            }

            if (j <= 5 &&
                    tiles[i + direction][j + 1].isOccupied() && tiles[i + direction][j + 1].getPiece().getAllianceType() != this.getAllianceType()) {
                legalMoves.add(new RegularMove(tile, tiles[i + direction][j + 1]));
            }
            if (j > 0 &&
                    tiles[i + direction][j - 1].isOccupied() && tiles[i + direction][j - 1].getPiece().getAllianceType() != this.getAllianceType()) {
                legalMoves.add(new RegularMove(tile, tiles[i + direction][j - 1]));
            }
        }
    }

    public boolean eligibleForPromotion(int x) {
        if ((this.getAllianceType() == Alliance.WHITE && x == 0) ||
                (this.getAllianceType() == Alliance.BLACK && x == 7)) return true;
        else return false;
    }


}