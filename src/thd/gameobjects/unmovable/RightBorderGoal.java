package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.movable.Hero;

import java.awt.*;

/**
 * Public class that manages a goal border at the end of a level.
 */

public class RightBorderGoal extends CollidingGameObject implements ShiftableGameObject {

    /**
     * Creates a right border in the game world.
     *
     * @param gameView        the actual game screen.
     * @param gamePlayManager manages interactions.
     */
    public RightBorderGoal(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        height = GameView.HEIGHT;
        width = 1;
        hitBoxOffsets(0, 0, 0, 0);

    }


    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Hero) {
            gamePlayManager.playerHasReachedGoal();
        }
    }

    @Override
    public void addToCanvas() {

    }
}
