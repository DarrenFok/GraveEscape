package grave_escape.game;

/**
 * The {@code Grid} class represents a grid structure used in the game.
 * It defines the number of rows and columns in the grid.
 */
public class Grid {
    private int numOfRows;
    private int numOfCols;

    /**
     * Constructs a {@code Grid} object with the specified number of rows and columns.
     * @param numOfRows the number of rows in the grid.
     * @param numOfCols the number of columns in the grid.
     */
    public Grid(int numOfRows, int numOfCols) {
        this.numOfRows = numOfRows;
        this.numOfCols = numOfCols;
    }

    /**
     * Obtains the number of rows in the grid.
     * @return the number of rows in the grid.
     */
    public int getNumOfRows() {
        return this.numOfRows;
    }

    /**
     * Obtains the number of columns in the grid.
     * @return the number of columns in the grid.
     */
    public int getNumOfCols() {
        return this.numOfCols;
    }
}
