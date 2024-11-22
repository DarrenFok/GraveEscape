package levels;

import grave_escape.enemy.MovingEnemy;
import grave_escape.enemy.StationaryEnemy;
import grave_escape.levels.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelInitializationTest {
    private Level level;

    @Test
    public void testLevelOneEasy(){
        level = new Level1Easy();
        int stationaryEnemies = 0;
        int movingEnemies = 0;
        for(int i = 0; i < level.getEnemies().size(); i++){
            if(level.getEnemies().get(i) instanceof StationaryEnemy){
                stationaryEnemies += 1;
            }
            else if(level.getEnemies().get(i) instanceof MovingEnemy){
                movingEnemies += 1;
            }
        }
        int totalEnemies = stationaryEnemies + movingEnemies;
        int numObj = level.getObjectives().size();
        int numWalls = level.getWalls().size();
        String levelName = level.getLevelName();
        String difficulty = level.getDifficulty();

        assertEquals(2, movingEnemies, "The number of moving enemies is unexpected");
        assertEquals(15, stationaryEnemies, "The number of stationary enemies is unexpected");
        assertEquals(17, totalEnemies, "The total number of enemies is unexpected");
        assertEquals(3, numObj, "The number of objectives is unexpected");
        assertEquals(117, numWalls, "The number of walls is unexpected");
        assertEquals("Level 1", levelName, "The level name is unexpected");
        assertEquals("Easy", difficulty, "The difficulty is unexpected");
    }

    @Test
    public void testLevelOneNormal(){
        level = new Level1Normal();
        int stationaryEnemies = 0;
        int movingEnemies = 0;
        for(int i = 0; i < level.getEnemies().size(); i++){
            if(level.getEnemies().get(i) instanceof StationaryEnemy){
                stationaryEnemies += 1;
            }
            else if(level.getEnemies().get(i) instanceof MovingEnemy){
                movingEnemies += 1;
            }
        }
        int totalEnemies = stationaryEnemies + movingEnemies;
        int numObj = level.getObjectives().size();
        int numWalls = level.getWalls().size();
        String levelName = level.getLevelName();
        String difficulty = level.getDifficulty();

        assertEquals(2, movingEnemies, "The number of moving enemies is unexpected");
        assertEquals(6, stationaryEnemies, "The number of stationary enemies is unexpected");
        assertEquals(8, totalEnemies, "The total number of enemies is unexpected");
        assertEquals(5, numObj, "The number of objectives is unexpected");
        assertEquals(85, numWalls, "The number of walls is unexpected");
        assertEquals("Level 1", levelName, "The level name is unexpected");
        assertEquals("Normal", difficulty, "The difficulty is unexpected");
    }

    @Test
    public void testLevelOneHard(){
        level = new Level1Hard();
        int stationaryEnemies = 0;
        int movingEnemies = 0;
        for(int i = 0; i < level.getEnemies().size(); i++){
            if(level.getEnemies().get(i) instanceof StationaryEnemy){
                stationaryEnemies += 1;
            }
            else if(level.getEnemies().get(i) instanceof MovingEnemy){
                movingEnemies += 1;
            }
        }
        int totalEnemies = stationaryEnemies + movingEnemies;
        int numObj = level.getObjectives().size();
        int numWalls = level.getWalls().size();
        String levelName = level.getLevelName();
        String difficulty = level.getDifficulty();

        assertEquals(2, movingEnemies, "The number of moving enemies is unexpected");
        assertEquals(12, stationaryEnemies, "The number of stationary enemies is unexpected");
        assertEquals(14, totalEnemies, "The total number of enemies is unexpected");
        assertEquals(5, numObj, "The number of objectives is unexpected");
        assertEquals(89, numWalls, "The number of walls is unexpected");
        assertEquals("Level 1", levelName, "The level name is unexpected");
        assertEquals("Hard", difficulty, "The difficulty is unexpected");
    }

    @Test
    public void testLevelTwoEasy(){
        level = new Level2Easy();
        int stationaryEnemies = 0;
        int movingEnemies = 0;
        for(int i = 0; i < level.getEnemies().size(); i++){
            if(level.getEnemies().get(i) instanceof StationaryEnemy){
                stationaryEnemies += 1;
            }
            else if(level.getEnemies().get(i) instanceof MovingEnemy){
                movingEnemies += 1;
            }
        }
        int totalEnemies = stationaryEnemies + movingEnemies;
        int numObj = level.getObjectives().size();
        int numWalls = level.getWalls().size();
        String levelName = level.getLevelName();
        String difficulty = level.getDifficulty();

        assertEquals(3, movingEnemies, "The number of moving enemies is unexpected");
        assertEquals(16, stationaryEnemies, "The number of stationary enemies is unexpected");
        assertEquals(19, totalEnemies, "The total number of enemies is unexpected");
        assertEquals(5, numObj, "The number of objectives is unexpected");
        assertEquals(118, numWalls, "The number of walls is unexpected");
        assertEquals("Level 2", levelName, "The level name is unexpected");
        assertEquals("Easy", difficulty, "The difficulty is unexpected");
    }

    @Test
    public void testLevelTwoNormal(){
        level = new Level2Normal();
        int stationaryEnemies = 0;
        int movingEnemies = 0;
        for(int i = 0; i < level.getEnemies().size(); i++){
            if(level.getEnemies().get(i) instanceof StationaryEnemy){
                stationaryEnemies += 1;
            }
            else if(level.getEnemies().get(i) instanceof MovingEnemy){
                movingEnemies += 1;
            }
        }
        int totalEnemies = stationaryEnemies + movingEnemies;
        int numObj = level.getObjectives().size();
        int numWalls = level.getWalls().size();
        String levelName = level.getLevelName();
        String difficulty = level.getDifficulty();

        assertEquals(2, movingEnemies, "The number of moving enemies is unexpected");
        assertEquals(16, stationaryEnemies, "The number of stationary enemies is unexpected");
        assertEquals(18, totalEnemies, "The total number of enemies is unexpected");
        assertEquals(5, numObj, "The number of objectives is unexpected");
        assertEquals(107, numWalls, "The number of walls is unexpected");
        assertEquals("Level 2", levelName, "The level name is unexpected");
        assertEquals("Normal", difficulty, "The difficulty is unexpected");
    }

    @Test
    public void testLevelTwoHard(){
        level = new Level2Hard();
        int stationaryEnemies = 0;
        int movingEnemies = 0;
        for(int i = 0; i < level.getEnemies().size(); i++){
            if(level.getEnemies().get(i) instanceof StationaryEnemy){
                stationaryEnemies += 1;
            }
            else if(level.getEnemies().get(i) instanceof MovingEnemy){
                movingEnemies += 1;
            }
        }
        int totalEnemies = stationaryEnemies + movingEnemies;
        int numObj = level.getObjectives().size();
        int numWalls = level.getWalls().size();
        String levelName = level.getLevelName();
        String difficulty = level.getDifficulty();

        assertEquals(4, movingEnemies, "The number of moving enemies is unexpected");
        assertEquals(9, stationaryEnemies, "The number of stationary enemies is unexpected");
        assertEquals(13, totalEnemies, "The total number of enemies is unexpected");
        assertEquals(6, numObj, "The number of objectives is unexpected");
        assertEquals(83, numWalls, "The number of walls is unexpected");
        assertEquals("Level 2", levelName, "The level name is unexpected");
        assertEquals("Hard", difficulty, "The difficulty is unexpected");
    }

    @Test
    public void testLevelThreeEasy(){
        level = new Level3Easy();
        int stationaryEnemies = 0;
        int movingEnemies = 0;
        for(int i = 0; i < level.getEnemies().size(); i++){
            if(level.getEnemies().get(i) instanceof StationaryEnemy){
                stationaryEnemies += 1;
            }
            else if(level.getEnemies().get(i) instanceof MovingEnemy){
                movingEnemies += 1;
            }
        }
        int totalEnemies = stationaryEnemies + movingEnemies;
        int numObj = level.getObjectives().size();
        int numWalls = level.getWalls().size();
        String levelName = level.getLevelName();
        String difficulty = level.getDifficulty();

        assertEquals(2, movingEnemies, "The number of moving enemies is unexpected");
        assertEquals(19, stationaryEnemies, "The number of stationary enemies is unexpected");
        assertEquals(21, totalEnemies, "The total number of enemies is unexpected");
        assertEquals(4, numObj, "The number of objectives is unexpected");
        assertEquals(73, numWalls, "The number of walls is unexpected");
        assertEquals("Level 3", levelName, "The level name is unexpected");
        assertEquals("Easy", difficulty, "The difficulty is unexpected");
    }

    @Test
    public void testLevelThreeNormal(){
        level = new Level3Normal();
        int stationaryEnemies = 0;
        int movingEnemies = 0;
        for(int i = 0; i < level.getEnemies().size(); i++){
            if(level.getEnemies().get(i) instanceof StationaryEnemy){
                stationaryEnemies += 1;
            }
            else if(level.getEnemies().get(i) instanceof MovingEnemy){
                movingEnemies += 1;
            }
        }
        int totalEnemies = stationaryEnemies + movingEnemies;
        int numObj = level.getObjectives().size();
        int numWalls = level.getWalls().size();
        String levelName = level.getLevelName();
        String difficulty = level.getDifficulty();

        assertEquals(3, movingEnemies, "The number of moving enemies is unexpected");
        assertEquals(19, stationaryEnemies, "The number of stationary enemies is unexpected");
        assertEquals(22, totalEnemies, "The total number of enemies is unexpected");
        assertEquals(6, numObj, "The number of objectives is unexpected");
        assertEquals(86, numWalls, "The number of walls is unexpected");
        assertEquals("Level 3", levelName, "The level name is unexpected");
        assertEquals("Normal", difficulty, "The difficulty is unexpected");
    }

    @Test
    public void testLevelThreeHard(){
        level = new Level3Hard();
        int stationaryEnemies = 0;
        int movingEnemies = 0;
        for(int i = 0; i < level.getEnemies().size(); i++){
            if(level.getEnemies().get(i) instanceof StationaryEnemy){
                stationaryEnemies += 1;
            }
            else if(level.getEnemies().get(i) instanceof MovingEnemy){
                movingEnemies += 1;
            }
        }
        int totalEnemies = stationaryEnemies + movingEnemies;
        int numObj = level.getObjectives().size();
        int numWalls = level.getWalls().size();
        String levelName = level.getLevelName();
        String difficulty = level.getDifficulty();

        assertEquals(3, movingEnemies, "The number of moving enemies is unexpected");
        assertEquals(16, stationaryEnemies, "The number of stationary enemies is unexpected");
        assertEquals(19, totalEnemies, "The total number of enemies is unexpected");
        assertEquals(6, numObj, "The number of objectives is unexpected");
        assertEquals(95, numWalls, "The number of walls is unexpected");
        assertEquals("Level 3", levelName, "The level name is unexpected");
        assertEquals("Hard", difficulty, "The difficulty is unexpected");
    }
}
