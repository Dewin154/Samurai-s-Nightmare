package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Public class that manages a left border of the game world.
 * Hero can´t go further through this border.
 */

public class LeftBorder extends CollidingGameObject implements ShiftableGameObject {

    /**
     * Creates a left border in the game world.
     *
     * @param gameView        the actual game screen.
     * @param gamePlayManager manages interactions.
     */
    public LeftBorder(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        height = GameView.HEIGHT;
        width = 1;
        hitBoxOffsets(0, 0, 0, 0);
    }


    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

    }

    @Override
    public void addToCanvas() {

    }
}
