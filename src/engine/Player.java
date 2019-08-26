package engine;

import java.util.*;

public class Player {
    private Map<String, Piece> pieceList ;
    public Alliance alliance;
    private List<Move> actualPossibleMoves;
    public Player(Alliance alliance) {
        this.alliance = alliance;
    }

    public void initPieces(Tile[][] tiles) {
        this.pieceList = new HashMap<>();
        int i,j;
        if(alliance == Alliance.BLACK){
            pieceList.put("BR1",tiles[0][0].getPiece());
            pieceList.put("BR2",tiles[0][7].getPiece());
            pieceList.put("BKN1",tiles[0][1].getPiece());
            pieceList.put("BKN2",tiles[0][6].getPiece());
            pieceList.put("BB1",tiles[0][2].getPiece());
            pieceList.put("BB2",tiles[0][5].getPiece());
            pieceList.put("BQ",tiles[0][3].getPiece());
            pieceList.put("BK",tiles[0][4].getPiece());
            pieceList.put("BP1",tiles[1][0].getPiece());
            pieceList.put("BP2",tiles[1][1].getPiece());
            pieceList.put("BP3",tiles[1][2].getPiece());
            pieceList.put("BP4",tiles[1][3].getPiece());
            pieceList.put("BP5",tiles[1][4].getPiece());
            pieceList.put("BP6",tiles[1][5].getPiece());
            pieceList.put("BP7",tiles[1][6].getPiece());
            pieceList.put("BP8",tiles[1][7].getPiece());

        }else{
            pieceList.put("WR1",tiles[7][0].getPiece());
            pieceList.put("WR2",tiles[7][7].getPiece());
            pieceList.put("WKN1",tiles[7][1].getPiece());
            pieceList.put("WKN2",tiles[7][6].getPiece());
            pieceList.put("WB1",tiles[7][2].getPiece());
            pieceList.put("WB2",tiles[7][5].getPiece());
            pieceList.put("WQ",tiles[7][3].getPiece());
            pieceList.put("WK",tiles[7][4].getPiece());
            pieceList.put("WP1",tiles[6][0].getPiece());
            pieceList.put("WP2",tiles[6][1].getPiece());
            pieceList.put("WP3",tiles[6][2].getPiece());
            pieceList.put("WP4",tiles[6][3].getPiece());
            pieceList.put("WP5",tiles[6][4].getPiece());
            pieceList.put("WP6",tiles[6][5].getPiece());
            pieceList.put("WP7",tiles[6][6].getPiece());
            pieceList.put("WP8",tiles[6][7].getPiece());
        }

    }

    public Map<String, Piece> getPieceList() {
        return pieceList;
    }

    public void setActualPossibleMoves(List<Move> actualPossibleMoves) {
        this.actualPossibleMoves = actualPossibleMoves;
    }

    public List<Move> getActualPossibleMoves() {
        return actualPossibleMoves;
    }
}
