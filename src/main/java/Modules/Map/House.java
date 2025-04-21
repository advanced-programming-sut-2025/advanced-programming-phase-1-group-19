package Modules.Map;

import Modules.Crafting.Refrigerator;

import java.util.ArrayList;

public class House extends Building {

    private Refrigerator refrigerator;

    public House(ArrayList<Tile> tiles, Size size) {
        this.tiles = tiles;
        this.size = size;
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }
}
