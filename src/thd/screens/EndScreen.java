package thd.screens;

import thd.game.utilities.GameView;

/**
 * Public class that manages an end screen of the game.
 */
public class EndScreen {

    private final GameView gameView;

    /**
     * Creates new end screen.
     *
     * @param gameView the actual game view.
     */
    public EndScreen(GameView gameView){
        this.gameView = gameView;
    }

    /**
     * Shows end screen with points.
     *
     * @param score achieved points in game.
     */
    public void showEndScreen(int score){
        String message = "You have collected " + score + " coins!";
        gameView.showEndScreen(message);
    }
}
