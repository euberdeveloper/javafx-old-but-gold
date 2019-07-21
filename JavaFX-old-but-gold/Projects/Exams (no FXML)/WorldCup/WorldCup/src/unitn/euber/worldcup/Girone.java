/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitn.euber.worldcup;

import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 *
 * @author Eugenio Vinicio Berretta
 */
public class Girone extends BorderPane {
    
    private final static Color[] BACKGROUND_COLORS = { Color.WHITE, Color.YELLOW };
    
    private final List<Team> teams;
    private Classifica classifica;
    private Matches matches;
    
    Girone(List<Team> teams, int color) {
        this.teams = teams;
        this.setBackground(new Background(new BackgroundFill(Girone.BACKGROUND_COLORS[color], CornerRadii.EMPTY, Insets.EMPTY)));
        this.initTop();
        this.initBottom();
    }
    
    private void initTop() {
        this.matches = new Matches(this.teams);
        this.setTop(this.matches);
    }
    
    private void initBottom() {
        this.classifica = new Classifica(this.teams);
        this.setBottom(this.classifica);
    }
    
    public void nextGiornata() {
        this.matches.nextGiornata();
        this.classifica.update();
    }
}
