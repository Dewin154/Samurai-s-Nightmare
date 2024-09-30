package thd.gameobjects.base;

import java.util.Random;

/**
 * Public class that manages a movement pattern.
 */

public class MovementPattern {

   protected final Random random;
   protected int currentIndex;
   protected boolean isGoingBack;

    protected MovementPattern(){
        random = new Random();
        currentIndex = -1;
    }

   protected Position startPosition(Position... referencePositions) {
        return new Position();
    }

    protected Position nextTargetPosition(Position... referencePositions) {
        return new Position();
    }

    /**
     * Public method that returns if an object is going back.
     * Object is going back, if it is going from right to left.
     *
     * @return boolean value.
     */
    public boolean isGoingBack() {
        return isGoingBack;
    }
}
