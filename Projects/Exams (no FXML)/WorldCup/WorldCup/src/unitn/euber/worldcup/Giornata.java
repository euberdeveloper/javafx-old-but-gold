/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitn.euber.worldcup;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Eugenio Vinicio Berretta
 */
public class Giornata extends VBox {
    
    private final Match first, second;
    private final int n;
    
    Giornata(Match first, Match second, int n) {
        this.first = first;
        this.second = second;
        this.n = n;
        this.initLayout();
    }
    
    private void initLayout() {
        Text header = new Text("== " + this.n + "giornata ===");
        this.getChildren().addAll(header, this.first, this.second);
    }
    
    public void play() {
        this.first.play();
        this.second.play();
    }
    
}
