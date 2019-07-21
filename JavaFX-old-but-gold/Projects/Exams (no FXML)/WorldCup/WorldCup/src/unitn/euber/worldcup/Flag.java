/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitn.euber.worldcup;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Eugenio Vinicio Berretta
 */
public abstract class Flag extends Pane {
    
    protected static final double HEIGHT = 60.0;
    protected static final double WIDTH = 30.0;
    
    Flag() {
        this.setWidth(Flag.WIDTH);
        this.setHeight(Flag.HEIGHT);
        Rectangle r = new Rectangle(60.0, 30.0);
        r.setStroke(Color.BLACK);
        this.getChildren().add(r);
    }
    
}
