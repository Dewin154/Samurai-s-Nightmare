package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.GameObject;

import java.util.LinkedList;

/**
 * Public class that manages game objects.
 * It manages their creation and destruction.
 */
public class GamePlayManager extends WorldShiftManager {

    protected boolean playerHasReachedTheGoal;
    protected boolean playerIsDead;

    /**
     * Public list of all objects for path decision. Game Objects can interact with this list.
     */
    public final LinkedList<CollidingGameObject> collidingGameObjectsForPathDecision;
    /**
     * Public variable of lives of hero.
     */
    public int lives;
    private final GameObjectManager gameObjectManager;
    protected int points;
    protected int numberOfShootingBullets;
    protected boolean easyMode;

    protected GamePlayManager(GameView gameView) {
        super(gameView);
        gameObjectManager = new GameObjectManager();
        collidingGameObjectsForPathDecision = new LinkedList<>();
        playerHasReachedTheGoal = false;
        playerIsDead = false;
    }

    /**
     * Creates a game object.
     *
     * @param gameObject Object to be created.
     */
    @Override
    public void spawnGameObject(GameObject gameObject) {
        super.spawnGameObject(gameObject);
        gameObjectManager.add(gameObject);
    }

    /**
     * Destroys a game object.
     *
     * @param gameObject Object to be destroyed.
     */
    @Override
    public void destroyGameObject(GameObject gameObject) {
        super.destroyGameObject(gameObject);
        gameObjectManager.remove(gameObject);
    }

    @Override
    protected void destroyAllGameObjects() {
        super.destroyAllGameObjects();
        gameObjectManager.removeAll();
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameObjectManager.gameLoopUpdate();
        gamePlayManagement();
    }

    private void gamePlayManagement() {
        livesDisplay.setLivesCounter(lives);
    }

    /**
     * Ends the level when the player has reached the end.
     */
    public void playerHasReachedGoal() {
        playerHasReachedTheGoal = true;
    }


    /**
     * Sets a flag that indicates a player has died.
     */
    public void playerHasDied(){
        playerIsDead = true;
    }

    /**
     * Returns if player has died.
     *
     * @return true if player has died, otherwise false.
     */
    public boolean isPlayerDead(){
        return playerIsDead;
    }

    /**
     * Adds points to the counter when enemy is killed.
     */
    public void addPointsWhenEnemyKilled() {
        points = scoreDisplay.increaseCoinCounterWhenEnemyKilled();
    }

    /**
     * Adds points to the counter when coin is collected.
     */
    public void addPointsWhenCoinCollected() {
        points = scoreDisplay.increaseCoinCounterWhenCoinCollected();
    }

    /**
     * Subtracts one life from counter.
     */
    public void lifeLost() {
        lives = livesDisplay.takeOneLife();
    }

    /**
     * Adds one life from counter.
     */
    public void lifeAdded() {
        lives = livesDisplay.addOneLife();
    }

    /**
     * Adds one bullet to the counter.
     */
    public void addOneShootingBullet() {
        numberOfShootingBullets = heroShootingBulletDisplay.addOneShootingBullet();
    }

    /**
     * Takes one bullet from the counter.
     */
    public void takeOneShootingBullet() {
        numberOfShootingBullets = heroShootingBulletDisplay.takeOneShootingBullet();
    }

    /**
     * Checks if the number of bullets is above zero.
     *
     * @return Returns true if there are still bullets to fire, otherwise false.
     */
    public boolean checkForRemainingBullets() {
        return numberOfShootingBullets != 0;
    }

    /**
     * This method removes an object that objects can collide from the list.
     *
     * @param other object to be removed.
     */
    public void removeFromCollidingGameObjectsForPathDecision(CollidingGameObject other) {
        collidingGameObjectsForPathDecision.remove(other);
    }


    /**
     * Returns if game is in easy mode.
     *
     * @return true if game is easy, otherwise false.
     */
    public boolean isEasyMode() {
        return easyMode;
    }

}
