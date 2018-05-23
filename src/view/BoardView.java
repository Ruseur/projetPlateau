package view;

import Controller.BoardController;
import Model.Board.Board;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

public class BoardView extends BorderPane implements EventHandler, Observer {

    private BoardController boardController;


    public BoardView(BoardController boardController, Board board) {
        this.boardController = boardController;

        board.addObserver(this);

        this.loadHomeView();
    }

    private void loadHomeView() {
        this.setTop(this.getMenuBar());
        this.setCenter(this.getListGamesView());
        this.getCenter().setId("GameView");
    }

    private Pane getListGamesView() {
        VBox listGamesView = new VBox();
        listGamesView.setAlignment(Pos.CENTER);
        listGamesView.setSpacing(10);

        Text title = new Text("Available games: ");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        listGamesView.getChildren().add(title);

        Text tetris = new Text("Tetris");
        tetris.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        listGamesView.getChildren().add(tetris);


        Text blokus = new Text("Blokus (soon)");
        blokus.setFont(Font.font("Arial", FontPosture.ITALIC, 14));
        listGamesView.getChildren().add(blokus);

        return listGamesView;
    }

    private MenuBar getMenuBar() {

        MenuBar menuBar = new MenuBar();
        Menu boardMenu = new Menu("Board");
        MenuItem home = new MenuItem("Home");
        home.setOnAction(this);
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(this);
        boardMenu.getItems().addAll(home,quit);
        menuBar.getMenus().add(boardMenu);

        // Games Menu
        Menu gamesMenuFile = new Menu("Games");

        MenuItem blokusItem = new MenuItem("Blokus");
        blokusItem.setOnAction(this);

        MenuItem tetrisItem = new MenuItem("Tetris");
        tetrisItem.setOnAction(this);

        MenuItem puzzleItem = new MenuItem("Puzzle");
        puzzleItem.setOnAction(this);

        gamesMenuFile.getItems().addAll(blokusItem,tetrisItem,puzzleItem);
        menuBar.getMenus().add(gamesMenuFile);

        return menuBar;
    }

    @Override
    public void handle(Event event) {
        if (event instanceof ActionEvent && event.getSource() instanceof MenuItem){
            String source = ((MenuItem)event.getSource()).getText();
            switch (source) {
                case "Home":
                    this.loadHomeView();
                    break;
                case "Quit":
                    Platform.exit();
                    break;
                case "Tetris":
                    this.boardController.switchGame("Tetris");
                    break;
                case "Blokus":
                    this.boardController.switchGame("Blokus");
                    break;
                case "Puzzle":
                    this.boardController.switchGame("Puzzle");
                    break;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void setBody(Pane body) {
        this.setCenter(body);
    }
}
