package thd.game.managers;

import thd.game.utilities.GameView;

/**
 * Public class that manages the game screen.
 */

public class GameViewManager extends GameView {

    private GameManager gameManager;

    @Override
    public void initialize() {
        gameManager = new GameManager(this);
        setWindowTitle("Samurai's Nightmare");
        setStatusText("Peter Okruhlica - Java Programmierung SS 2024");
        setWindowIcon("hero_shootingbullet_shuriken.png");
        gameManager.startNewGame();
    }

    @Override
    public void gameLoop() {
        gameManager.gameLoopUpdate();
    }
}
