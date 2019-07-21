/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitn.euber.worldcup;

import java.util.Random;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author Eugenio Vinicio Berretta
 */
public class Match extends HBox {

    private final Team firstTeam;
    private final Team secondTeam;
    private Result result;
    
    private final Text resultLabel = new Text("");

    Match(Team firstTeam, Team secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        
        Text firstT = new Text(this.firstTeam.getName());
        Text secondT = new Text(this.secondTeam.getName());
        this.getChildren().addAll(firstT, secondT, this.resultLabel);
    }

    private void updateScore() {
        if (this.result.getFirstScore() > this.result.getSecondScore()) {
            this.firstTeam.updateScore(Team.MatchResult.WIN);
            this.secondTeam.updateScore(Team.MatchResult.LOST);
        }
        else if (this.result.getFirstScore() < this.result.getSecondScore()) {
            this.firstTeam.updateScore(Team.MatchResult.LOST);
            this.secondTeam.updateScore(Team.MatchResult.WIN);
        }
        else {
            this.firstTeam.updateScore(Team.MatchResult.DRAW);
            this.secondTeam.updateScore(Team.MatchResult.DRAW);
        }
        this.resultLabel.setText(this.firstTeam.getScore() + " : " + this.secondTeam.getScore());
    }

    public boolean isPlayed() {
        return this.result != null;
    }

    public void play() {
        if (!this.isPlayed()) {
            this.result = new Result();
            this.updateScore();
        }
    }

    public Team getFirstTeam() {
        return this.firstTeam;
    }

    public Team getSecondTeam() {
        return this.secondTeam;
    }

    public class Result {

        private static final int MAX_GOALS = 3;

        private final Random random = new Random(System.currentTimeMillis());

        private final int firstScore;
        private final int secondScore;

        Result() {
            // Essendo MAX_GOALS = 3, la probabilità di pareggio è già 1 / 3
            this.firstScore = this.getGoals();
            this.secondScore = this.getGoals();
        }

        private int getGoals() {
            return this.random.nextInt(Result.MAX_GOALS);
        }

        public int getFirstScore() {
            return this.firstScore;
        }

        public int getSecondScore() {
            return this.secondScore;
        }

    }

}
