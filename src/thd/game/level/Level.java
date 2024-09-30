package thd.game.level;

/**
 * Public class that manages a level.
 */
public class Level {

    /**
     * Name of the level.
     */
    public String name;

    /**
     * Number of the level.
     */
    public int number;
    /**
     * The string representation of the level.
     */
    public String world;
    /**
     * Offset of lines for the world.
     */
    public int worldOffsetLines;
    /**
     * Offset of columns for the world.
     */
    public int worldOffsetColumns;
    /**
     * File in png - format to store its background.
     */
    String background;

    /**
     * Difficulty of the level.
     */
    public static Difficulty difficulty = Difficulty.STANDARD;

}
