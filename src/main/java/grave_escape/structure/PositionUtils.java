package grave_escape.structure;

import java.util.List;

/**
 * This class contains functions that relate to position comparison
 */
public class PositionUtils {
    /**
     * Default constructor for PositionUtils class. This class provides static utility methods and does not
     * require instantiation.
     */
    public PositionUtils(){
        // Default constructor no initialization required
    }
    /**
     * Function that compares a position (typically the player's) to all positions of Objects of a generic type in a list,
     * to check whether the player is in the same position as any of those objects.
     *
     * @param x: X coordinate of the player
     * @param y: Y coordinate of the player
     * @param entities: The list of Objects of generic type T to be compared to
     * @return Boolean value representing whether player is currently in the same position as any of the objects in the
     *          entities list
     * @param <T>: The generic object type that the list contains
     */
    public static <T extends Position> T isEntityAtPosition(int x, int y, List<T> entities){
        for(T entity : entities){
            if(entity.getX() == x && entity.getY() == y){
                return entity;
            }
        }
        return null;
    }

    /**
     * Method that checks whether a position (typically a player's) is the same as any of the walls present.
     * @param x: X coordinate of the player
     * @param y: Y coordinate of the player
     * @param walls: List of walls
     * @return Boolean value representing whether player is in the same position as any of the walls
     */
    public static boolean isWall(int x, int y, List<Wall> walls){
        if(isEntityAtPosition(x, y, walls) != null){
            return true;
        }
        return false;
    }
}
