package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Public class that manages a background of the game.
 */
public class Background extends GameObject {

    private int number;

    /**
     * Creates a new GameObject.
     *
     * @param gameView        GameView to show the game object on.
     * @param gamePlayManager manages interactions.
     */
    public Background(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        distanceToBackground = 0;
        imageScaleFactor = 0.7;
        rotation = 0;
    }

    @Override
    public void addToCanvas() {
        if (number == 1) {
            gameView.addImageToCanvas("game_background_1.png", position.getX(), position.getY(), imageScaleFactor, rotation);
        } else if (number == 2) {
            gameView.addImageToCanvas("game_background_2.png", position.getX(), position.getY(), imageScaleFactor, rotation);
        } else if (number == 3){
            gameView.addImageToCanvas("game_background_3.png", position.getX(), position.getY(), imageScaleFactor, rotation);
        } else if (number == 4){
            gameView.addImageToCanvas("game_background_4.png", position.getX(), position.getY(), imageScaleFactor, rotation);
        }
    }

    /**
     * Sets a number for the background based on the current level.
     *
     * @param number the number of the level.
     */
    public void setNumber(int number){
        this.number = number;
    }
}
