package grave_escape.game;

/**
 * The {@code Values} class contains constant values used throughout the game.
 * This includes values for collectable objectives and paths for various sprites and image assets.
 * <p>
 * This class is not meant to be instantiated, as it only holds static constants.
 * </p>
 */
public final class Values {

    // Private constructor to prevent instantiation
    private Values(){}

    // Values for collectable objectives
    /**
     * The value assigned to mandatory collectable objectives.
     */
    public static final int MANDATORY_VALUE = 25;

    /**
     * The value assigned to optional collectable objectives.
     */
    public static final int OPTIONAL_VALUE = 50;

    // Paths for sprites and other image files
    /**
     * The path to the game background image.
     */
    public static final String GAME_BACKGROUND = "/Game Background/in_game_background.png";

    /**
     * The path to the swamp terrain sprite.
     */
    public static final String SWAMP = "/Terrain/Swamp.png";

    /**
     * The path to the open door sprite.
     */
    public static final String DOOR_OPEN = "/Terrain/Door_Open.png";

    /**
     * The path to the closed door sprite.
     */
    public static final String DOOR_CLOSE = "/Terrain/Door_Close.png";

    /**
     * The path to the hero facing right sprite.
     */
    public static final String HERO_RIGHT_1 = "/Player/Hero_Right_1.png";

    /**
     * The path to the hero facing left sprite.
     */
    public static final String HERO_LEFT_1 = "/Player/Hero_Left_1.png";

    /**
     * The path to the hero facing up sprite.
     */
    public static final String HERO_UP_1 = "/Player/Hero_Up_1.png";

    /**
     * The path to the hero facing down sprite.
     */
    public static final String HERO_DOWN_1 = "/Player/Hero_Down_1.png";

    /**
     * The path to the objective key sprite.
     */
    public static final String OBJECTIVE_KEY = "/Objectives/Objective_Key.png";

    /**
     * The path to the objective coin sprite.
     */
    public static final String OBJECTIVE_COIN = "/Objectives/Coin.png";

    /**
     * The path to the thorn enemy sprite.
     */
    public static final String THORN_ENEMY = "/Enemies/Thorns.png";

    /**
     * The path to the ghost enemy facing right sprite.
     */
    public static final String GHOST_RIGHT_1 = "/Enemies/Ghost_Right_1.png";

    /**
     * The path to the ghost enemy facing left sprite.
     */
    public static final String GHOST_LEFT_1 = "/Enemies/Ghost_Left_1.png";

    /**
     * The path to the ghost enemy facing up sprite.
     */
    public static final String GHOST_UP_1 = "/Enemies/Ghost_Up_1.png";

    /**
     * The path to the ghost enemy facing down sprite.
     */
    public static final String GHOST_DOWN_1 = "/Enemies/Ghost_Down_1.png";

    /**
     * The path to the tree terrain sprite.
     */
    public static final String TREE_TERRAIN = "/Terrain/Tree.png";

    /**
     * The path to the heart UI sprite.
     */
    public static final String HEART_UI = "/In-Game UI Header/heart.png";

    /**
     * The path to the objective key UI sprite.
     */
    public static final String OBJECTIVE_KEY_UI = "/In-Game UI Header/Objective_Key_transparent.png";

    /**
     * The path to the objective coin UI sprite.
     */
    public static final String OBJECTIVE_COIN_UI = "/In-Game UI Header/Better_Coin_transparent.png";
}
