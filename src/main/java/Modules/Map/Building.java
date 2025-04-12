package Modules.Map;

import java.util.ArrayList;

public abstract class Building {
    private ArrayList<Tile> tiles;
    private final Size size;

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Size getSize() {
        return size;
    }
}
