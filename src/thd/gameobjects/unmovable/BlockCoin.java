package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.HeroHeadHitbox;


/**
 * Public class that manages a block of coin.
 */
public class BlockCoin extends Block{
    /**
     * Creates a new game object that is able to collide.
     *
     * @param gameView        Window to show the game object on.
     * @param gamePlayManager Controls the game play.
     * @param position        A position on which a block of coin should be spawned at.
     */
    public BlockCoin(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        this.position.updateCoordinates(position);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof HeroHeadHitbox){
            int randomNumber = random.nextInt(11); //Random number between 0-10
            gameView.playSound("coin_collected.wav", false);
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.removeFromCollidingGameObjectsForPathDecision(this);
            if (randomNumber <= 5){
                CollectableCoin coin = new CollectableCoin(gameView, gamePlayManager);
                coin.getPosition().updateCoordinates(position.getX(), position.getY() - 10);
                gamePlayManager.spawnGameObject(coin);
            } else if (randomNumber >= 6 && randomNumber <= 8){
                CollectableHeroShootingBullet shuriken = new CollectableHeroShootingBullet(gameView, gamePlayManager);
                shuriken.getPosition().updateCoordinates(position.getX(), position.getY() - 10);
                gamePlayManager.spawnGameObject(shuriken);
            } else if (randomNumber >= 9){
                CollectableHeart heart = new CollectableHeart(gameView, gamePlayManager);
                heart.getPosition().updateCoordinates(position.getX(), position.getY() - 10);
                gamePlayManager.spawnGameObject(heart);
            }
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("block_coin.png", position.getX(), position.getY(), imageScaleFactor, rotation);
    }
}
