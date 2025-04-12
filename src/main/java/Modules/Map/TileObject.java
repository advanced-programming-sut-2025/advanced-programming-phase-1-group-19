package Modules.Map;

public abstract class TileObject {

    protected Tile placedTile;

    public Tile getPlacedTile() {
        return placedTile;
    }

    public void setPlacedTile(Tile placedTile) {
        this.placedTile = placedTile;
    }
}
