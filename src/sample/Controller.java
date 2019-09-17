package sample;

import engine.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    private Node selectedPiece;
    private boolean isSelected = false;
    private Model model = null;
    private List<Move> moves;
    private Map<Piece, ImageView> pieces;
    private Player currentPlayer;

    @FXML
    private GridPane chessPane;
    @FXML
    private Label turnText;
    @FXML
    private Label whiteCheckText;
    @FXML
    private Label blackCheckText;
    @FXML
    Button newGameButton;

    public Controller(Model model) {
        this.model = model;
    }


    EventHandler<MouseEvent> tileClickedEventHandler = e -> {
        int x = (int) e.getSceneX() / 50;
        int y = (int) e.getSceneY() / 50;
        if (isSelected) {
            if (!moves.isEmpty()) {
                for (int i = 0; i < moves.size(); i++) {
                    if (moves.get(i).getDestTile().x == y && moves.get(i).getDestTile().y == x) {
                        makeMove(x, y);
                        unselect();
                        return;
                    }
                }
            }
            unselect();
        }

    };

    private void unselect() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    ((Rectangle) getNodeFromGridPane(i, j)).setFill(Color.WHITE);
                } else if (i % 2 == 0 && j % 2 == 1) {
                    ((Rectangle) getNodeFromGridPane(i, j)).setFill(Color.BLACK);
                } else if (i % 2 == 1 && j % 2 == 0) {
                    ((Rectangle) getNodeFromGridPane(i, j)).setFill(Color.BLACK);
                } else {
                    ((Rectangle) getNodeFromGridPane(i, j)).setFill(Color.WHITE);
                }
            }
        }
        isSelected = false;
        selectedPiece = null;
    }

    void printMoves() {
        if (moves != null) {
            for (int i = 0; i < moves.size(); i++)
                System.out.println(moves.get(i).getDestTile().x + " " + moves.get(i).getDestTile().y);
        } else System.out.println("no moves");

    }

    EventHandler<MouseEvent> mouseClickedEventHandler = e -> {
        //System.out.println("clicked piece");
        int x = (int) e.getSceneX() / 50;
        int y = (int) e.getSceneY() / 50;
        Alliance pieceAlliance = model.board.getTiles()[y][x].getPiece().getAllianceType();
        Rectangle r = ((Rectangle) getNodeFromGridPane(x, y));
        int i;
        if (!isSelected && (currentPlayer.alliance == Alliance.WHITE && pieceAlliance == Alliance.WHITE ||
                currentPlayer.alliance == Alliance.BLACK && pieceAlliance == Alliance.BLACK)) {
            r.setFill(Color.ORANGE);
            isSelected = true;
            selectedPiece = (ImageView) e.getSource();
            moves = model.getPossibleMoves(y, x);
            if (!moves.isEmpty()) {
                for (i = 0; i < moves.size(); i++) {
                    select(moves.get(i));
                }
            }
        } else if (isSelected) {
            if (!moves.isEmpty()) {
                System.out.println("im selected");
                for (i = 0; i < moves.size(); i++) {
                    if (moves.get(i).getDestTile().x == y && moves.get(i).getDestTile().y == x) {
                        makeMove(x, y);
                        System.out.println("making move  nad unselecting");
                        unselect();
                        return;
                    }
                }
            }

            System.out.println("empty move list, unselecting");
            unselect();
            return;
        }

    };

    private void makeMove(int x, int y) {
        int i;
        for (i = 0; i < moves.size(); i++) {
            if (moves.get(i).getDestTile().x == y && moves.get(i).getDestTile().y == x) {
                if (moves.get(i).getDestTile().isOccupied()) {
                    chessPane.getChildren().remove(selectedPiece);
                    chessPane.getChildren().remove(pieces.get(model.board.getTiles()[y][x].getPiece()));
                    chessPane.add(selectedPiece, x, y);
                    model.board.makeMove(moves.get(i).getSourceTile(), moves.get(i).getDestTile());
                } else {
                    chessPane.getChildren().remove(selectedPiece);
                    chessPane.add(selectedPiece, x, y);
                    model.board.makeMove(moves.get(i).getSourceTile(), moves.get(i).getDestTile());
                    if(moves.get(i) instanceof CastlingMove){
                        selectedPiece = pieces.get(((CastlingMove) moves.get(i)).rookSourceMove.getPiece());
                        chessPane.getChildren().remove(selectedPiece);
                        System.out.println(selectedPiece);
                        System.out.println(((CastlingMove) moves.get(i)).rookDestMove.x);
                        System.out.println(((CastlingMove) moves.get(i)).rookDestMove.y);
                        chessPane.add(selectedPiece, ((CastlingMove) moves.get(i)).rookDestMove.y,((CastlingMove) moves.get(i)).rookDestMove.x);
                        model.board.makeMove(((CastlingMove) moves.get(i)).rookSourceMove, ((CastlingMove) moves.get(i)).rookDestMove);
                    }
                }
            }
        }
        if (model.isFirstMoved(y, x, Piece.PieceType.PAWN)) model.setFirstMoved(y, x);
        if (model.isFirstMoved(y, x, Piece.PieceType.KING)) model.setFirstMoved(y, x);
        if (model.isFirstMoved(y, x, Piece.PieceType.ROOK)) model.setFirstMoved(y, x);
        if(model.checkForPromotion(y,x)){
            chessPane.getChildren().remove(pieces.get(model.board.getTiles()[y][x].getPiece()));
            pieces.remove(model.board.getTiles()[y][x].getPiece());
            if(model.getCurrentPlayer().alliance==Alliance.WHITE) {
                model.board.getTiles()[y][x].setPiece(new Queen(Alliance.WHITE,y,x));
                pieces.put(model.board.getTiles()[y][x].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/WQ.png"), 50, 50, false, false)));
            }else{
                model.board.getTiles()[y][x].setPiece(new Queen(Alliance.BLACK,y,x));
                pieces.put(model.board.getTiles()[y][x].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/BQ.png"), 50, 50, false, false)));
            }
            chessPane.add(pieces.get(model.board.getTiles()[y][x].getPiece()), x,y);
            pieces.get(model.board.getTiles()[y][x].getPiece()).setOnMouseClicked(mouseClickedEventHandler);
            model.updatePlayerPieces(y,x);
        }
        model.incrementCounter();
        moves.clear();
        currentPlayer = model.getCurrentPlayer();
        if (model.isInCheck(model.whitePlayer)) {
            whiteCheckText.setText("White player CHECK");

        } else {
            whiteCheckText.setText("");
        }
        if (model.isInCheck(model.blackPlayer)) {
            blackCheckText.setText("Black player CHECK");
        } else {
            blackCheckText.setText("");

        }

        turnText.setText(currentPlayer.alliance + "PLAYER TURN");
    }

    private void select(Move move) {

        isSelected = true;
        int y = move.getDestTile().x;
        int x = move.getDestTile().y;

        ((Rectangle) getNodeFromGridPane(x, y)).setFill(Color.ORANGE);
        if(move instanceof  CastlingMove){
            y = ((CastlingMove)move).rookDestMove.x;
            x = ((CastlingMove)move).rookDestMove.y;
            ((Rectangle) getNodeFromGridPane(x, y)).setFill(Color.ORANGE);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i;
        initPieces();
        for (i = 0; i < 64; i++) {
            chessPane.getChildren().get(i).setOnMouseClicked(tileClickedEventHandler);
        }
        currentPlayer = model.getCurrentPlayer();
        turnText.setText(currentPlayer.alliance + " PLAYER TURN");
    }

    @FXML
    private void gameResetEvent(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setContentText("Do you really want to start a new game?");
        ButtonType okButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alert.getButtonTypes().setAll(okButton, noButton);
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        System.out.println("lol");
        if (button == okButton) {
            int i, j;
            for (i = 0; i < 8; i++) {
                for (j = 0; j < 8; j++) {
                    chessPane.getChildren().remove(pieces.get(model.board.getTiles()[i][j].getPiece()));
                }
            }
            model.resetGame();
            initPieces();
            currentPlayer = model.getCurrentPlayer();
            turnText.setText(currentPlayer.alliance + " PLAYER TURN");
        } else if (button == noButton) {
            System.out.println("canceled");
        }


    }

    private Node getNodeFromGridPane(int col, int row) {
        for (Node node : chessPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void initPieces() {
        pieces = new HashMap<Piece, ImageView>();
        Tile[][] t = model.board.getTiles();
        int i;
        pieces.put(t[0][0].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/BR.png"), 50, 50, false, false)));
        pieces.put(t[0][7].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/BR.png"), 50, 50, false, false)));
        pieces.put(t[0][1].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/BKN.png"), 50, 50, false, false)));
        pieces.put(t[0][6].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/BKN.png"), 50, 50, false, false)));
        pieces.put(t[0][2].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/BB.png"), 50, 50, false, false)));
        pieces.put(t[0][5].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/BB.png"), 50, 50, false, false)));
        pieces.put(t[0][3].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/BQ.png"), 50, 50, false, false)));
        pieces.put(t[0][4].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/BK.png"), 50, 50, false, false)));
        pieces.put(t[4][4].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/BB.png"), 50, 50, false, false)));
        for (i = 0; i < 8; i++)
            pieces.put(t[1][i].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/BP.png"), 50, 50, false, false)));
        for (i = 0; i < 8; i++)
            pieces.put(t[6][i].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/WP.png"), 50, 50, false, false)));

        pieces.put(t[7][0].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/WR.png"), 50, 50, false, false)));
        pieces.put(t[7][7].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/WR.png"), 50, 50, false, false)));
        pieces.put(t[7][1].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/WKN.png"), 50, 50, false, false)));
        pieces.put(t[7][6].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/WKN.png"), 50, 50, false, false)));
        pieces.put(t[7][2].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/WB.png"), 50, 50, false, false)));
        pieces.put(t[7][5].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/WB.png"), 50, 50, false, false)));
        pieces.put(t[7][3].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/WQ.png"), 50, 50, false, false)));
        pieces.put(t[7][4].getPiece(), new ImageView(new Image(getClass().getResourceAsStream("images/WK.png"), 50, 50, false, false)));


        chessPane.add(pieces.get(t[0][0].getPiece()), 0, 0);
        chessPane.add(pieces.get(t[0][7].getPiece()), 7, 0);
        chessPane.add(pieces.get(t[0][1].getPiece()), 1, 0);
        chessPane.add(pieces.get(t[0][6].getPiece()), 6, 0);
        chessPane.add(pieces.get(t[0][2].getPiece()), 2, 0);
        chessPane.add(pieces.get(t[0][5].getPiece()), 5, 0);
        chessPane.add(pieces.get(t[0][3].getPiece()), 3, 0);
        chessPane.add(pieces.get(t[0][4].getPiece()), 4, 0);
        chessPane.add(pieces.get(t[7][0].getPiece()), 0, 7);
        chessPane.add(pieces.get(t[7][7].getPiece()), 7, 7);
        chessPane.add(pieces.get(t[7][1].getPiece()), 1, 7);
        chessPane.add(pieces.get(t[7][6].getPiece()), 6, 7);
        chessPane.add(pieces.get(t[7][2].getPiece()), 2, 7);
        chessPane.add(pieces.get(t[7][5].getPiece()), 5, 7);
        chessPane.add(pieces.get(t[7][3].getPiece()), 3, 7);
        chessPane.add(pieces.get(t[7][4].getPiece()), 4, 7);
        chessPane.add(pieces.get(t[4][4].getPiece()), 4, 4);

        for (i = 0; i < 8; i++) {
            chessPane.add(pieces.get(t[1][i].getPiece()), i, 1);
        }
        for (i = 0; i < 8; i++) {
            chessPane.add(pieces.get(t[6][i].getPiece()), i, 6);
        }
        for (Map.Entry<Piece, ImageView> entry : pieces.entrySet()) {
            entry.getValue().setOnMouseClicked(mouseClickedEventHandler);
        }
    }
}
