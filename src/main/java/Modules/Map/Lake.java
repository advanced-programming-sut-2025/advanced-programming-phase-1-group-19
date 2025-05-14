package Modules.Map;

import java.io.Serializable;
import java.util.ArrayList;

public class Lake extends Building implements Serializable {

    public Lake(ArrayList<Tile> tiles, Size size) {
        this.tiles = tiles;
        this.size = size;
    }

}
