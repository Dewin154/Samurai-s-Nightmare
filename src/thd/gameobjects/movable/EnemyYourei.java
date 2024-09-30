package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.unmovable.Block;


/**
 * * Public class that manages a floating ghost enemy.
 * * This enemy moves back and forth with fast movement.
 */
public class EnemyYourei extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<Hero> {

    private final BackAndForthMovementPattern backAndForthMovementPattern;
    private boolean isGoingBack;
    private boolean canTakeLife;
    private boolean canCollideWithBlock;
    private String animatedYourei;
    private State currentState;
    private DyingStateLeft dyingStateLeft;
    private DyingStateRight dyingStateRight;

    /**
     * Creates a ghost based on the ground. This ghost moves rapidly fast.
     *
     * @param gameView        The actual game screen.
     * @param gamePlayManager manages interactions.
     * @param position        Position of the enemy to be placed on.
     */

    public EnemyYourei(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        this.position.updateCoordinates(position);
        imageScaleFactor = 1.3;
        height = 70 * imageScaleFactor;
        width = 36 * imageScaleFactor;
        speedInPixel = 9;
        rotation = 0;
        backAndForthMovementPattern = new BackAndForthMovementPattern();
        isGoingBack = false;
        hitBoxOffsets(0, 0, 0, 0);
        distanceToBackground = 2;
        canTakeLife = true;
        currentState = State.STANDARD;
        dyingStateLeft = DyingStateLeft.DYING_1;
        dyingStateRight = DyingStateRight.DYING_1;
    }

    @Override
    public void updateStatus() {
        if (!canTakeLife){
            if (gameView.timer(1000, this)) {
                canTakeLife = true;
            }
        }
        if (gameView.timer(100, this)) {
            canCollideWithBlock = true;
        }

        switch (currentState) {

            case STANDARD -> {
                if (isGoingBack) {
                    animatedYourei = "enemy_yourei_idle_looking_left.png";
                } else {
                    animatedYourei = "enemy_yourei_idle_looking_right.png";
                }
            }
            case DYING -> {
                if (isGoingBack) {
                    if (gameView.timer(100, this)) {
                        switchToNextDyingStateLeft();
                    }
                    animatedYourei = dyingStateLeft.display;
                    if (animatedYourei.equals(DyingStateLeft.DYING_4.display)) {
                        gamePlayManager.destroyGameObject(this);
                    }
                } else {
                    if (gameView.timer(100, this)) {
                        switchToNextDyingStateRight();
                    }
                    animatedYourei = dyingStateRight.display;
                    if (animatedYourei.equals(DyingStateRight.DYING_4.display)) {
                        gamePlayManager.destroyGameObject(this);
                    }
                }
            }
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(animatedYourei, position.getX(), position.getY(), imageScaleFactor, rotation);
    }

    /**
     * Prints the position of the enemy on the console.
     *
     * @return position of the object.
     */
    @Override
    public String toString() {
        return "Enemy: " + position;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof HeroShootingBullet) {
            currentState = State.DYING;
            gameView.playSound("enemy_yourei_dying.wav", false);
            gamePlayManager.addPointsWhenEnemyKilled();
        }
        if (other instanceof Hero && canTakeLife && !(gamePlayManager.isPlayerDead())) {
            gamePlayManager.lifeLost();
            gameView.playSound("hero_getting_hurt.wav", false);
            canTakeLife = false;
        }
        if (other instanceof Block && canCollideWithBlock) {
            isGoingBack = !isGoingBack;
            canCollideWithBlock = false;
        }
    }

    @Override
    public void updatePosition() {
        if (currentState.equals(State.STANDARD)) {
            backAndForthMovementPattern.movingBackAndForth(position, isGoingBack, speedInPixel);
            isGoingBack = backAndForthMovementPattern.isGoingBack();
        }
    }

    @Override
    public boolean tryToActivate(Hero player) {
        return player.getPosition().distance(this.getPosition()) <= DISTANCE_TO_ACTIVATE_ENEMIES_IN_PIXEL;
    }

    private enum State {STANDARD, DYING}

    private enum DyingStateRight {
        DYING_1("enemy_yourei_dying_1_right.png"), DYING_2("enemy_yourei_dying_2_right.png"),
        DYING_3("enemy_yourei_dying_3_right.png"), DYING_4("enemy_yourei_dying_4_right.png");
        private String display;

        DyingStateRight(String display) {
            this.display = display;
        }
    }

    private void switchToNextDyingStateRight() {
        int nextDyingRight = (dyingStateRight.ordinal() + 1) % DyingStateRight.values().length;
        dyingStateRight = DyingStateRight.values()[nextDyingRight];
    }


    private enum DyingStateLeft {
        DYING_1("enemy_yourei_dying_1_left.png"), DYING_2("enemy_yourei_dying_2_left.png"),
        DYING_3("enemy_yourei_dying_3_left.png"), DYING_4("enemy_yourei_dying_4_left.png");
        private String display;

        DyingStateLeft(String display) {
            this.display = display;
        }
    }

    private void switchToNextDyingStateLeft() {
        int nextDyingLeft = (dyingStateLeft.ordinal() + 1) % DyingStateLeft.values().length;
        dyingStateLeft = DyingStateLeft.values()[nextDyingLeft];
    }

}
