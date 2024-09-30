package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class BackAndForthMovementPattern extends MovementPattern {

    BackAndForthMovementPattern() {
        super();
    }

    void movingBackAndForth(Position position, boolean isGoingBack, double speedInPixel) {
        this.isGoingBack = isGoingBack;
        if (!this.isGoingBack) {
            position.right(speedInPixel);
        } else {
            position.left(speedInPixel);
        }
    }
}
