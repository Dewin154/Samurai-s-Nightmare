package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * Public class that manages a timer display of the game.
 */

public class GameTimer extends GameObject {

    private int timer;

    /**
     * Variable to control the timer.
     */
    public boolean stopTimer;

    /**
     * Creates the timer object on game screen.
     *
     * @param gameView the actual game screen.
     * @param gamePlayManager manages interactions.
     */

    public GameTimer(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        timer = 300;
        stopTimer = false;
        position.updateCoordinates(600, 0);
        distanceToBackground = 4;
    }

    /**
     * Resets the timer to 300.
     */
    public void resetTimer(){
        timer = 300;
    }

    /**
     * Returns the current timer value.
     *
     * @return the current timer value.
     */
    public int getTimer(){
        return timer;
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(1000, this) && timer > 0 && !stopTimer){
            timer--;
        }
    }

    /**
     * Adds the timer to the game screen.
     */
    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("Time: " + timer, position.getX(), position.getY(), 40, false, Color.WHITE, 0, "pixelfontscoredisplay.ttf");
    }
}
