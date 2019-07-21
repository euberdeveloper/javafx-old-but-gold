package unitn.euber.dicegame;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The class of the game area
 * @author Eugenio Vinicio Berretta
 */
public class GameArea extends BorderPane {

    // Static fields to set the ui
    private static final Color BACKGROUND_COLOR = Color.GREEN;
    private static final int MAX_SCORE = 30;
    private static final int WIN_TOTAL = 15;

    // The ui elements
    private final GameButtons gameButtons;
    private final GameLabels gameLabels;
    private final GameField gameField;

    // The other fields
    private int total = 0;
    private int score = GameArea.MAX_SCORE;
    private Animation animation = Animation.TRASLATE;

    /**
     * Constructor of the class GameArea
     */
    public GameArea() {
        // Inits the ui

        this.setBackground(new Background(new BackgroundFill(GameArea.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        this.gameButtons = new GameButtons(this);
        this.setLeft(this.gameButtons);
        BorderPane.setAlignment(this.gameButtons, Pos.TOP_LEFT);

        this.gameLabels = new GameLabels(this);
        this.setBottom(this.gameLabels);
        BorderPane.setAlignment(this.gameLabels, Pos.CENTER);

        this.gameField = new GameField(this);
        this.setCenter(this.gameField);
        BorderPane.setAlignment(this.gameField, Pos.CENTER);
    }

    /**
     * Starts a new game, by resetting everything as it was in the beginning
     */
    public void newGame() {
        this.gameField.reset();
        this.setTotal(0);
        this.setScore(GameArea.MAX_SCORE);
        this.setAnimation(Animation.TRASLATE);
    }

    /**
     * Shows a new window with the current game status
     */
    public void print() {
        Scene printScene = new Scene(new GameStatus(this), 200, 300);
        
        Stage printStage = new Stage();
        printStage.setTitle("STATUS");
        printStage.setScene(printScene);
        printStage.show();
    }
    
    /**
     * It shows the alert that tells the user that he has won and its score
     */
    public void weWon() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("HAI VINTO!!!!");
        alert.setHeaderText("ABBIAMO VINTOOOOOOOO");
        alert.setContentText("Hai ottenuto " + this.getScore() + " punti");
        alert.showAndWait();
    }

    /**
     * Returns the total of the dices values
     * @return the total of the dices values
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the total of the dices value
     * @param total The new total
     */
    public void setTotal(int total) {
        this.total = total;
        this.gameLabels.setTotalLabel();
    }

    /**
     * Gets the user's score
     * @return The user's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the user's score
     * @param score The new user's score
     */
    public void setScore(int score) {
        this.score = score;
        this.gameLabels.setScoreLabel();
        if (this.getTotal() == GameArea.WIN_TOTAL) {
            this.weWon();
        }
    }

    /**
     * Returns the exit animation
     * @return The exit animation
     */
    public Animation getAnimation() {
        return this.animation;
    }

    /**
     * Sets the exit animation and updates the toggle button title
     * @param animation The new exit animation
     */
    public void setAnimation(Animation animation) {
        this.animation = animation;
        this.gameButtons.updateAnimation();
    }
    
    /**
     * Returns all the dices in a List
     * @return The dices
     */
    public List<Dice> getDices() {
        return this.gameField.getDices();
    }

}
