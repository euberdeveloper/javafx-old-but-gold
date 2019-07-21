package unitn.euber.dicegame;

import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Class of a dice
 * @author Eugenio Vinicio Berretta
 */
public final class Dice extends StackPane implements EventHandler<MouseEvent> {

    // Static fields for ui and settings
    private static final Color BACKGROUND_COLOR = Color.BEIGE;
    private static final double SIZE = 80.0;
    private static final int DECREMENT_SCORE = 1;
    private static final Duration TRANSITION_DURATION = Duration.millis(800);

    // Parent instance
    private final GameField parent;
    // Random generator
    private final Random random = new Random();
    // Text of the dice
    private final Text label = new Text();

    // Index
    private final int index;
    // Value
    private int value;

    /**
     * Constructor of the class Dice
     * @param parent the parent instance
     * @param index the index of the dice
     */
    public Dice(GameField parent, int index) {
        this.parent = parent;
        this.index = index;
        this.setValue(this.randomValue());
        this.setPadding(new Insets(Dice.SIZE));
        this.getChildren().add(this.label);
        this.setBackground(new Background(new BackgroundFill(Dice.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setOnMouseClicked(this);
    }

    // Gets a random value for the dice
    private int randomValue() {
        return random.nextInt(6) + 1;
    }

    // Tosses the dice (sets the value to a new random value)
    private void toss() {
        this.setValue(this.randomValue());
    }

    // Calls the method decrementScore in the parent instance
    private void decrementScore() {
        this.parent.decrementScore(Dice.DECREMENT_SCORE);
    }

    // Gets the right transition
    private Transition getTransition() {
        Transition transition = null;
        System.out.println(this.parent.getAnimation().name());
        switch (this.parent.getAnimation()) {
            case TRASLATE:
                // Creates the traslate transition
                TranslateTransition traslate = new TranslateTransition(Dice.TRANSITION_DURATION, this);
                traslate.setByX(500);
                traslate.setByY(500);
                traslate.setAutoReverse(false);
                traslate.setOnFinished(event -> this.parent.removeDice(this));
                transition = traslate;
                break;
            case DISSOLVE:
                // Creates the dissolve transition
                FadeTransition fade = new FadeTransition(Dice.TRANSITION_DURATION, this);
                fade.setByValue(1);
                fade.setToValue(0);
                fade.setAutoReverse(false);
                fade.setOnFinished(event -> this.parent.removeDice(this));
                transition = fade;
                break;
        }
        return transition;
    }

    /**
     * Returns the index of the dice
     * @return The index of the dice
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Returns the value of the dice
     * @return The value of the dice
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Set the value of the dice, updating the layout
     * @param value The new value of the dice
     */
    public void setValue(int value) {
        this.value = value;
        this.label.setText("" + this.value);
    }

    /**
     * Removes the dice from its parent with the right animation
     */
    public void exit() {
        this.getTransition().play();
    }

    /**
     * Overriding of the handle method. It executes a toss and updates everything.
     */
    @Override
    public void handle(MouseEvent event) {
        if (!this.parent.isFinished()) {
            this.toss();
            this.parent.calcTotal();
            this.decrementScore();
            event.consume();
        }
    }

}
