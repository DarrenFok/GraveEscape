package grave_escape.objectives;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The {@code HighestResult} class manages the leaderboard of the game.
 * It loads and saves player results, allowing the addition or update of a player's highest score.
 */
public class HighestResult {

    private static final String FILENAME = "Data/leaderboard.csv";
    private final List<PlayerResult> leaderboard;
    private static HighestResult instance = null;

    /**
     * Private constructor to initialize the leaderboard by loading from the file.
     */
    private HighestResult() {
        this.leaderboard = loadLeaderboard();
    }

    /**
     * Returns the singleton instance of the {@code HighestResult} class.
     * @return the singleton instance of {@code HighestResult}.
     */
    public static HighestResult getInstance() {
        if(instance == null) {
            instance = new HighestResult();
        }
        return instance;
    }

    /**
     * Loads the leaderboard from a CSV file.
     * @return a list of {@code PlayerResult} objects representing the leaderboard.
     */
    private List<PlayerResult> loadLeaderboard() {
        List<PlayerResult> results = new ArrayList<>();
        File file = new File(FILENAME);

        if (!file.exists()) {
            return results; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    results.add(new PlayerResult(name, score));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading leaderboard: " + e.getMessage());
        }
        return results;
    }

    /**
     * Saves the current leaderboard to the CSV file.
     */
    private void saveLeaderboard() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (PlayerResult result : leaderboard) {
                writer.write(result.getName() + "," + result.getHighestScore());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save leaderboard: " + e.getMessage());
        }
    }

    /**
     * Adds or updates a player's highest score in the leaderboard.
     * @param name  the player's name.
     * @param score the player's score to save.
     */
    public void savePlayerResult(String name, int score) {
        leaderboard.sort(Comparator.comparingInt(PlayerResult::getHighestScore).reversed());
        boolean playerExists = false;
        for (PlayerResult result : leaderboard) {
            if (result.getName().equals(name)) {
                playerExists = true;
                if (score > result.getHighestScore()) {
                    result.setHighestScore(score);
                }
                break;
            }
        }
        if (!playerExists) {
            leaderboard.add(new PlayerResult(name, score));
        }
        saveLeaderboard();
    }

    /**
     * Retrieves the current leaderboard, sorted by highest score.
     * @return a list of {@code PlayerResult} objects representing the sorted leaderboard.
     */
    public List<PlayerResult> getLeaderboard() {
        leaderboard.sort(Comparator.comparingInt(PlayerResult::getHighestScore).reversed());
        return leaderboard;
    }

    /**
     * The {@code PlayerResult} class represents a player's data, including their name and highest score.
     */
    public static class PlayerResult {
        private String name;
        private int highestScore;

        /**
         * Constructs a {@code PlayerResult} object with the given name and highest score.
         * @param name         the player's name.
         * @param highestScore the player's highest score.
         */
        public PlayerResult(String name, int highestScore) {
            this.name = name;
            this.highestScore = highestScore;
        }

        /**
         * Retrieves the player's name.
         * @return the player's name.
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the player's name.
         * @param name the player's name.
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Retrieves the player's highest score.
         * @return the player's highest score.
         */
        public int getHighestScore() {
            return highestScore;
        }

        /**
         * Sets the player's highest score.
         * @param highestScore the player's highest score.
         */
        public void setHighestScore(int highestScore) {
            this.highestScore = highestScore;
        }

        /**
         * Returns a string representation of the player's result.
         * @return a string representation of the player's name and highest score.
         */
        @Override
        public String toString() {
            return "PlayerResult{" +
                    "name='" + name + '\'' +
                    ", highestScore=" + highestScore +
                    '}';
        }
    }
}
