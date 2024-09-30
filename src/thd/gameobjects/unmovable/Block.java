package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.movable.Hero;

/**
 * Abstract class that manages a building block in the game.
 */

public abstract class Block extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<Hero> {

    private final int outOfSightX;
    /**
     * Creates a new game object that is able to collide.
     *
     * @param gameView        Window to show the game object on.
     * @param gamePlayManager Controls the game play.
     */
    public Block(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        imageScaleFactor = 0.95;
        rotation = 0;
        height = 53 * imageScaleFactor;
        width = 53 * imageScaleFactor;
        distanceToBackground = 1;
        outOfSightX = -(2 * GameView.WIDTH);
        variableForTimerInMilliseconds = 20000;
    }
    @Override
    public boolean tryToActivate(Hero player) {
        return player.getPosition().distance(this.getPosition()) <= DISTANCE_TO_ACTIVATE_BLOCKS_IN_PIXEL;
    }

    @Override
    public void updateStatus(){
        if (position.getX() <= outOfSightX && gameView.timer(variableForTimerInMilliseconds, this)){
            gamePlayManager.destroyGameObject(this);
        }
    }


}
