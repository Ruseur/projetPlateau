/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Controleur;
import Controller.JeuController;
import Model.Plateau.Grille;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        JeuController controleurapp = new JeuController();
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Board");
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(controleurapp);
        menuFile.getItems().add(quit);
        menuBar.getMenus().add(menuFile);


        // Games Menu
        Menu gamesMenuFile = new Menu("Games");

        MenuItem blokusItem = new MenuItem("Blokus");
        // TODO Change by game's controller
        JeuController blokusController = new JeuController();
        blokusItem.setOnAction(blokusController);

        MenuItem tetrisItem = new MenuItem("Tetris");
        // TODO Change by game's controller
        JeuController tetrisController = new JeuController();
        tetrisItem.setOnAction(tetrisController);

        MenuItem puzzleItem = new MenuItem("Puzzle");
        // TODO Change by game's controller
        JeuController puzzleController = new JeuController();
        puzzleItem.setOnAction(puzzleController);

        gamesMenuFile.getItems().addAll(blokusItem,tetrisItem,puzzleItem);
        menuBar.getMenus().add(gamesMenuFile);
        
        //Initialiser la grille
        Grille grille = new Grille(5,5);
        Controleur controleur = new Controleur(this, grille);
        PanelPrincipal gridPane = new PanelPrincipal(grille, controleur);
        grille.addObserver(gridPane);
        
        borderpane.setTop(menuBar);
        borderpane.setCenter(gridPane);
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
