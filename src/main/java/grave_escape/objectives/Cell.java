package grave_escape.objectives;

import grave_escape.game.Position;

public class Cell {
    private Position position;
    private boolean isWalkable;

    public Cell(Position position, boolean isWalkable) {
        this.position = position;
        this.isWalkable = isWalkable;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isWalkable() {
        return isWalkable;
    }
}
