public class Grid {
    private int dimension;
    private int[][] grid;

    public Grid(int dimension) {
        this.dimension = dimension;
        int [][] grid = new int[dimension][dimension];
    }

    public int getDimension() {
        return dimension;
    }
}
