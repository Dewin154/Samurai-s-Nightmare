package thd.game.managers;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.utilities.FileAccess;
import thd.game.utilities.GameView;
import thd.screens.EndScreen;
import thd.screens.StartScreen;

class GameManager extends LevelManager {

    GameManager(GameView gameView) {
        super(gameView);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }

    private void gameManagement() {
        if (endOfGame()) {
            timer.stopTimer = true;
            if (!overlay.isMessageShown()) {
                overlay.showMessage("Game Over");
            }
            if (gameView.timer(2000, this)) {
                overlay.stopShowing();
                gameView.stopAllSounds();
                EndScreen endScreen = new EndScreen(gameView);
                endScreen.showEndScreen(points);
                startNewGame();
            }
        } else if (endOfLevel()) {
            timer.stopTimer = true;
            if (!overlay.isMessageShown()) {
                overlay.showMessage("Great Job!");
            }
            if (gameView.timer(2000, this)) {
                overlay.stopShowing();
                timer.stopTimer = false;
                switchToNextLevel();
                initializeLevel();
                playerHasReachedTheGoal = false;
            }
        }
    }

     void startNewGame() {
        Difficulty difficulty = FileAccess.readDifficultyFromDisc();
        StartScreen startScreen = new StartScreen(gameView);
        startScreen.showStartScreenWithPreselectedDifficulty(difficulty);
        difficulty = startScreen.getSelectedDifficulty();
        FileAccess.writeDifficultyToDisc(difficulty);
        Level.difficulty = difficulty;
        if (Level.difficulty.equals(Difficulty.EASY)) {
            lives = LIVES_EASY;
            easyMode = true;
        } else if (Level.difficulty.equals(Difficulty.STANDARD)) {
            lives = LIVES_STANDARD;
            easyMode = false;
        }
        timer.stopTimer = false;
        hero.resetHeroAfterDying();
        initializeGame();
    }

    private boolean endOfGame() {
        return lives == 0 || (!hasNextLevel() && endOfLevel()) || timer.getTimer() == 0;
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
        overlay.showMessage(level.name, 2);
    }

    @Override
    protected void initializeGame() {
        super.initializeGame();
        playerHasReachedTheGoal = false;
        playerIsDead = false;
        gameView.stopAllSounds();
        gameView.playSound("background_music.wav", true);
        initializeLevel();
    }

    private boolean endOfLevel() {
        return playerHasReachedTheGoal;
    }
}