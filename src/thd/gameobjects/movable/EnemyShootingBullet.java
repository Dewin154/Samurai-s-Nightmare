package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.unmovable.Block;
import thd.gameobjects.unmovable.Ground;

class EnemyShootingBullet extends CollidingGameObject implements ShiftableGameObject {

    private final Hero hero;
    private final Position positionOfHeroToBeShotAt;
    private double directionX;
    private double directionY;
    private final int factor;

    /**
     * Creates an enemy bullet.
     *
     * @param gameView          the actual game screen.
     * @param gamePlayManager   manages interactions.
     * @param enemyOnrePosition Position of the enemy onre.
     * @param hero              The player that should be shot at.
     */
    EnemyShootingBullet(GameView gameView, GamePlayManager gamePlayManager, Position enemyOnrePosition, Hero hero) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(enemyOnrePosition);
        position.updateCoordinates(getPosition().getX(), getPosition().getY() + 30);
        imageScaleFactor = 1.5;
        height = 8 * imageScaleFactor;
        width = 14 * imageScaleFactor;
        speedInPixel = 15;
        factor = 17;
        hitBoxOffsets(0, 0, 0, 0);
        distanceToBackground = 2;
        this.hero = hero;
        positionOfHeroToBeShotAt = new Position(hero.getPosition().getX() + 30, hero.getPosition().getY() + 40);
        calculateDeltaOfTrajectory();
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("enemy_shootingbullet_onre.png", position.getX(), position.getY(), imageScaleFactor, rotation);
    }

    @Override
    public void updatePosition() {
        position.flyOnTrajectory(directionX, directionY, speedInPixel);
    }


    @Override
    public void updateStatus() {
        if (gameView.timer(10000, this)) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Hero && !(gamePlayManager.isPlayerDead())) {
            gamePlayManager.lifeLost();
            gameView.playSound("hero_getting_hurt.wav", false);
            gamePlayManager.destroyGameObject(this);
        } else if (other instanceof Block || other instanceof Ground) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    private void calculateDeltaOfTrajectory() {
        double deltaY;
        double deltaX;
        deltaY = position.getY() - positionOfHeroToBeShotAt.getY();
        deltaX = position.getX() - positionOfHeroToBeShotAt.getX();
        normalizeVector(deltaX, deltaY);
    }

    private void normalizeVector(double deltaX, double deltaY) {
        double lengthOfVector = Math.pow(deltaX, 2) + Math.pow(deltaY, 2);
        lengthOfVector = Math.sqrt(lengthOfVector);
        directionX = deltaX / lengthOfVector;
        directionY = deltaY / lengthOfVector;
        rotation = Math.atan2(deltaY, deltaX);
        rotation = Math.toDegrees(rotation);
    }
}

