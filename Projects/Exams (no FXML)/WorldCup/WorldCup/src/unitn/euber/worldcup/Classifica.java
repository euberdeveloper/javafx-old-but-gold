/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitn.euber.worldcup;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Eugenio Vinicio Berretta
 */
public class Classifica extends BorderPane {

    private static final double ROW_SPACING = 10.0;

    private final List<Team> teams;
    private final VBox classifica = new VBox();

    Classifica(List<Team> teams) {
        this.teams = teams;
        this.initHeader();
        this.initCenter();
    }

    private void initHeader() {
        Text header = new Text("CLASSIFICA");
        this.setTop(header);
        BorderPane.setAlignment(header, Pos.CENTER);
    }
    
    private void initCenter() {
        this.classifica.setAlignment(Pos.CENTER_LEFT);
        this.setCenter(this.classifica);
        this.update();
    }

    private HBox getRow(Team team) {
        HBox row = new HBox();
        Text score = new Text("" + team.getScore());
        Text name = new Text(team.getName());
        row.getChildren().addAll(score, name/*, team.getFlag()*/);
        row.setSpacing(Classifica.ROW_SPACING);
        return row;
    }

    public final void update() {
        this.teams.sort(teams.get(0));
        this.classifica.getChildren().clear();
        this.teams.forEach((Team team) -> {
            this.classifica.getChildren().add(this.getRow(team));
        });
    }

}
