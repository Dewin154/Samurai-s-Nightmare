package thd.game.utilities;

import thd.game.level.Difficulty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Public class that manages the file access.
 */

public class FileAccess {
    private static final Path WICHTEL_GAME_FILE = Path.of(System.getProperty("user.home")).resolve("wichtelgame.txt");

    /**
     * Writes the difficulty on the disk.
     *
     * @param difficulty the current difficulty.
     */
    public static void writeDifficultyToDisc(Difficulty difficulty) {
        try {
            Files.writeString(WICHTEL_GAME_FILE, difficulty.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Returns the current difficulty.
     *
     * @return the current difficutly.
     */
    public static Difficulty readDifficultyFromDisc() {
        try {
            String difficultyString = Files.readString(WICHTEL_GAME_FILE);
            return switch (difficultyString) {
                case "STANDARD" -> Difficulty.STANDARD;
                case "EASY" -> Difficulty.EASY;
                default -> throw new IOException();
            };
        } catch (IOException e) {
            return Difficulty.STANDARD;
        }
    }
}