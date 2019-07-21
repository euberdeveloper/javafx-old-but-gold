package unitn.euber.worldcup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Eugenio Vinicio Berretta
 */
public class MainView extends GridPane {

    private static final Color BACKGROUND = Color.AZURE;
    private static final double GAP = 10.0;
    private static final int SIZE = 2;

    private static final List<Team> TEAMS = Arrays.asList(new Team[]{
        new Team("Austria", new HorizontalFlag(Color.RED, Color.WHITE, Color.RED)),
        new Team("Spagna", new HorizontalFlag(Color.RED, Color.YELLOW, Color.RED)),
        new Team("Germania", new HorizontalFlag(Color.RED, Color.YELLOW, Color.BLACK)),
        new Team("Russia", new HorizontalFlag(Color.WHEAT, Color.RED, Color.BLUE)),
        new Team("Olanda", new HorizontalFlag(Color.RED, Color.WHITE, Color.BLUE)),
        new Team("Bulgaria", new HorizontalFlag(Color.WHEAT, Color.GREEN, Color.RED)),
        new Team("Ungheria", new HorizontalFlag(Color.RED, Color.WHITE, Color.GREEN)),
        new Team("Italia", new HorizontalFlag(Color.RED, Color.WHITE, Color.RED)),
        new Team("Belgio", new HorizontalFlag(Color.RED, Color.WHITE, Color.RED)),
        new Team("Irlanda", new HorizontalFlag(Color.RED, Color.YELLOW, Color.RED)),
        new Team("Francia", new HorizontalFlag(Color.RED, Color.YELLOW, Color.BLACK)),
        new Team("Svezia", new HorizontalFlag(Color.WHEAT, Color.RED, Color.BLUE)),
        new Team("Finlandia", new HorizontalFlag(Color.RED, Color.WHITE, Color.BLUE)),
        new Team("Danimarca", new HorizontalFlag(Color.WHEAT, Color.GREEN, Color.RED)),
        new Team("Polonia", new HorizontalFlag(Color.RED, Color.WHITE, Color.GREEN)),
        new Team("Ucraina", new HorizontalFlag(Color.RED, Color.WHITE, Color.RED))
    });

    private final List<Girone> gironi = new ArrayList<>();
    
    MainView() {
        this.initStyle();
        this.initGironi();
        this.initController();
    }
    
    private void initController() {
        Scene scene = new Scene(new Controller(this));
        Stage stage = new Stage();
        stage.setTitle("Controller");
        stage.setScene(scene);
        stage.show();
    }

    // Gets the size of a constraint
    private double getConstraintSize() {
        return 100.0 / (MainView.SIZE);
    }

    private void initStyle() {
        // Set background color and gap
        this.setBackground(new Background(new BackgroundFill(MainView.BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setHgap(MainView.GAP);
        this.setVgap(MainView.GAP);

        // Set column constraints
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(this.getConstraintSize());
        for (int i = 0; i < MainView.SIZE; i++) {
            this.getColumnConstraints().add(cc);
        }

        // Set row constraints
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(this.getConstraintSize());
        for (int i = 0; i < MainView.SIZE; i++) {
            this.getRowConstraints().add(rc);
        }
    }

    private void addGirone(int index, Girone girone) {
        this.add(girone, index % MainView.SIZE, index / MainView.SIZE);
    }

    private void initGironi() {
        Collections.shuffle(MainView.TEAMS);
        final int size = MainView.SIZE * MainView.SIZE;
        final int subSize = MainView.TEAMS.size() / size;
        for (int i = 0; i < size; i++) {
            Girone g = new Girone(MainView.TEAMS.subList(i * subSize, (i + 1) * subSize), (i == 1 || i == 2 ? 1 : 0));
            this.addGirone(i, g);
            this.gironi.add(g);
        }
    }
    
    public void nextGiornata() {
        this.gironi.forEach((Girone g) -> g.nextGiornata());
    }

}
