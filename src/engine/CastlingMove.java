package engine;

public class CastlingMove extends Move{
    public Tile rookDestMove;
    public Tile rookSourceMove;
    public CastlingMove(Tile sourceMove, Tile destinationMove,Tile rookSourceMove,Tile rookDestMove) {
        super(sourceMove, destinationMove);
        this.rookDestMove=rookDestMove;
        this.rookSourceMove=rookSourceMove;
    }
}
