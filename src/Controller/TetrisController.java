package Controller;

import Model.Plateau.Grille;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.GrillePanel;
import view.TetrisPanel;

public class TetrisController extends GameController  implements EventHandler<MouseEvent> {


    public TetrisController(){

        //Initialiser la vue
        Grille grille = new Grille(10,20);
        TetrisPanel tetrisPanel = new TetrisPanel(grille);
        grille.addObserver(tetrisPanel);

        super.gamePanel = tetrisPanel;
    }


    public void start() {


    }




}
