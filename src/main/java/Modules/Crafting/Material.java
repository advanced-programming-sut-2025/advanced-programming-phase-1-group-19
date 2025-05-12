package Modules.Crafting;

import Modules.Item;
import Modules.Map.Tile;

public class Material extends Item {
    private MaterialType type;

    public Material(MaterialType type) {
        super(type.getName(), 1, false);
        this.type = type;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public void use() {

    }

    @Override
    public void drop(Tile tile) {
        tile.setObject(Material.this);
    }

    @Override
    public void delete() {

    }
}
