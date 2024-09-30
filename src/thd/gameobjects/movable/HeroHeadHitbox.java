package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.unmovable.BlockCoin;

/**
 * Public class that manages a hitbox of the hero's head.
 */
public class HeroHeadHitbox extends Hitbox{

    boolean hasCollidedWithBlockCoin;

    /**
     * Creates a new game object that is able to collide.
     *
     * @param gameView        Window to show the game object on.
     * @param gamePlayManager Controls the game play.
     */
    public HeroHeadHitbox(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        width = 10;
        height = 10;
        hitBoxOffsets(14, -14, 25, 0);
        hasCollidedWithBlockCoin = false;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof BlockCoin){
            hasCollidedWithBlockCoin = true;
        }
    }

    @Override
    public void addToCanvas() {

    }
}
