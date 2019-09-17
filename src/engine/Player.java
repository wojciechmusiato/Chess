package engine;

import sample.Model;

import java.util.*;

public class Player {
    public List<Piece> pieceList ;
    public Alliance alliance;
    public Player(Alliance alliance) {
        this.alliance = alliance;
    }

    public void initPieces(Tile[][] tiles) {
        this.pieceList = new ArrayList<>();
        if(alliance == Alliance.BLACK){
            pieceList.add(tiles[0][0].getPiece());
            pieceList.add(tiles[0][7].getPiece());
            pieceList.add(tiles[0][1].getPiece());
            pieceList.add(tiles[0][6].getPiece());
            pieceList.add(tiles[0][2].getPiece());
            pieceList.add(tiles[0][5].getPiece());
            pieceList.add(tiles[0][3].getPiece());
            pieceList.add(tiles[0][4].getPiece());
            pieceList.add(tiles[1][0].getPiece());
            pieceList.add(tiles[1][1].getPiece());
            pieceList.add(tiles[1][2].getPiece());
            pieceList.add(tiles[1][3].getPiece());
            pieceList.add(tiles[1][4].getPiece());
            pieceList.add(tiles[1][5].getPiece());
            pieceList.add(tiles[1][6].getPiece());
            pieceList.add(tiles[1][7].getPiece());

        }else{
            pieceList.add(tiles[6][0].getPiece());
            pieceList.add(tiles[6][1].getPiece());
            pieceList.add(tiles[6][2].getPiece());
            pieceList.add(tiles[6][3].getPiece());
            pieceList.add(tiles[6][4].getPiece());
            pieceList.add(tiles[6][5].getPiece());
            pieceList.add(tiles[6][6].getPiece());
            pieceList.add(tiles[6][7].getPiece());
            pieceList.add(tiles[7][0].getPiece());
            pieceList.add(tiles[7][1].getPiece());
            pieceList.add(tiles[7][2].getPiece());
            pieceList.add(tiles[7][3].getPiece());
            pieceList.add(tiles[7][4].getPiece());
            pieceList.add(tiles[7][5].getPiece());
            pieceList.add(tiles[7][6].getPiece());
            pieceList.add(tiles[7][7].getPiece());

        }

    }

    public  boolean checkForCheck(Player player, Model model){
        int i,j;
        for(i=0;i<pieceList.size();i++){
            List<Move> moves =(ArrayList<Move>) player.pieceList.get(i).calculateLegalMoves(model,false);

            if(moves!=null) {
                for (j = 0; j < moves.size(); j++) {
                    /*System.out.println(player.pieceList.get(i).getPieceType() + " " +player.pieceList.get(i).getAllianceType());
                    System.out.println("From");
                    System.out.println(moves.get(j).getSourceTile().x + " " + moves.get(j).getSourceTile().y);
                    System.out.println("To");
                    System.out.println(moves.get(j).getDestTile().x + " " + moves.get(j).getDestTile().y);*/
                    if (moves.get(j).getDestTile().isOccupied() && moves.get(j).getDestTile().getPiece().getPieceType() == Piece.PieceType.KING) {
                        ((King)moves.get(j).getDestTile().getPiece()).isChecked = true;
                        return true;
                    }
                }
            }
        }
        ((King)this.getKing()).isChecked = false;
        return false;
    }

    public Piece getKing(){
        for(int i = 0 ; i < pieceList.size();i++){
            if(pieceList.get(i).getPieceType()== Piece.PieceType.KING){
                return pieceList.get(i);
            }
        }
        return null;
    }



    public boolean checkLeftRook() {
        for(int i = 0 ; i < pieceList.size();i++){
            if(pieceList.get(i).getPieceType()== Piece.PieceType.ROOK &&
                    !pieceList.get(i).firstMoved && pieceList.get(i).y==0){
                return true;
            }
        }
        return false;
    }
    public boolean checkRightRook() {
        for(int i = 0 ; i < pieceList.size();i++){
            if(pieceList.get(i).getPieceType()== Piece.PieceType.ROOK &&
                    !pieceList.get(i).firstMoved && pieceList.get(i).y==7){
                return true;
            }
        }
        return false;
    }

    public List<Move> getMoves(Model model) {
        List<Move> moves = new ArrayList<>();
        int i,j;
        for(i=0;i<pieceList.size();i++){
            moves.addAll(pieceList.get(i).calculateLegalMoves(model,false));
        }
        return moves;
    }
}
