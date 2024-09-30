package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;


/**
 * Public class that manages a block of dirt.
 */

public class BlockDirt extends Block {
    /**
     * Creates a new game object that is able to collide.
     *
     * @param gameView        Window to show the game object on.
     * @param gamePlayManager Controls the game play.
     * @param position        Position of the block to be placed on.
     */
    public BlockDirt(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        this.position.updateCoordinates(position);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {}

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("block_dirt.png", position.getX(), position.getY(), imageScaleFactor, rotation);
    }

}
