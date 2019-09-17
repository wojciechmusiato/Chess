package engine;

public  class Tile {
    final public int x, y;
    private boolean isOccupied;
    private Piece piece;
    public Tile(int x, int y, boolean isOccupied) {
        this.x = x;
        this.y = y;
        this.isOccupied= isOccupied;

    }


    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
