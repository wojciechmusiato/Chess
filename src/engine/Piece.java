package engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Piece {
    private Alliance allianceType;
    private PieceType pieceType;
    private List<Move> possibleMoves;
    public abstract Collection<Move> calculateLegalMoves(Board board, int x, int y);

    protected Piece(Alliance allianceType, PieceType pieceType) {
        this.allianceType = allianceType;
        this.pieceType = pieceType;
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
            legalMoves.add(new Move(tile, tiles[i][j]));
            return 1;
        } else {
            legalMoves.add(new Move(tile, tiles[i][j]));
            return 0;
        }
    }

    public enum PieceType{
        ROOK,PAWN,KNIGHT,BISHOP,KING,QUEEN;
    }

}
