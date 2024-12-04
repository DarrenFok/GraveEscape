package grave_escape.structure;

import java.util.List;

public class PositionUtils {
    public static <T extends Position> T isEntityAtPosition(int x, int y, List<T> entities){
        for(T entity : entities){
            if(entity.getX() == x && entity.getY() == y){
                return entity;
            }
        }
        return null;
    }

    public static boolean isWall(int x, int y, List<Wall> walls){
        if(isEntityAtPosition(x, y, walls) != null){
            return true;
        }
        return false;
    }
}
