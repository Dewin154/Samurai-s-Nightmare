package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.unmovable.Block;
import thd.gameobjects.unmovable.Ground;

/**
 * Public class that manages a hitbox of hero's feet.
 */

public class HeroFeetHitbox extends Hitbox {

    boolean isGoingToCollide;

    /**
     * Creates a new game object that is able to collide.
     *
     * @param gameView        Window to show the game object on.
     * @param gamePlayManager Controls the game play.
     */
    public HeroFeetHitbox(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        width = 10;
        height = 10;
        hitBoxOffsets(12, 100, 29, 2);
        isGoingToCollide = false;
    }

    @Override
    public void addToCanvas() {

    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        isGoingToCollide = other instanceof Block || other instanceof Ground;
    }



}
