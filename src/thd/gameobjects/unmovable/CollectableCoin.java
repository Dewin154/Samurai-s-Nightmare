package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.movable.Hero;

import static thd.gameobjects.unmovable.CoinBlockImages.COIN;

/**
 * Class that manages a collectible coin.
 */
public class CollectableCoin extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<Hero> {

    private final String coin;
    private final double blockSize;
    private int outOfSightX;
    private double fallingVelocity;
    private double gravity;

    /**
     * Creates a collectible coin.
     *
     * @param gameView        The actual game screen.
     * @param gamePlayManager manages interactions.
     */
    public CollectableCoin(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        coin = COIN;
        blockSize = 2.5;
        height = 15 * blockSize;
        width = 12 * blockSize;
        rotation = 0;
        hitBoxOffsets(11, 10, -21, -4);
        distanceToBackground = 4;
        outOfSightX = -(2 * GameView.WIDTH);
        variableForTimerInMilliseconds = 20000;
        fallingVelocity = 0;
        gravity = 0.3;
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(coin, position.getX(), position.getY(), blockSize, rotation);
    }
    @Override
    public void updatePosition(){
        fallingVelocity += gravity;
        position.down(fallingVelocity);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Hero) {
            gameView.playSound("coin_collected.wav", false);
            gamePlayManager.addPointsWhenCoinCollected();
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
