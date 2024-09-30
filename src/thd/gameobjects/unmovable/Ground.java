package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Public class that manages a ground of the game.
 */

public class Ground extends CollidingGameObject implements ShiftableGameObject {

    private final int outOfSightX;
    /**
     * Creates a ground on the bottom of the game screen.
     *
     * @param gameView        The actual game screen.
     * @param gamePlayManager manages interactions.
     */
    public Ground(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        height = 100;
        width = GameView.WIDTH;
        position.updateCoordinates(0, GameView.HEIGHT - height);
        imageScaleFactor = 1;
        rotation = 0;
        outOfSightX = -(2 * GameView.WIDTH);
        variableForTimerInMilliseconds = 20000;
        hitBoxOffsets(0, 0, 0, 0);
        distanceToBackground = 1;
    }

    @Override
    public void updateStatus(){
        if (position.getX() <= outOfSightX && gameView.timer(variableForTimerInMilliseconds, this)){
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("ground.png", position.getX(), position.getY(), imageScaleFactor, rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }
}
