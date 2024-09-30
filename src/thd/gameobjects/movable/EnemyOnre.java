package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Public class that manages a standing ghost enemy.
 * This enemy is able to shoot.
 * This enemy needs to be shot twice to die.
 */

public class EnemyOnre extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<Hero> {

    private final Hero hero;
    private int numberOfLives;
    private State currentState;
    private String animatedOnre;
    private ChargingLeft chargingLeft;
    private ChargingRight chargingRight;
    private DyingLeft dyingLeft;
    private DyingRight dyingRight;

    /**
     * Creates a standing ghost on the ground.
     *
     * @param gameView        the actual game screen.
     * @param gamePlayManager manages interactions.
     * @param hero            the actual hero.
     */

    public EnemyOnre(GameView gameView, GamePlayManager gamePlayManager, Hero hero) {
        super(gameView, gamePlayManager);
        this.hero = hero;
        speedInPixel = 5;
        imageScaleFactor = 1.4;
        height = 70 * imageScaleFactor;
        width = 26 * imageScaleFactor;
        variableForTimerInMilliseconds = 2500;
        hitBoxOffsets(0, 0, 0, 0);
        distanceToBackground = 2;
        numberOfLives = 1;
        currentState = State.STANDARD;
        chargingLeft = ChargingLeft.CHARGING_1;
        chargingRight = ChargingRight.CHARGING_1;
        dyingLeft = DyingLeft.DYING_1;
        dyingRight = DyingRight.DYING_1;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(animatedOnre, position.getX(), position.getY(), imageScaleFactor, rotation);
    }

    @Override
    public void updatePosition() {
    }

    @Override
    public void updateStatus() {

        if (gameView.timer(variableForTimerInMilliseconds, this) && !(currentState.equals(State.DYING))) {
            EnemyShootingBullet enemyShootingBullet = new EnemyShootingBullet(gameView, gamePlayManager, position, hero);
            gamePlayManager.spawnGameObject(enemyShootingBullet);
            currentState = State.STANDARD;
        }
        if (gameView.timer(1600, this) && !(currentState.equals(State.DYING))) {
            currentState = State.CHARGING;
        }

        switch (currentState) {
            case STANDARD -> {
                if (isLookingLeft()) {
                    animatedOnre = "enemy_onre_idle_looking_left.png";
                } else {
                    animatedOnre = "enemy_onre_idle_looking_right.png";
                }
            }
            case CHARGING -> {
                if (isLookingLeft()) {
                    if (gameView.timer(150, this) && !(animatedOnre.equals(ChargingLeft.CHARGING_6.display))) {
                        switchToNextChargingLeft();
                    }
                    animatedOnre = chargingLeft.display;
                } else {
                    if (gameView.timer(150, this) && !(animatedOnre.equals(ChargingRight.CHARGING_6.display))) {
                        switchToNextChargingRight();
                    }
                    animatedOnre = chargingRight.display;
                }
            }
            case DYING -> {
                if (isLookingLeft()) {
                    if (gameView.timer(100, this)) {
                        switchToNextDyingLeft();
                    }
                    animatedOnre = dyingLeft.display;
                    if (animatedOnre.equals(DyingLeft.DYING_4.display)){
                        gamePlayManager.destroyGameObject(this);
                    }
                } else {
                    if (gameView.timer(100, this)) {
                        switchToNextDyingRight();
                    }
                    animatedOnre = dyingRight.display;
                    if (animatedOnre.equals(DyingRight.DYING_4.display)){
                        gamePlayManager.destroyGameObject(this);
                    }
                }

            }
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof HeroShootingBullet && numberOfLives == 0) {
            currentState = State.DYING;
            gameView.playSound("enemy_onre_dying.wav", false);
            gamePlayManager.addPointsWhenEnemyKilled();
        } else if (other instanceof HeroShootingBullet) {
            numberOfLives--;
        }

    }

    @Override
    public boolean tryToActivate(Hero player) {
        return player.getPosition().distance(this.getPosition()) <= DISTANCE_TO_ACTIVATE_ENEMIES_IN_PIXEL;
    }

    private boolean isLookingLeft() {
        return hero.getPosition().getX() <= position.getX();
    }

    private enum State {STANDARD, CHARGING, DYING}

    private enum ChargingLeft {
        CHARGING_1("enemy_onre_charge_1_left.png"), CHARGING_2("enemy_onre_charge_2_left.png"), CHARGING_3("enemy_onre_charge_3_left.png"),
        CHARGING_4("enemy_onre_charge_4_left.png"), CHARGING_5("enemy_onre_charge_5_left.png"), CHARGING_6("enemy_onre_charge_6_left.png");
        private String display;

        ChargingLeft(String display) {
            this.display = display;
        }
    }

    private void switchToNextChargingLeft() {
        int nextChargingLeft = (chargingLeft.ordinal() + 1) % ChargingLeft.values().length;
        chargingLeft = ChargingLeft.values()[nextChargingLeft];
    }

    private enum ChargingRight {
        CHARGING_1("enemy_onre_charge_1_right.png"), CHARGING_2("enemy_onre_charge_2_right.png"), CHARGING_3("enemy_onre_charge_3_right.png"),
        CHARGING_4("enemy_onre_charge_4_right.png"), CHARGING_5("enemy_onre_charge_5_right.png"), CHARGING_6("enemy_onre_charge_6_right.png");
        private String display;

        ChargingRight(String display) {
            this.display = display;
        }
    }

    private void switchToNextChargingRight() {
        int nextChargingRight = (chargingRight.ordinal() + 1) % ChargingRight.values().length;
        chargingRight = ChargingRight.values()[nextChargingRight];
    }

    private enum DyingLeft {
        DYING_1("enemy_onre_dying_1_left.png"), DYING_2("enemy_onre_dying_2_left.png"),
        DYING_3("enemy_onre_dying_3_left.png"), DYING_4("enemy_onre_dying_4_left.png");
        private String display;

        DyingLeft(String display) {
            this.display = display;
        }
    }

    private void switchToNextDyingLeft() {
        int nextDyingLeft = (dyingLeft.ordinal() + 1) % DyingLeft.values().length;
        dyingLeft = DyingLeft.values()[nextDyingLeft];
    }

    private enum DyingRight {
        DYING_1("enemy_onre_dying_1_right.png"), DYING_2("enemy_onre_dying_2_right.png"),
        DYING_3("enemy_onre_dying_3_right.png"), DYING_4("enemy_onre_dying_4_right.png");
        private String display;

        DyingRight(String display) {
            this.display = display;
        }
    }

    private void switchToNextDyingRight() {
        int nextDyingRight = (dyingRight.ordinal() + 1) % DyingRight.values().length;
        dyingRight = DyingRight.values()[nextDyingRight];
    }
}

