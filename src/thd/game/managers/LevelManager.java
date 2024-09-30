package thd.game.managers;

import thd.game.level.*;
import thd.game.utilities.GameView;

import java.util.List;

class LevelManager extends GameWorldManager {

    private List<Level> levels;
    private int index;
    protected static final int LIVES_EASY = 5;
    protected static final int LIVES_STANDARD = 3;

    protected LevelManager(GameView gameView) {
        super(gameView);
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
        initializeGameObjects();
    }

    protected void initializeGame(){
        levels = List.of(new Level1(), new Level2(), new Level3(), new Level4());
        index = 0;
        level = levels.get(0);
        points = 0;
        numberOfShootingBullets = 5;
    }

    protected boolean hasNextLevel() {
        return index < levels.size() - 1;
    }

    protected void switchToNextLevel() {
        if (hasNextLevel()) {
            index++;
            level = levels.get(index);
        } else {
            throw new NoMoreLevelsAvailableException("There are no more levels available");
        }
    }
    private void initializeGameObjects(){
        scoreDisplay.setCoinCounter(points);
        livesDisplay.setLivesCounter(lives);
        heroShootingBulletDisplay.setNumberOfShootingBullets(numberOfShootingBullets);
        timer.resetTimer();
    }
}
