package thd.screens;

import thd.game.level.Difficulty;
import thd.game.utilities.GameView;

/**
 * Public class that manages a start screen of the game.
 */
public class StartScreen {

    private final GameView gameView;
    private Difficulty selectedDifficulty;

    /**
     * Creates new start screen.
     *
     * @param gameView the actual game view.
     */
    public StartScreen(GameView gameView){
        this.gameView = gameView;
    }

    /**
     * Shows the startscreen.
     *
     * @param preselectedDifficulty the difficulty to be marked yellow.
     */
    public void showStartScreenWithPreselectedDifficulty(Difficulty preselectedDifficulty){
        boolean isEasy = preselectedDifficulty.equals(Difficulty.EASY);
        String title =" Samurai's Nightmare";
        String description = """
                                                          Fight your way through in your nightmares!
                \n                         Use WASD to move around and with SPACE you can shoot to kill your enemies
                \n                                                 There are 3 types of enemies:
                                                1. Enemy Yourei - Flying ghost that can be killed with shuriken
                                                2. Enemy Onre - Stationary ghost that shoots bullets at you and
                                                                needs to be shot twice to die
                                                3. Enemy Gotoku - This human enemy can not be shot at, but you can
                                                                  kill him by jumping on him
                                                    Be careful! Wooden blocks disappear if you jump on them""";

        boolean difficultyIsSetToEasy = gameView.showSimpleStartScreen(title, description, isEasy);
        if (difficultyIsSetToEasy){
            selectedDifficulty = Difficulty.EASY;
        } else {
            selectedDifficulty = Difficulty.STANDARD;
        }
    }

    /**
     * Returns the selected difficulty.
     *
     * @return the selected difficulty.
     */
    public Difficulty getSelectedDifficulty(){
        return selectedDifficulty;
    }
}
