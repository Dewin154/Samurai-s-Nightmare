package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;

import static thd.gameobjects.unmovable.CoinBlockImages.COIN;

/**
 * Public class that manages a score display on the game screen.
 */

public class ScoreDisplay extends GameObject {

    private int coinCounter;
    private Position counterPosition;
    private int fontSize;

    /**
     * Creates a score screen on the game screen with exact dimensions and position.
     *
     * @param gameView        the actual game screen.
     * @param gamePlayManager manages interactions.
     * @see Position
     */

    public ScoreDisplay(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        width = 150.0;
        size = 4;
        height = 33.0;
        rotation = 0;
        coinCounter = 0;
        fontSize = 50;
        distanceToBackground = 4;
        position.updateCoordinates(GameView.WIDTH - this.width - 8, +7);
        counterPosition = new Position(position.getX() + 50, position.getY() - 12);
    }

    /**
     * Prints the object on the game screen.
     */
    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(COIN, position.getX(), position.getY(), size, rotation);
        gameView.addTextToCanvas(convertIntCoinCounterToString(), counterPosition.getX(), counterPosition.getY(), fontSize, false, Color.ORANGE, rotation, "pixelfontscoredisplay.ttf");
    }

    private String convertIntCoinCounterToString() {
        return String.valueOf(coinCounter);
    }

    /**
     * Public method that increases a coin counter by 100.
     *
     * @return coin counter increased by 100.
     */
    public int increaseCoinCounterWhenCoinCollected() {
        coinCounter += 50;
        return coinCounter;
    }

    /**
     * Public method that increases a coin counter by 200.
     *
     * @return coin counter increased by 200;
     */
    public int increaseCoinCounterWhenEnemyKilled() {
        coinCounter += 100;
        return coinCounter;
    }

    /**
     * Sets the coin counter.
     *
     * @param coinCounter the amount to be set.
     */
    public void setCoinCounter(int coinCounter){
        this.coinCounter = coinCounter;
    }
}
