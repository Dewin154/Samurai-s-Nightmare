package thd.game.managers;

import thd.game.level.Level;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

import java.awt.event.KeyEvent;

class UserControlledGameObjectPool {

    protected final GameView gameView;
    protected Level level;
    protected ScoreDisplay scoreDisplay;
    protected LivesDisplay livesDisplay;
    protected GameTimer timer;
    protected HeroShootingBulletDisplay heroShootingBulletDisplay;
    protected Background background;
    protected Hero hero;
    protected HeroFeetHitbox heroFeetHitbox;
    protected HeroHeadHitbox heroHeadHitbox;
    protected EnemyGotokuHeadHitbox enemyGotokuHeadHitbox;
    protected Overlay overlay;
    private boolean cooldownToJump;

    UserControlledGameObjectPool(GameView gameView) {
        this.gameView = gameView;
    }

    protected void gameLoopUpdate() {
        Integer[] pressedKeys = gameView.keyCodesOfCurrentlyPressedKeys();
        for (int keyCode : pressedKeys) {
            processKeyCode(keyCode);
        }
        if (gameView.timer(75, this)){
            cooldownToJump = true;
        }
    }

    private void processKeyCode(int keyCode) {
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            hero.left();
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            hero.right();
        } else if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            if (cooldownToJump) {
                hero.jump();
                cooldownToJump = false;
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {
            hero.shoot();
        }
    }
}
