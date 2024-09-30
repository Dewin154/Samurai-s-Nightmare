package thd.gameobjects.base;

/**
 * Public interface that manages an object that can be activated.
 *
 * @param <T> data type that an object manages.
 */
public interface ActivatableGameObject<T> {

    /**
     * Distance until an enemy stays inactive.
     */
    int DISTANCE_TO_ACTIVATE_ENEMIES_IN_PIXEL = 1000;

    /**
     * Distance until a block stays inactive.
     */
    int DISTANCE_TO_ACTIVATE_BLOCKS_IN_PIXEL = 2000;

    /**
     * Method that tries to activate an object that implements this method.
     *
     * @param info  can be passed if some other object should activate this object.
     * @return true, if the object should activate, otherwise false.
     */
    boolean tryToActivate(T info);

}
