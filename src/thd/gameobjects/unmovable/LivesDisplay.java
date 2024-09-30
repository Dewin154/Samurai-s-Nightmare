package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Public class that manages a lives display of the game.
 */
public class LivesDisplay extends GameObject {

    private int livesCounter;
    private final int xPositionSecondHeart;
    private final int xPositionThirdHeart;
    private final int xPositionFourthHeart;
    private final int xPositionFifthHeart;

    /**
     * Creates the lives display object on game screen.
     *
     * @param gameView        the actual game screen.
     * @param gamePlayManager manages interactions.
     */
    public LivesDisplay(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(20, 0);
        livesCounter = gamePlayManager.lives;
        imageScaleFactor = 2;
        rotation = 0;
        xPositionSecondHeart = 60;
        xPositionThirdHeart = 120;
        xPositionFourthHeart = 180;
        xPositionFifthHeart = 240;
        distanceToBackground = 4;
    }

    @Override
    public void addToCanvas() {
        if (livesCounter == 5) {
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX(), position.getY(), imageScaleFactor, rotation);
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX() + xPositionSecondHeart, position.getY(), imageScaleFactor, rotation);
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX() + xPositionThirdHeart, position.getY(), imageScaleFactor, rotation);
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX() + xPositionFourthHeart, position.getY(), imageScaleFactor, rotation);
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX() + xPositionFifthHeart, position.getY(), imageScaleFactor, rotation);
        } else if (livesCounter == 4) {
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX(), position.getY(), imageScaleFactor, rotation);
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX() + xPositionSecondHeart, position.getY(), imageScaleFactor, rotation);
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX() + xPositionThirdHeart, position.getY(), imageScaleFactor, rotation);
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX() + xPositionFourthHeart, position.getY(), imageScaleFactor, rotation);

        } else if (livesCounter == 3) {
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX(), position.getY(), imageScaleFactor, rotation);
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX() + xPositionSecondHeart, position.getY(), imageScaleFactor, rotation);
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX() + xPositionThirdHeart, position.getY(), imageScaleFactor, rotation);
        } else if (livesCounter == 2) {
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX(), position.getY(), imageScaleFactor, rotation);
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX() + xPositionSecondHeart, position.getY(), imageScaleFactor, rotation);
        } else if (livesCounter == 1) {
            gameView.addImageToCanvas("livesdisplay_heart.png", position.getX(), position.getY(), imageScaleFactor, rotation);
        }
    }

    /**
     * Public method that takes one life from counter.
     *
     * @return lives counter decreased by 1.
     */
    public int takeOneLife() {
        if (livesCounter != 0){
            return --livesCounter;
        } else {
            return 0;
        }
    }

    /**
     * Public method that adds one life to the counter.
     *
     * @return lives counter increased by 1.
     */
    public int addOneLife() {
        if (gamePlayManager.isEasyMode()) {
            if (livesCounter != 5) {
                return ++livesCounter;
            } else {
                return livesCounter;
            }
        } else {
            if (livesCounter != 3) {
                return ++livesCounter;
            } else {
                return livesCounter;
            }
        }
    }

    /**
     * Sets lives counter.
     *
     * @param livesCounter the amount to be set.
     */
    public void setLivesCounter(int livesCounter) {
        this.livesCounter = livesCounter;
    }
}