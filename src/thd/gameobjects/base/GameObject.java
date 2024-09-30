package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;

import java.util.Objects;
import java.util.Random;


/**
 * Represents an object in the game.
 */
public abstract class GameObject {

    protected final GamePlayManager gamePlayManager;
    protected final GameView gameView;
    protected final Position position;
    protected final Position targetPosition;
    protected Random random;
    protected double speedInPixel;
    protected double rotation;
    protected double size;
    protected double width;
    protected double height;
    protected double imageScaleFactor;
    protected int variableForTimerInMilliseconds;
    protected char distanceToBackground;

    /**
     * Creates a new GameObject.
     *
     * @param gameView        GameView to show the game object on.
     * @param gamePlayManager manages interactions.
     */

    public GameObject(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameView = gameView;
        position = new Position();
        targetPosition = new Position();
        this.gamePlayManager = gamePlayManager;
        random = new Random();
    }

    /**
     * Updates the position of the game object.
     * If a rotation is needed, it has to be configured in this method.
     */
    public void updatePosition() {
    }

    /**
     * Draws the game object to the canvas.
     */
    public abstract void addToCanvas();


    /**
     * Returns the current position of the game object.
     *
     * @return position of the game object.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns width of game object.
     *
     * @return Width of game object
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns height of game object.
     *
     * @return Height of game object
     */
    public double getHeight() {
        return height;
    }

    /**
     * Updates a status of game object.
     */
    public void updateStatus() {
    }

    /**
     * Returns distance to background of game object.
     *
     * @return Distance to background of game object.
     */
    public char getDistanceToBackground(){
        return distanceToBackground;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameObject otherGameObject = (GameObject) o;

        return this.position.equals(otherGameObject.position)
                && Double.compare(this.width, otherGameObject.width) == 0
                && Double.compare(this.rotation, otherGameObject.rotation) == 0
                && Double.compare(this.speedInPixel, otherGameObject.speedInPixel) == 0
                && Double.compare(this.size, otherGameObject.size) == 0
                && Double.compare(this.height, otherGameObject.height) == 0
                && Double.compare(this.imageScaleFactor, otherGameObject.imageScaleFactor) == 0
                && Character.compare(this.distanceToBackground, otherGameObject.distanceToBackground) == 0
                && this.position.equals(otherGameObject.targetPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, width, rotation, speedInPixel, size, height, imageScaleFactor, targetPosition, distanceToBackground);
    }

}