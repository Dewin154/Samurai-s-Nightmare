package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.HeroFeetHitbox;

/**
 * Public class that manages a block of wood.
 */

public class BlockWood extends Block {

    private boolean cooldownToSelfdestruction;


    /**
     * Creates a new game object that is able to collide.
     *
     * @param gameView        Window to show the game object on.
     * @param gamePlayManager Controls the game play.
     * @param position        Position of the block to be placed on.
     */
    public BlockWood(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        this.position.updateCoordinates(position);
        cooldownToSelfdestruction = false;
        variableForTimerInMilliseconds = 500;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof HeroFeetHitbox){
            cooldownToSelfdestruction = true;
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("block_wood.png", position.getX(), position.getY(), imageScaleFactor, rotation);
    }
    @Override
    public void updateStatus(){
        if (cooldownToSelfdestruction && gameView.timer(variableForTimerInMilliseconds, this)){
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.removeFromCollidingGameObjectsForPathDecision(this);
        }
    }

}
