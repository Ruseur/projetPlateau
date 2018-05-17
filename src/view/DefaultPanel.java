/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author vvhuan
 */
public class DefaultPanel extends GridPane{

    public DefaultPanel(){
        this.add(new Label("Hello world !"), 2, 0);
    }

}
