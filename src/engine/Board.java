package engine;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Tile[][] TILES;
    private final int BOARD_SIZE = 8;

    public Board() {
        this.TILES = new Tile[BOARD_SIZE][BOARD_SIZE];
        initializeTiles();
        initializePieces();
    }
    public  Tile[][] getTiles(){
        return TILES;
    }

    private void initializePieces() {
        int i;
        TILES[0][0].setPiece( new Rook(Alliance.BLACK));
        TILES[4][4].setPiece( new Bishop(Alliance.BLACK));
        TILES[0][7].setPiece( new Rook(Alliance.BLACK));
        TILES[7][0].setPiece( new Rook(Alliance.WHITE));
        TILES[7][7].setPiece( new Rook(Alliance.WHITE));

        TILES[0][2].setPiece( new Bishop(Alliance.BLACK));
        TILES[0][5].setPiece( new Bishop(Alliance.BLACK));
        TILES[7][2].setPiece( new Bishop(Alliance.WHITE));
        TILES[7][5].setPiece( new Bishop(Alliance.WHITE));

        TILES[0][1].setPiece( new Knight(Alliance.BLACK));
        TILES[0][6].setPiece( new Knight(Alliance.BLACK));
        TILES[7][1].setPiece( new Knight(Alliance.WHITE));
        TILES[7][6].setPiece( new Knight(Alliance.WHITE));

        for(i=0;i<8;i++){
            TILES[1][i].setPiece( new Pawn(Alliance.BLACK));
            TILES[6][i].setPiece( new Pawn(Alliance.WHITE));
        }
        TILES[0][3].setPiece( new Queen(Alliance.BLACK));
        TILES[7][3].setPiece( new Queen(Alliance.WHITE));
        TILES[0][4].setPiece( new King(Alliance.BLACK));
        TILES[7][4].setPiece( new King(Alliance.WHITE));
        for (i = 0; i < 8; i++) {
            TILES[0][i].setOccupied(true);
            TILES[1][i].setOccupied(true);
            TILES[4][4].setOccupied(true);
            TILES[6][i].setOccupied(true);
            TILES[7][i].setOccupied(true);
        }
    }

    private void initializeTiles() {
        int i, j;
        for (i = 0; i < BOARD_SIZE; i++)
            for (j = 0; j < BOARD_SIZE; j++)
                this.TILES[i][j] = new Tile(i, j, false);
    }

    public void makeMove(Tile sourceTile, Tile destTile) {
        sourceTile.setOccupied(false);
        destTile.setOccupied(true);
        destTile.setPiece(sourceTile.getPiece());

    }


}
