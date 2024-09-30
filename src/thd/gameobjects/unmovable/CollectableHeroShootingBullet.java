package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.movable.Hero;

import javax.swing.*;

/**
 * Public class that manages a collectable item of shooting bullet.
 */

public class CollectableHeroShootingBullet extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<Hero> {

    private final int outOfSightX;
    private double fallingVelocity;
    private double gravity;


    /**
     * Creates a new game object that is able to collide.
     *
     * @param gameView        Window to show the game object on.
     * @param gamePlayManager Controls the game play.
     */
    public CollectableHeroShootingBullet(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        imageScaleFactor = 1.2;
        height = 23 * imageScaleFactor;
        width = 23 * imageScaleFactor;
        rotation = 0;
        hitBoxOffsets(8, 10, -15, 0);
        distanceToBackground = 4;
        outOfSightX = -(2 * GameView.WIDTH);
        variableForTimerInMilliseconds = 20000;
        fallingVelocity = 0;
        gravity = 0.3;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Hero) {
            gamePlayManager.addOneShootingBullet();
            gameView.playSound("item_collected.wav", false);
            gamePlayManager.destroyGameObject(this);
        }
        if (other instanceof Ground || other instanceof Block){
            position.up(fallingVelocity);
            fallingVelocity = 0;
        }
    }

    @Override
    public void updatePosition(){
        fallingVelocity += gravity;
        position.down(fallingVelocity);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("hero_shootingbullet_shuriken.png", position.getX(), position.getY(), imageScaleFactor, rotation);
    }

    @Override
    public boolean tryToActivate(Hero player) {
        return player.getPosition().distance(this.getPosition()) <= DISTANCE_TO_ACTIVATE_BLOCKS_IN_PIXEL;
    }

    @Override
    public void updateStatus() {
        if (position.getX() <= outOfSightX && gameView.timer(variableForTimerInMilliseconds, this)) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}
