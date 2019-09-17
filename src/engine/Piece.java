package engine;

import sample.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Piece {
    private Alliance allianceType;
    private PieceType pieceType;
    private List<Move> possibleMoves;
    protected int x;
    protected int y;
    public abstract Collection<Move> calculateLegalMoves(Model model, boolean withCastling);
    public boolean firstMoved;

    protected Piece(Alliance allianceType, PieceType pieceType, int x, int y) {
        this.x=x;
        this.y=y;
        this.allianceType = allianceType;
        this.pieceType = pieceType;
        firstMoved=false;

    }

    public Alliance getAllianceType() {
        return allianceType;
    }

    public void setAllianceType(Alliance allianceType) {
        this.allianceType = allianceType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public List<Move> getPossibleMoves() {
        return possibleMoves;
    }
    public void setPieceType(PieceType pieceType){
        this.pieceType = pieceType;
    }
    public void setPossibleMoves(List<Move> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    protected int check(Tile[][] tiles, int i, int j, Tile tile, ArrayList<Move> legalMoves){
        if (tiles[i][j].isOccupied() && tiles[i][j].getPiece().getAllianceType() == this.getAllianceType()) {
            return 1;
        } else if (tiles[i][j].isOccupied() && tiles[i][j].getPiece().getAllianceType() != this.getAllianceType()) {
            legalMoves.add(new RegularMove(tile, tiles[i][j]));
            return 1;
        } else {
            legalMoves.add(new RegularMove(tile, tiles[i][j]));
            return 0;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public enum PieceType{
        ROOK,PAWN,KNIGHT,BISHOP,KING,QUEEN;
    }

}
