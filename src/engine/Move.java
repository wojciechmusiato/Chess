package engine;

public abstract class Move {
    private Tile destination;
    private Tile source;
    private boolean capture;

    public Move(Tile sourceMove, Tile destinationMove) {
        this.destination = destinationMove;
        this.source = sourceMove;
        if (!destination.isOccupied())
            capture = false;
        else
            capture = true;
    }

    public boolean isCapture() {
        return capture;
    }

    public Tile getSourceTile() {
        return source;
    }

    public Tile getDestTile() {
        return destination;
    }
}
