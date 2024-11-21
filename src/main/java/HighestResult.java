import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighestResult {

    private static final String FILENAME = "leaderboard.csv";
    private final List<PlayerResult> leaderboard;
    private static HighestResult instance = null;

    // Constructor
    private HighestResult() {
        this.leaderboard = loadLeaderboard();
    }

    public static HighestResult getInstance() {
        if(instance == null) {
            instance = new HighestResult();
        }
        return instance;
    }

    // Load the leaderboard from the CSV file
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

    // Save the leaderboard to the CSV file
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

    // Add or update a player's highest score
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

    // Get the leaderboard
    public List<PlayerResult> getLeaderboard() {
        leaderboard.sort(Comparator.comparingInt(PlayerResult::getHighestScore).reversed());
        return leaderboard;
    }

    // PlayerResult class to represent individual player data
    public static class PlayerResult {
        private String name;
        private int highestScore;

        public PlayerResult(String name, int highestScore) {
            this.name = name;
            this.highestScore = highestScore;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHighestScore() {
            return highestScore;
        }

        public void setHighestScore(int highestScore) {
            this.highestScore = highestScore;
        }

        @Override
        public String toString() {
            return "PlayerResult{" +
                    "name='" + name + '\'' +
                    ", highestScore=" + highestScore +
                    '}';
        }
    }
}