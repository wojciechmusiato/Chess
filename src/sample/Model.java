package sample;

import engine.*;

import java.util.ArrayList;
import java.util.List;

public class Model {
    Board board;
    Player whitePlayer;
    Player blackPlayer;
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
        return (ArrayList) board.getTiles()[x][y].getPiece().calculateLegalMoves(board, x, y);
    }

    public boolean isPawnFirstMoved(int x, int y) {
        return board.getTiles()[x][y].getPiece().getPieceType() == Piece.PieceType.PAWN && !((Pawn)board.getTiles()[x][y].getPiece()).firstMoved;

    }

    public void setPawnFirstMoved(int x, int y) {
        ((Pawn)board.getTiles()[x][y].getPiece()).firstMoved = true;
    }

    public Player getCurrentPlayer() {
        if(counter%2==0){
            return whitePlayer;
        }else return blackPlayer;
    }
    public void incrementCounter(){
        counter++;
    }

    public void resetGame() {
        counter=0;
        this.board = new Board();
        this.whitePlayer = new Player(Alliance.WHITE);
        this.blackPlayer = new Player(Alliance.BLACK);
        initializePlayers();
    }
}
