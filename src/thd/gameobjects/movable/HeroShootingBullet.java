package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.unmovable.Block;

/**
 * Public class that manages a shooting bullet of the hero.
 */

class HeroShootingBullet extends CollidingGameObject implements ShiftableGameObject {

    private final boolean isShootingLeft;
    private final int updateRotation;

    /**
     * Creates a shuriken that flies and rotates based on the hero's position.
     *
     * @param gameView        The actual game screen.
     * @param gamePlayManager manages interactions.
     * @param hero            The hero that serves as position reference.
     */
    HeroShootingBullet(GameView gameView, GamePlayManager gamePlayManager, Hero hero) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(hero.getPosition().getX(), hero.getPosition().getY() + 30);
        imageScaleFactor = 1;
        height = 23 * imageScaleFactor;
        width = 23 * imageScaleFactor;
        rotation = 10;
        updateRotation = 10;
        speedInPixel = 12;
        isShootingLeft = hero.isLookingLeft();
        hitBoxOffsets(0, 0, 0, 0);
        distanceToBackground = 4;
    }


    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("hero_shootingbullet_shuriken.png", position.getX(), position.getY(), imageScaleFactor, rotation);
    }

    @Override
    public void updatePosition() {
        if (isShootingLeft) {
            position.left(speedInPixel);
            rotation -= updateRotation;
        } else {
            position.right(speedInPixel);
            rotation += updateRotation;
        }
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(10000, this)) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof EnemyYourei || other instanceof EnemyOnre || other instanceof Block) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}
