package Modules.Map;

import java.util.ArrayList;

public class NPCVillage {
    private final Position topLeft;
    private final Size size;
    private final ArrayList<Tile> tiles;

    public NPCVillage() {
        topLeft = new Position(100, 100);
        size = new Size(50, 50);
        tiles = new ArrayList<>();
        for(int i = 100; i < 150; i++) {
            for(int j = 100; j < 150; j++) {
                tiles.add(new Tile(new Position(i, j)));
            }
        }
    }

    public Position getTopLeft() {
        return topLeft;
    }

    public Size getSize() {
        return size;
    }

    public Tile getTile(Position position) {
        for(Tile tile : tiles) {
            if(tile.getPosition().equals(position)) {
                return tile;
            }
        }
        return null;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
