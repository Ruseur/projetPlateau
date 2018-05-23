/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class DefaultView extends BorderPane {

    public DefaultView(){
        this.setTop(new Label("Hello world !"));
    }

    public DefaultView(String text){
        this.setTop(new Label(text));
    }

}
