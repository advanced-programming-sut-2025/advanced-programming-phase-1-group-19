package Modules.Crafting;

import Modules.Item;
import Modules.Map.Tile;

public class Material extends Item {
    private MaterialType type;

    public Material(MaterialType type) {
        super(type.getName(), 0, false);
        this.type = type;
    }

    public MaterialType getType() {
        return type;
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return type.getName();
    }
}
