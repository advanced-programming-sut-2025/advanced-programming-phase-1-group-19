package Modules.Map;

import java.io.Serializable;
import java.util.ArrayList;

public class GreenHouse extends Building implements Serializable {

    public GreenHouse(ArrayList<Tile> tiles, Size size) {
        this.tiles = tiles;
        this.size = size;
    }
}
