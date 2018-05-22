package view;

import Controller.BoardController;
import Model.Plateau.Plateau;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public class BoardView extends BorderPane implements EventHandler, Observer {

    private BoardController boardController;
    private Plateau plateau;


    public BoardView(BoardController boardController, Plateau plateau) {
        this.boardController = boardController;

        this.plateau = plateau;
        plateau.addObserver(this);


        this.setTop(this.getMenuBar());
        this.setCenter(new DefaultView());
        this.getCenter().setId("GameView");
    }

    private MenuBar getMenuBar() {

        MenuBar menuBar = new MenuBar();
        Menu boardMenu = new Menu("Board");
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(this);
        boardMenu.getItems().add(quit);
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
                case "Quit":
                    Platform.exit();
                    break;
                case "Tetris":
                    this.boardController.changeGame("Tetris");
                    break;
                case "Blokus":
                    this.boardController.changeGame("Blokus");
                    break;
                case "Puzzle":
                    this.boardController.changeGame("Puzzle");
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
