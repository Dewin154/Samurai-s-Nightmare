package thd.gameobjects.base;

/**
 * Public interface to manage a main character of the game.
 */
public interface MainCharacter {

    /**
     * Constant of how strong the jumping velocity should be.
     */
    double JUMPING_VELOCITY = 22;
    /**
     * Constant of how strong the falling velocity should be.
     */
    double FALLING_VELOCITY = 1;

    /**
     * Maximum a main character can fall.
     */
    double MAX_FALLING_VELOCITY = 20;
    /**
     * Makes the hero shoot a bullet.
     */
    void shoot();
}
