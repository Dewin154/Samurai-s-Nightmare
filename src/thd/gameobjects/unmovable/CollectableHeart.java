package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.movable.Hero;

/**
 * Class that manages a collectable heart.
 */
public class CollectableHeart extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<Hero> {

    private final String heart;
    private final int outOfSightX;
    private double fallingVelocity; //TODO Redundant
    private final double gravity;


    /**
     * Creates a collectible heart.
     *
     * @param gameView        The actual game screen.
     * @param gamePlayManager manages interactions.
     */
    public CollectableHeart(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        imageScaleFactor = 1.5;
        heart = "livesdisplay_heart.png";
        height = 15 * imageScaleFactor;
        width = 12 * imageScaleFactor;
        rotation = 0;
        hitBoxOffsets(15, 10, 0, 15);
        distanceToBackground = 4;
        outOfSightX = -(2 * GameView.WIDTH);
        variableForTimerInMilliseconds = 20000;
        fallingVelocity = 0;
        gravity = 0.3;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(heart, position.getX(), position.getY(), imageScaleFactor, rotation);
    }

    @Override
    public void updatePosition(){
        fallingVelocity += gravity;
        position.down(fallingVelocity);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Hero) {
            gamePlayManager.lifeAdded();
            gameView.playSound("item_collected.wav", false);
            gamePlayManager.destroyGameObject(this);
        }
        if (other instanceof Ground || other instanceof Block){
            position.up(fallingVelocity);
            fallingVelocity = 0;
        }
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
