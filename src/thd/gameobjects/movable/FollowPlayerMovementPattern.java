package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;

class FollowPlayerMovementPattern extends MovementPattern {

    boolean isStanding;
    private final Hero hero;
    private final EnemyGotoku enemyGotoku;
    private final double speedInPixel;
    private final EnemyGotokuHeadHitbox enemyGotokuFeetHitbox;

    FollowPlayerMovementPattern(Hero hero, double speedInPixel, EnemyGotoku enemyGotoku, EnemyGotokuHeadHitbox enemyGotokuFeetHitbox) {
        super();
        this.hero = hero;
        this.speedInPixel = speedInPixel;
        this.enemyGotoku = enemyGotoku;
        this.enemyGotokuFeetHitbox = enemyGotokuFeetHitbox;
    }

    void followPlayer() {
        int tolerance = 5;
        if (Math.abs(enemyGotoku.getPosition().getX() - hero.getPosition().getX()) > tolerance) {
            if (hero.getPosition().getX() < enemyGotoku.getPosition().getX()) {
                enemyGotoku.getPosition().left(speedInPixel);
                enemyGotokuFeetHitbox.getPosition().left(speedInPixel);
            } else {
                enemyGotoku.getPosition().right(speedInPixel);
                enemyGotokuFeetHitbox.getPosition().right(speedInPixel);
            }
            isStanding = false;
        } else {
            isStanding = true;
        }
        isGoingBack = (int) enemyGotoku.getPosition().getX() >= (int) hero.getPosition().getX();
    }
}