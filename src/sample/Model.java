package sample;

import engine.*;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public Board board;
    public Player whitePlayer;
    public Player blackPlayer;
    boolean gameEnded = false;
    private int counter = 0;

    public Model() {
        this.board = new Board();
        this.whitePlayer = new Player(Alliance.WHITE);
        this.blackPlayer = new Player(Alliance.BLACK);
        initializePlayers();
    }

    void initializePlayers() {
        whitePlayer.initPieces(board.getTiles());
        blackPlayer.initPieces(board.getTiles());
    }


    public List<Move> getPossibleMoves(int x, int y) {
        return (ArrayList) board.getTiles()[x][y].getPiece().calculateLegalMoves(this, true);
    }

    public boolean isFirstMoved(int x, int y, Piece.PieceType pieceType) {
        return board.getTiles()[x][y].getPiece().getPieceType() == pieceType && !(board.getTiles()[x][y].getPiece()).firstMoved;

    }

    public void setFirstMoved(int x, int y) {
        (board.getTiles()[x][y].getPiece()).firstMoved = true;
    }


    public boolean isInCheck(Player player) {
        if (player.alliance == Alliance.WHITE) {
            return player.checkForCheck(whitePlayer, this);
        } else {
            return player.checkForCheck(blackPlayer, this);
        }
    }

    public Player getCurrentPlayer() {
        if (counter % 2 == 0) {
            return whitePlayer;
        } else return blackPlayer;
    }

    public void incrementCounter() {
        counter++;
    }

    public void resetGame() {
        counter = 0;
        this.board = new Board();
        this.whitePlayer = new Player(Alliance.WHITE);
        this.blackPlayer = new Player(Alliance.BLACK);
        initializePlayers();
    }


    public boolean checkForPromotion(int x, int y) {
        int index = getCurrentPlayer().alliance==Alliance.WHITE ? 0 : 7;
            if(board.getTiles()[x][y].isOccupied()){
                if(x==index && board.getTiles()[x][y].getPiece().getPieceType()== Piece.PieceType.PAWN){
                    return true;
            }
        }
        return false;
    }

    public void updatePlayerPieces(int y, int x) {
        getCurrentPlayer().pieceList.add(board.getTiles()[x][y].getPiece());
    }
}
