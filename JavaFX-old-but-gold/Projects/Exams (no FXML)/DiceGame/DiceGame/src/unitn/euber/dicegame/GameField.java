package unitn.euber.dicegame;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

/**
 * The class of the game field
 * @author Eugenio Vinicio Berretta
 */
public final class GameField extends TilePane implements EventHandler<MouseEvent> {

    /**
     * The maximum number of dices.
     */
    public static final int MAX_DICES = 5;

    // Static fields for the ui and settings
    private static final double SPACING = 15.0;
    private static final int DECREMENT_SCORE = 3;

    // The parent instance
    private final GameArea parent;
    // The dices
    private final List<Dice> dices = new ArrayList<>();

    /**
     * Constructor of the class GameField
     */
    public GameField(GameArea parent) {
        this.parent = parent;
        this.setPrefColumns(GameField.MAX_DICES);
        this.setHgap(GameField.SPACING);
        this.setVgap(GameField.SPACING);
        this.setAlignment(Pos.CENTER);
        this.setOnMouseClicked(this);
        this.calcTotal();
    }

    // Returns the number of dices
    private int size() {
        return this.dices.size();
    }

    // Returns true if the maximum number of dices is reached, false otherwise
    private boolean isFull() {
        return this.size() >= GameField.MAX_DICES;
    }

    // Updates the ui
    private void update() {
        this.getChildren().clear();
        this.getChildren().addAll(this.dices);
    }

    // Calls the method decrementScore on the parent instance
    private void decrementScore() {
        this.decrementScore(GameField.DECREMENT_SCORE);
    }

    /**
     * Decrements the score by n
     * @param n The number subtracted to the score
     */
    public void decrementScore(int n) {
        this.parent.setScore(this.parent.getScore() - n);
    }
    
    /**
     * Returns the exit animation
     * @return The exit animation
     */
    public Animation getAnimation() {
        return this.parent.getAnimation();
    }

    /**
     * Returns true if the game is finished
     * @return True if the game is finished
     */
    public boolean isFinished() {
        return this.parent.getScore() <= 0;
    }
    
    /**
     * Removes the given dice from the layout
     * @param dice The dice to remove
     */
    public void removeDice(Dice dice) {
        this.dices.remove(dice);
    }

    /**
     * Removes all the dices
     */
    public void reset() {
        this.dices.forEach((Dice dice) -> dice.exit());
        this.update();
    }

    /**
     * Set the total, calculating it by summing the dices value
     */
    public void calcTotal() {
        int total = 0;
        total = this.dices.stream()
                .map((dice) -> dice.getValue())
                .reduce(total, Integer::sum);
        this.parent.setTotal(total);
    }

    /**
     * Returns all the dices
     * @return All the dices
     */
    public List<Dice> getDices() {
        return this.dices;
    }
    
    /**
     * Overriding of the method handle. Adds a dice if possible.
     */
    @Override
    public void handle(MouseEvent event) {
        if (!this.isFull() && !this.isFinished()) {
            this.dices.add(new Dice(this, this.size()));
            this.calcTotal();
            this.decrementScore();
            this.update();
        }
    }

}
