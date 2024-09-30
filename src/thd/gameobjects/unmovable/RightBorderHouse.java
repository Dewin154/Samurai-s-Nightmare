package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Public class that manages a house at the end of the level.
 */

public class RightBorderHouse extends GameObject implements ShiftableGameObject {
    /**
     * Creates a new GameObject.
     *
     * @param gameView        GameView to show the game object on.
     * @param gamePlayManager manages interactions.
     */
    public RightBorderHouse(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        imageScaleFactor = 1.4;
        rotation = 0;
        distanceToBackground = 1;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("right_border_house.png", position.getX(), position.getY(), imageScaleFactor, rotation);
    }
}
