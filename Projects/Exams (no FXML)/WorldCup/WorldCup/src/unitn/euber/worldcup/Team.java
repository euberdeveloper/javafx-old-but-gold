/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitn.euber.worldcup;

import java.util.Comparator;

/**
 *
 * @author Eugenio Vinicio Berretta
 */
public class Team implements Comparator<Team> {
    
    private final String name;
    private final Flag flag;
    private int score;
    
    Team(String name, Flag flag) {
        this.name = name;
        this.flag = flag;
        this.score = 0;
    }

    public String getName() {
        return this.name;
    }

    public Flag getFlag() {
        return this.flag;
    }

    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    
    public void updateScore(Team.MatchResult draw) {
        switch (draw) {
            case LOST:
                break;
            case DRAW:
                this.setScore(this.score + 1);
                break;
            case WIN:
                this.setScore(this.score + 3);
        }
    }

    @Override
    public int compare(Team x, Team y) {
        if (x == null && y == null) {
            return 0;
        }
        else if (x == null && y != null) {
            return -1;
        }
        else if (x != null && y == null) {
            return 1;
        }
        else {
            final int s = x.getScore() - y.getScore();
            return score == 0 ? x.getName().compareTo(y.getName()) : score;
        }
    }
    
    public static enum MatchResult {
        WIN, LOST, DRAW
    }
    
}
