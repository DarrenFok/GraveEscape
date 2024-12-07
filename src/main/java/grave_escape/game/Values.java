package grave_escape.game;

/**
 * A utility class that holds constant values for the application.
 * <p>
 * This class is designed to be used as a container for constant values
 * and does not allow instantiation. The private constructor ensures that
 * instances of this class cannot be created.
 * </p>
 */
public final class Values {
    /**
     * Private constructor to prevent instantiation of the Values class.
     * This ensures that this class cannot be instantiated, as it is meant to
     * only hold constant values.
     */
    private Values(){
        // Private constructor to prevent instantiation
    }

    // Values for collectable objectives
    /**
     * The amount of points obtained when collecting a mandatory objective
     */
    public static final int MANDATORY_VALUE = 25;
    /**
     * The amount of points obtained when collecting an optional value
     */
    public static final int OPTIONAL_VALUE = 50;

    // Paths for sprites and other image files
    /**
     * The path for the .png file for the in-game background
     */
    public static final String GAME_BACKGROUND = "/Game Background/in_game_background.png";
    /**
     * The path for the .png file for the swamp terrain tile sprite
     */
    public static final String SWAMP = "/Terrain/Swamp.png";
    /**
     * The path for the .png file for the open-door tile sprite
     */
    public static final String DOOR_OPEN = "/Terrain/Door_Open.png";
    /**
     * The path for the .png file for the closed-door tile sprite
     */
    public static final String DOOR_CLOSE = "/Terrain/Door_Close.png";

    /**
     * The path for the .png file for the hero looking right sprite
     */
    public static final String HERO_RIGHT_1 = "/Player/Hero_Right_1.png";
    /**
     * The path for the .png file for the hero looking left sprite
     */
    public static final String HERO_LEFT_1 = "/Player/Hero_Left_1.png";
    /**
     * The path for the .png file for the hero looking up sprite
     */
    public static final String HERO_UP_1 = "/Player/Hero_Up_1.png";
    /**
     * The path for the .png file for the hero looking down sprite
     */
    public static final String HERO_DOWN_1 = "/Player/Hero_Down_1.png";

    /**
     * The path for the .png file for the mandatory key objective sprite
     */
    public static final String OBJECTIVE_KEY = "/Objectives/Objective_Key.png";
    /**
     * The path for the .png file for the optional coin objective sprite
     */
    public static final String OBJECTIVE_COIN = "/Objectives/Coin.png";

    /**
     * The path for the .png file for the thorn enemy tile sprite
     */
    public static final String THORN_ENEMY = "/Enemies/Thorns.png";

    /**
     * The path for the .png file for the enemy looking right sprite
     */
    public static final String GHOST_RIGHT_1 = "/Enemies/Ghost_Right_1.png";
    /**
     * The path for the .png file for the hero looking left sprite
     */
    public static final String GHOST_LEFT_1 = "/Enemies/Ghost_Left_1.png";
    /**
     * The path for the .png file for the hero looking up sprite
     */
    public static final String GHOST_UP_1 = "/Enemies/Ghost_Up_1.png";
    /**
     * The path for the .png file for the hero looking down sprite
     */
    public static final String GHOST_DOWN_1 = "/Enemies/Ghost_Down_1.png";

    /**
     * The path for the .png file for the tree terrain tile sprite
     */
    public static final String TREE_TERRAIN = "/Terrain/Tree.png";

    /**
     * The path for the .png file for heart icon in the header UI representing lives left
     */
    public static final String HEART_UI = "/In-Game UI Header/heart.png";
    /**
     * The path for the .png file for the mandatory key objective icon in the header UI representing the number of
     * mandatory objectives left
     */
    public static final String OBJECTIVE_KEY_UI = "/In-Game UI Header/Objective_Key_transparent.png";
    /**
     * The path for the .png file for the optional coin objective icon in the header UI representing the number of
     * optional objectives left
     */
    public static final String OBJECTIVE_COIN_UI = "/In-Game UI Header/Better_Coin_transparent.png";
}
