/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitn.euber.worldcup;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Eugenio Vinicio Berretta
 */
public class HorizontalFlag extends Flag {
    
    private final Rectangle firstRectangle = new Rectangle();
    private final Rectangle secondRectangle = new Rectangle();
    private final Rectangle thirdRectangle = new Rectangle();
    
    private final Color firstColor;
    private final Color secondColor;
    private final Color thirdColor;

    public HorizontalFlag(Color firstColor, Color secondColor, Color thirdColor) {
        this.firstColor = firstColor;
        this.secondColor = secondColor;
        this.thirdColor = thirdColor;
        this.renderFlag();
    }
    
    private void initRectangle(Color color, Rectangle rectangle, int n) {
        rectangle.setFill(color);
        rectangle.setHeight(Flag.HEIGHT / 3);
        rectangle.setWidth(Flag.WIDTH);
        rectangle.setX(0);
        rectangle.setY(n * Flag.HEIGHT);
    }
    
    private void renderFlag() {
        this.initRectangle(firstColor, firstRectangle, 0);
        this.initRectangle(secondColor, secondRectangle, 1);
        this.initRectangle(thirdColor, thirdRectangle, 2);
        //this.getChildren().addAll(this.firstRectangle, this.secondRectangle, this.thirdRectangle);
    }
    
}
