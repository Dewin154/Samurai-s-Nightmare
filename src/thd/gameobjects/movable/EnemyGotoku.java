package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.unmovable.Block;
import thd.gameobjects.unmovable.Ground;

/**
 * Public class that manages a close combat enemy, that follows the player and needs to be shot 2 times to be killed.
 */
public class EnemyGotoku extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<Hero> {

    private final FollowPlayerMovementPattern followPlayerMovementPattern;
    private final EnemyGotokuHeadHitbox enemyGotokuHeadHitbox;
    private final Hero hero;
    private boolean canTakeLife;
    private final int gravity;
    private double fallingVelocity;
    private String animatedGotoku;
    private State currentState;
    private RunningStateLeft runningStateLeft;
    private RunningStateRight runningStateRight;
    private DyingState dyingState;

    /**
     * Creates an enemy of close combat enemy.
     *
     * @param gameView              the actual game screen.
     * @param gamePlayManager       manages interactions.
     * @param hero                  player to be followed.
     * @param enemyGotokuHeadHitbox the head hitbox of the enemy.
     */

    public EnemyGotoku(GameView gameView, GamePlayManager gamePlayManager, Hero hero, EnemyGotokuHeadHitbox enemyGotokuHeadHitbox) {
        super(gameView, gamePlayManager);
        this.enemyGotokuHeadHitbox = enemyGotokuHeadHitbox;
        this.enemyGotokuHeadHitbox.passHero(hero);
        this.hero = hero;
        hitBoxOffsets(4, 30, -5, -30);
        imageScaleFactor = 1.4;
        speedInPixel = 4; // Max speed 5, otherwise problems with hitbox
        height = 71 * imageScaleFactor;
        width = 33 * imageScaleFactor;
        distanceToBackground = 2;
        followPlayerMovementPattern = new FollowPlayerMovementPattern(hero, speedInPixel, this, enemyGotokuHeadHitbox);
        gravity = 1;
        fallingVelocity = 0;
        canTakeLife = true;
        currentState = State.STANDARD;
        animatedGotoku = "enemy_gotoku_idle_looking_left.png";
        runningStateLeft = RunningStateLeft.RUNNING_1;
        runningStateRight = RunningStateRight.RUNNING_1;
        dyingState = DyingState.DYING_1;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(animatedGotoku, position.getX(), position.getY(), imageScaleFactor, rotation);
    }

    @Override
    public void updatePosition() {

        if (!(currentState.equals(State.DYING))) {
            followPlayerMovementPattern.followPlayer();
        }
        for (CollidingGameObject collidingGameObject : gamePlayManager.collidingGameObjectsForPathDecision) {
            if (collidesWith(collidingGameObject) && followPlayerMovementPattern.isGoingBack()) {
                position.right(speedInPixel);
                enemyGotokuHeadHitbox.getPosition().right(speedInPixel);
            } else if (collidesWith(collidingGameObject)) {
                position.left(speedInPixel);
                enemyGotokuHeadHitbox.getPosition().left(speedInPixel);
            }
        }
        fallingVelocity += gravity;
        position.down(fallingVelocity);
        enemyGotokuHeadHitbox.down(fallingVelocity);
    }

    @Override
    public void updateStatus() {
        if (enemyGotokuHeadHitbox.destroyEnemyGotoku) {
            currentState = State.DYING;
        } else {
            if (followPlayerMovementPattern.isStanding) {
                currentState = State.STANDARD;
            } else {
                currentState = State.RUNNING;
            }
        }

        switch (currentState) {
            case STANDARD -> {
                if (followPlayerMovementPattern.isGoingBack()) {
                    animatedGotoku = "enemy_gotoku_idle_looking_left.png";
                } else {
                    animatedGotoku = "enemy_gotoku_idle_looking_right.png";
                }
            }
            case RUNNING -> {
                if (followPlayerMovementPattern.isGoingBack()) {
                    if (gameView.timer(50, this)) {
                        switchToNextRunningLeft();
                        animatedGotoku = runningStateLeft.display;
                    }
                } else if (gameView.timer(50, this)) {
                    switchToNextRunningRight();
                    animatedGotoku = runningStateRight.display;
                }
            }
            case DYING -> {
                if (gameView.timer(100, this)) {
                    switchToNextDyingState();
                    animatedGotoku = dyingState.display;
                    if (animatedGotoku.equals(DyingState.DYING_4.display)) {
                        gamePlayManager.destroyGameObject(this);
                    }
                }
            }
        }
        if (!canTakeLife){
            if (gameView.timer(1000, this)) {
                canTakeLife = true;
            }
        }

    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Hero && canTakeLife && !(gamePlayManager.isPlayerDead())) {
            gamePlayManager.lifeLost();
            gameView.playSound("hero_getting_hurt.wav", false);
            canTakeLife = false;
        }
        if (other instanceof Ground || other instanceof Block) {
            enemyGotokuHeadHitbox.up(fallingVelocity);
            position.up(fallingVelocity);
            fallingVelocity = 0;
        }
    }


    @Override
    public boolean tryToActivate(Hero player) {
        return hero.getPosition().distance(this.getPosition()) <= DISTANCE_TO_ACTIVATE_ENEMIES_IN_PIXEL;
    }

    private enum State {STANDARD, RUNNING, DYING}

    private enum RunningStateLeft {
        RUNNING_1("enemy_gotoku_run_1_left.png"), RUNNING_2("enemy_gotoku_run_2_left.png"), RUNNING_3("enemy_gotoku_run_3_left.png"),
        RUNNING_4("enemy_gotoku_run_4_left.png"), RUNNING_5("enemy_gotoku_run_5_left.png"), RUNNING_6("enemy_gotoku_run_6_left.png"), RUNNING_7("enemy_gotoku_run_7_left.png");

        private String display;

        RunningStateLeft(String display) {
            this.display = display;
        }
    }

    private void switchToNextRunningLeft() {
        int nextRunningLeft = (runningStateLeft.ordinal() + 1) % RunningStateLeft.values().length;
        runningStateLeft = RunningStateLeft.values()[nextRunningLeft];
    }

    private enum RunningStateRight {
        RUNNING_1("enemy_gotoku_run_1_right.png"), RUNNING_2("enemy_gotoku_run_2_right.png"), RUNNING_3("enemy_gotoku_run_3_right.png"),
        RUNNING_4("enemy_gotoku_run_4_right.png"), RUNNING_5("enemy_gotoku_run_5_right.png"), RUNNING_6("enemy_gotoku_run_6_right.png"), RUNNING_7("enemy_gotoku_run_7_right.png");

        private String display;

        RunningStateRight(String display) {
            this.display = display;
        }
    }

    private void switchToNextRunningRight() {
        int nextRunningRight = (runningStateRight.ordinal() + 1) % RunningStateRight.values().length;
        runningStateRight = RunningStateRight.values()[nextRunningRight];
    }

    private enum DyingState {
        DYING_1("enemy_gotoku_dying_1.png"), DYING_2("enemy_gotoku_dying_2.png"),
        DYING_3("enemy_gotoku_dying_3.png"), DYING_4("enemy_gotoku_dying_4.png");
        private String display;

        DyingState(String display) {
            this.display = display;
        }
    }

    private void switchToNextDyingState() {
        int nextDyingState = (dyingState.ordinal() + 1) % DyingState.values().length;
        dyingState = DyingState.values()[nextDyingState];
    }

}
