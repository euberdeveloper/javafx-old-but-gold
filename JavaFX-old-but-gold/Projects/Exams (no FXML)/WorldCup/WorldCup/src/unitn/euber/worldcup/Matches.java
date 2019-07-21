/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitn.euber.worldcup;

import java.util.List;
import javafx.scene.layout.VBox;

/**
 *
 * @author Eugenio Vinicio Berretta
 */
public class Matches extends VBox {

    private final List<Team> teams;
    private final Giornata[] giornate = {null, null, null};
    
    private int current = 0;

    Matches(List<Team> teams) {
        this.teams = teams;
        this.generateMatches();
    }

    private void generateMatches() {
        this.giornate[0] = new Giornata(new Match(this.teams.get(0), this.teams.get(1)), new Match(this.teams.get(2), this.teams.get(3)), 1);
        this.giornate[1] = new Giornata(new Match(this.teams.get(0), this.teams.get(2)), new Match(this.teams.get(1), this.teams.get(3)), 2);
        this.giornate[2] = new Giornata(new Match(this.teams.get(0), this.teams.get(3)), new Match(this.teams.get(1), this.teams.get(2)), 3);
        this.getChildren().addAll(this.giornate);
    }
    
    public void nextGiornata() {
        this.giornate[current++].play();
    }

}
