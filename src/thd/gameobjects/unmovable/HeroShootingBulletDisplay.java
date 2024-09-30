package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;

/**
 * Public class that manages a display for count of shooting bullets.
 */

public class HeroShootingBulletDisplay extends GameObject {

    private final Position counterPosition;
    private int numberOfShootingBullets;
    private final int fontSize;

    /**
     * Creates a new GameObject.
     *
     * @param gameView        GameView to show the game object on.
     * @param gamePlayManager manages interactions.
     */
    public HeroShootingBulletDisplay(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        width = 150.0;
        size = 4;
        height = 33.0;
        rotation = 0;
        imageScaleFactor = 1.5;
        fontSize = 50;
        distanceToBackground = 4;
        position.updateCoordinates(35, 80);
        counterPosition = new Position(80, 60);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("hero_shootingbullet_shuriken.png", position.getX(), position.getY(), imageScaleFactor, rotation);
        gameView.addTextToCanvas(String.valueOf(numberOfShootingBullets), counterPosition.getX(), counterPosition.getY(), fontSize, false, Color.WHITE, rotation, "pixelfontscoredisplay.ttf");
    }

    /**
     * Adds one bullet to the counter.
     *
     * @return the updated count of bullets.
     */
    public int addOneShootingBullet() {
        return ++numberOfShootingBullets;
    }

    /**
     * Takes one bullet from the counter.
     *
     * @return the updated count of bullets.
     */
    public int takeOneShootingBullet() {
        if (numberOfShootingBullets != 0) {
            return --numberOfShootingBullets;
        }
        return numberOfShootingBullets;
    }

    /**
     * Sets number of shooting bullets.
     *
     * @param numberOfShootingBullets the number to be set.
     */
    public void setNumberOfShootingBullets(int numberOfShootingBullets){
        this.numberOfShootingBullets = numberOfShootingBullets;
    }
}
