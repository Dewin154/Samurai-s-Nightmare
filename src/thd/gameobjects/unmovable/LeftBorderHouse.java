package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Public class that manages a house as a border of the world.
 * The actual border needs to be managed in {@link LeftBorder}.
 */

public class LeftBorderHouse extends GameObject implements ShiftableGameObject {

    /**
     * Creates a new GameObject.
     *
     * @param gameView        GameView to show the game object on.
     * @param gamePlayManager manages interactions.
     */
    public LeftBorderHouse(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        imageScaleFactor = 0.6;
        rotation = 0;
        distanceToBackground = 1;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("left_border_house.png", position.getX(), position.getY(), imageScaleFactor, rotation);
    }
}
