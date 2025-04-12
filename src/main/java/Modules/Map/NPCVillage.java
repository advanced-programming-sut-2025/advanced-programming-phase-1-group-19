package Modules.Map;

import java.util.ArrayList;

public class NPCVillage {
    private final Position topLeft;
    private final Size size;
    private final ArrayList<Tile> tiles;

    public NPCVillage() {}

    public Position getTopLeft() {
        return topLeft;
    }

    public Size getSize() {
        return size;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
