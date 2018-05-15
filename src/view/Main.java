/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.BoardController;
import Controller.GameController;
import Controller.TetrisController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author vvhuan
 */
public class Main extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane borderpane = new BorderPane();
        
        //Initialiser le menu
        GamePanel gamePanel = new GamePanel();
        BoardController boardController = new BoardController(borderpane);
        MenuBar menuBar = new MenuBar();
        Menu boardMenu = new Menu("Board");
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(boardController);
        boardMenu.getItems().add(quit);
        menuBar.getMenus().add(boardMenu);



        // Games Menu
        Menu gamesMenuFile = new Menu("Games");

        MenuItem blokusItem = new MenuItem("Blokus");
        blokusItem.setOnAction(boardController);

        MenuItem tetrisItem = new MenuItem("Tetris");
        tetrisItem.setOnAction(boardController);

        MenuItem puzzleItem = new MenuItem("Puzzle");
        puzzleItem.setOnAction(boardController);

        gamesMenuFile.getItems().addAll(blokusItem,tetrisItem,puzzleItem);
        menuBar.getMenus().add(gamesMenuFile);
        
        //Initialiser la grille
        /*
        Grille grille = new Grille(5,5);
        GameController controleur = new GameController(this, grille);
        GridPanel gridPane = new GridPanel(grille, controleur);
        grille.addObserver(gridPane);
         */





        borderpane.setTop(menuBar);
        borderpane.setCenter(gamePanel);
        Scene scene = new Scene(borderpane, 500, 530);
        //((VBox) scene.getRoot()).getChildren().addAll(menuBar);
        primaryStage.setTitle("JavaFX / ObserverObservable / MVC example");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
