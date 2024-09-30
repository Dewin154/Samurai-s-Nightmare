package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Public class that manages a head hitbox of the enemy gotoku.
 */

public class EnemyGotokuHeadHitbox extends Hitbox implements ShiftableGameObject {

    private Hero hero;
    boolean destroyEnemyGotoku;

    /**
     * Creates a new game object that is able to collide.
     *
     * @param gameView        Window to show the game object on.
     * @param gamePlayManager Controls the game play.
     */
    public EnemyGotokuHeadHitbox(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        width = 10;
        height = 10;
        hitBoxOffsets(3, 8, 33, 10);
        destroyEnemyGotoku = false;
    }

    @Override
    public void addToCanvas() {

    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    if (other instanceof HeroFeetHitbox && !hero.isJumping && !gamePlayManager.isPlayerDead()) {
            gameView.playSound("enemy_gotoku_dying.wav", false);
            gamePlayManager.addPointsWhenEnemyKilled();
            gamePlayManager.destroyGameObject(this);
            destroyEnemyGotoku = true;
            hero.isJumping = true;
        }
    }

    @Override
    public void updatePosition() {

    }

    void passHero(Hero hero) {
        this.hero = hero;
    }


}
