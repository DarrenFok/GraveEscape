package grave_escape.levels;

/**
 * The GameMode enum represents the two possible game modes a player can select in the main menu. Depending on
 * the game mode selected, the game will behave differently when a level is completed, or the player dies in the level.
 * For example, in campaign mode, the player will continue to the next level if it is completed, while if the player
 * dies, the level resets, and they lose a life. In practice mode, once the level is completed, the player will return to
 * the main menu, while dying will also return them to the main menu.
 */
public enum GameMode {
    /**
     * Represents the Campaign game mode
     */
    CAMPAIGN,
    /**
     * Represents the Practice game mode
     */
    PRACTICE
}
