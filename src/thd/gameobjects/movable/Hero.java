package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.MainCharacter;
import thd.gameobjects.unmovable.Block;
import thd.gameobjects.unmovable.Ground;


/**
 * Public class that manages the main character of the game.
 */
public class Hero extends CollidingGameObject implements MainCharacter{

    boolean isJumping;
    private final HeroFeetHitbox heroFeetHitbox;
    private final HeroHeadHitbox heroHeadHitbox;
    private State currentState;
    private final double gravity;
    private final double endOfJump;
    private double jumpingVelocity;
    private double fallingVelocity;
    private boolean onGround;
    private boolean isReadyToShoot;
    private boolean lookingLeft;
    private final double leftBorderForShift;
    private final double rightBorderForShift;
    private boolean playDifferentSound;
    private boolean playWalkingSound;
    private String animatedHero;
    private JumpingStateRight jumpingStateRight;
    private JumpingStateLeft jumpingStateLeft;
    private RunningRight runningRight;
    private RunningLeft runningLeft;
    private ShootingRight shootingRight;
    private ShootingLeft shootingLeft;
    private DyingLeft dyingLeft;
    private DyingRight dyingRight;
    private int runningSound;
    private boolean lockMovementAfterDying;

    /**
     * Creates a Hero object with a specified game screen.
     *
     * @param gameView        The actual game screen.
     * @param gamePlayManager manages interactions.
     * @param heroFeet        Hitbox of the hero's feet.
     * @param heroHeadHitbox  Hitbox of the hero's head.
     */

    public Hero(GameView gameView, GamePlayManager gamePlayManager, HeroFeetHitbox heroFeet, HeroHeadHitbox heroHeadHitbox) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(heroFeet.getPosition());
        this.heroFeetHitbox = heroFeet;
        this.heroHeadHitbox = heroHeadHitbox;
        speedInPixel = 8;
        rotation = 0;
        imageScaleFactor = 1.3;
        height = 81 * imageScaleFactor;
        width = 46 * imageScaleFactor;
        lookingLeft = false;
        hitBoxOffsets(12, 0, -20, 0);
        distanceToBackground = 4;
        jumpingVelocity = JUMPING_VELOCITY;
        fallingVelocity = FALLING_VELOCITY;
        gravity = 1;
        endOfJump = 0;
        onGround = true;
        isReadyToShoot = true;
        variableForTimerInMilliseconds = 750;
        leftBorderForShift = 400;
        rightBorderForShift = 680;
        animatedHero = "hero_samurai_idle_looking_right.png";
        currentState = State.STANDARD;
        jumpingStateLeft = JumpingStateLeft.JUMPING_1;
        jumpingStateRight = JumpingStateRight.JUMPING_1;
        runningLeft = RunningLeft.RUNNING_1;
        runningRight = RunningRight.RUNNING_1;
        shootingLeft = ShootingLeft.SHOOTING_1;
        shootingRight = ShootingRight.SHOOTING_1;
        dyingLeft = DyingLeft.DYING_1;
        dyingRight = DyingRight.DYING_1;
        lockMovementAfterDying = false;
    }

    /**
     * This method resets variables after hero's death in order to ensure movement.
     */
    public void resetHeroAfterDying(){
        lockMovementAfterDying = false;
        currentState = State.STANDARD;
    }


    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(animatedHero, position.getX(), position.getY(), imageScaleFactor, rotation);
    }


    @Override
    public void updatePosition() {
        if (isJumping) {
            fallingVelocity = FALLING_VELOCITY;
            heroFeetHitbox.up(jumpingVelocity);
            position.up(jumpingVelocity);
            heroHeadHitbox.up(jumpingVelocity);
            jumpingVelocity -= gravity;

            for (CollidingGameObject collidingGameObject : gamePlayManager.collidingGameObjectsForPathDecision) {
                if (collidesWith(collidingGameObject) || heroHeadHitbox.hasCollidedWithBlockCoin) {
                    ++jumpingVelocity;
                    position.down(jumpingVelocity);
                    heroFeetHitbox.down(jumpingVelocity);
                    heroHeadHitbox.down(jumpingVelocity);
                    jumpingVelocity = endOfJump;
                    heroHeadHitbox.hasCollidedWithBlockCoin = false;
                    break;
                }
            }
            if (jumpingVelocity <= endOfJump) {
                isJumping = false;
                jumpingVelocity = JUMPING_VELOCITY;
            }
        } else {
            if (heroFeetHitbox.isGoingToCollide) {
                fallingVelocity = FALLING_VELOCITY;
            } else if (fallingVelocity <= MAX_FALLING_VELOCITY) {
                fallingVelocity += gravity;
            }
            position.down(fallingVelocity);
            heroFeetHitbox.down(fallingVelocity);
            heroHeadHitbox.down(fallingVelocity);
        }
    }

    /**
     * Moves hero to the left.
     */
    public void left() {
        if (!lockMovementAfterDying) {
            if (position.getX() > leftBorderForShift) {
                position.left(speedInPixel);
                heroFeetHitbox.left(speedInPixel);
                heroHeadHitbox.left(speedInPixel);
            } else {
                gamePlayManager.moveWorldToRight(speedInPixel);
            }
            if (!isJumping && onGround && currentState != State.DYING) {
                currentState = State.RUNNING;
            }
            lookingLeft = true;

            if (playWalkingSound && onGround) {
                runningSound = gameView.playSound("hero_running.wav", false);
                playWalkingSound = false;
            }
            for (CollidingGameObject collidingGameObject : gamePlayManager.collidingGameObjectsForPathDecision) {
                if (collidesWith(collidingGameObject)) {
                    position.right(speedInPixel);
                    heroFeetHitbox.right(speedInPixel);
                    heroHeadHitbox.right(speedInPixel);
                    break;
                }
            }
        }
    }

    /**
     * Moves hero to the right.
     */
    public void right() {
        if (!lockMovementAfterDying) {
            if (position.getX() < rightBorderForShift) {
                position.right(speedInPixel);
                heroFeetHitbox.right(speedInPixel);
                heroHeadHitbox.right(speedInPixel);
            } else {
                gamePlayManager.moveWorldToLeft(speedInPixel);
            }
            if (!isJumping && onGround && currentState != State.DYING) {
                currentState = State.RUNNING;
            }
            lookingLeft = false;
            if (playWalkingSound && onGround) {
                runningSound = gameView.playSound("hero_running.wav", false);
                playWalkingSound = false;
            }
            for (CollidingGameObject collidingGameObject : gamePlayManager.collidingGameObjectsForPathDecision) {
                if (collidesWith(collidingGameObject)) {
                    position.left(speedInPixel);
                    heroFeetHitbox.left(speedInPixel);
                    heroHeadHitbox.left(speedInPixel);
                    break;
                }
            }
        }
    }

    /**
     * Lets hero jump.
     * <p>
     * {@link Hero#updatePosition()} This method handles the actual jumping.
     */
    public void jump() {
        if (!isJumping && onGround && !lockMovementAfterDying) {
            isJumping = true;
            onGround = false;
            heroFeetHitbox.isGoingToCollide = false;
            currentState = State.JUMPING;

            if (playDifferentSound) {
                gameView.playSound("hero_jump_1.wav", false);
                playDifferentSound = false;
            } else {
                gameView.playSound("hero_jump_2.wav", false);
                playDifferentSound = true;
            }
        }
    }

    @Override
    public void shoot() {
        if (gamePlayManager.checkForRemainingBullets() && isReadyToShoot && !lockMovementAfterDying) {
            isReadyToShoot = false;
            gameView.playSound("shuriken_throw.wav", false);
            currentState = State.SHOOTING;
            HeroShootingBullet heroShootingBullet = new HeroShootingBullet(gameView, gamePlayManager, this);
            gamePlayManager.spawnGameObject(heroShootingBullet);
            gamePlayManager.takeOneShootingBullet();
        }
    }

    @Override
    public void updateStatus() {
        if (!isReadyToShoot && gameView.timer(variableForTimerInMilliseconds, this)) {
            isReadyToShoot = true;
        }
        if (position.getY() >= GameView.HEIGHT) {
            gamePlayManager.lives = 0;
        }
        if (gameView.timer(700, this)) {
            playWalkingSound = true;
        }
        if (!(currentState.equals(State.RUNNING))) {
            gameView.stopSound(runningSound);
        }
        if (gamePlayManager.lives <= 0) {
            currentState = State.DYING;
        }

        switch (currentState) {

            case STANDARD -> {
                if (lookingLeft) {
                    animatedHero = "hero_samurai_idle_looking_left.png";
                } else {
                    animatedHero = "hero_samurai_idle_looking_right.png";
                }
            }
            case RUNNING -> {
                if (lookingLeft) {
                    if (gameView.timer(50, this)) {
                        switchToNextRunningLeft();
                    }
                    animatedHero = runningLeft.display;
                } else {
                    if (gameView.timer(50, this)) {
                        switchToNextRunningRight();
                    }
                    animatedHero = runningRight.display;
                }
            }
            case SHOOTING -> {
                if (lookingLeft) {
                    if (gameView.timer(100, this)) {
                        switchToNextShootingLeft();
                    }
                    animatedHero = shootingLeft.display;

                } else {
                    if (gameView.timer(100, this)) {
                        switchToNextShootingRight();
                    }
                    animatedHero = shootingRight.display;
                }
            }
            case JUMPING -> {
                if (lookingLeft && gameView.timer(120, this)) {
                    switchToNextJumpingStateLeft();
                    animatedHero = jumpingStateLeft.display;
                } else if (!lookingLeft && gameView.timer(120, this)) {
                    switchToNextJumpingStateRight();
                    animatedHero = jumpingStateRight.display;
                }
            }
            case DYING -> {
                lockMovementAfterDying = true;
                gamePlayManager.playerHasDied();
                if (lookingLeft && gameView.timer(300, this)) {
                    if (!(animatedHero.equals(DyingLeft.DYING_3.display))) {
                        switchToNextDyingLeft();
                    }
                    animatedHero = dyingLeft.display;

                } else if (!lookingLeft && gameView.timer(300, this)) {
                    if (!(animatedHero.equals(DyingRight.DYING_3.display))) {
                        switchToNextDyingRight();
                    }
                    animatedHero = dyingRight.display;
                }
            }
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if ((other instanceof Ground || other instanceof Block) && !isJumping) {
            heroFeetHitbox.up(fallingVelocity);
            position.up(fallingVelocity);
            heroHeadHitbox.up(fallingVelocity);
            onGround = true;
            fallingVelocity = FALLING_VELOCITY;
            jumpingStateRight = JumpingStateRight.JUMPING_1;
            jumpingStateLeft = JumpingStateLeft.JUMPING_1;
            if (currentState != State.SHOOTING || gameView.timer(200, this)) {
                currentState = State.STANDARD;
            }
        }
    }

    boolean isLookingLeft() {
        return lookingLeft;
    }

    private enum State {STANDARD, RUNNING, JUMPING, SHOOTING, DYING}

    private enum JumpingStateLeft {
        JUMPING_1("jumping_1_left.png"), JUMPING_2("jumping_2_left.png"), JUMPING_3("jumping_3_left.png"), JUMPING_4("jumping_4_left.png"),
        JUMPING_5("jumping_5_left.png"), JUMPING_6("jumping_6_left.png"), JUMPING_7("jumping_7_left.png"), JUMPING_8("jumping_8_left.png");
        private final String display;

        JumpingStateLeft(String display) {
            this.display = display;
        }
    }

    private void switchToNextJumpingStateLeft() {
        int nextJumpingStateLeft = (jumpingStateLeft.ordinal() + 1) % JumpingStateLeft.values().length;
        jumpingStateLeft = JumpingStateLeft.values()[nextJumpingStateLeft];
    }

    private enum JumpingStateRight {
        JUMPING_1("jumping_1_right.png"), JUMPING_2("jumping_2_right.png"), JUMPING_3("jumping_3_right.png"), JUMPING_4("jumping_4_right.png"),
        JUMPING_5("jumping_5_right.png"), JUMPING_6("jumping_6_right.png"), JUMPING_7("jumping_7_right.png"), JUMPING_8("jumping_8_right.png");
        private final String display;

        JumpingStateRight(String display) {
            this.display = display;
        }
    }

    private void switchToNextJumpingStateRight() {
        int nextJumpingStateRight = (jumpingStateRight.ordinal() + 1) % JumpingStateRight.values().length;
        jumpingStateRight = JumpingStateRight.values()[nextJumpingStateRight];
    }


    private enum RunningLeft {
        RUNNING_1("running_1_left.png"), RUNNING_2("running_2_left.png"), RUNNING_3("running_3_left.png"), RUNNING_4("running_4_left.png"),
        RUNNING_5("running_5_left.png"), RUNNING_6("running_6_left.png"), RUNNING_7("running_7_left.png"), RUNNING_8("running_8_left.png");
        private final String display;

        RunningLeft(String display) {
            this.display = display;
        }
    }

    private void switchToNextRunningLeft() {
        int nextRunningLeft = (runningLeft.ordinal() + 1) % RunningLeft.values().length;
        runningLeft = RunningLeft.values()[nextRunningLeft];
    }

    private enum RunningRight {
        RUNNING_1("running_1_right.png"), RUNNING_2("running_2_right.png"), RUNNING_3("running_3_right.png"), RUNNING_4("running_4_right.png"),
        RUNNING_5("running_5_right.png"), RUNNING_6("running_6_right.png"), RUNNING_7("running_7_right.png"), RUNNING_8("running_8_right.png");
        private final String display;

        RunningRight(String display) {
            this.display = display;
        }
    }

    private void switchToNextRunningRight() {
        int nextRunningRight = (runningRight.ordinal() + 1) % RunningRight.values().length;
        runningRight = RunningRight.values()[nextRunningRight];
    }

    private enum ShootingLeft {
        SHOOTING_1("shooting_1_left.png"), SHOOTING_2("shooting_2_left.png");
        private final String display;

        ShootingLeft(String display) {
            this.display = display;
        }
    }

    private void switchToNextShootingLeft() {
        int nextShootingLeft = (shootingLeft.ordinal() + 1) % ShootingLeft.values().length;
        shootingLeft = ShootingLeft.values()[nextShootingLeft];
    }

    private enum ShootingRight {
        SHOOTING_1("shooting_1_right.png"), SHOOTING_2("shooting_2_right.png");
        private final String display;

        ShootingRight(String display) {
            this.display = display;
        }
    }

    private void switchToNextShootingRight() {
        int nextShootingRight = (shootingRight.ordinal() + 1) % ShootingRight.values().length;
        shootingRight = ShootingRight.values()[nextShootingRight];
    }

    private enum DyingRight {
        DYING_1("hero_dying_1_right.png"), DYING_2("hero_dying_2_right.png"), DYING_3("hero_dying_3_right.png");
        private String display;

        DyingRight(String display) {
            this.display = display;
        }
    }

    private void switchToNextDyingRight() {
        int nextDyingRight = (dyingRight.ordinal() + 1) % DyingRight.values().length;
        dyingRight = DyingRight.values()[nextDyingRight];
    }

    private enum DyingLeft {
        DYING_1("hero_dying_1_left.png"), DYING_2("hero_dying_2_left.png"), DYING_3("hero_dying_3_left.png");

        private String display;

        DyingLeft(String display) {
            this.display = display;
        }
    }

    private void switchToNextDyingLeft() {
        int nextDyingLeft = (dyingLeft.ordinal() + 1) % DyingLeft.values().length;
        dyingLeft = DyingLeft.values()[nextDyingLeft];
    }

}

