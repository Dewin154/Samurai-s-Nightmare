package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;

abstract class Hitbox extends CollidingGameObject {
    /**
     * Creates a new game object that is able to collide.
     *
     * @param gameView        Window to show the game object on.
     * @param gamePlayManager Controls the game play.
     */
    Hitbox(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
    }

    void up(double speedInPixel) {
        position.up(speedInPixel);
    }

    void down(double speedInPixel) {
        position.down(speedInPixel);
    }

    void left(double speedInPixel) {
        position.left(speedInPixel);
    }

    void right(double speedInPixel) {
        position.right(speedInPixel);
    }
}
