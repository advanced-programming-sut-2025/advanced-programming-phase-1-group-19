package Modules.Crafting;

import Modules.Item;

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
    public void drop() {

    }

    @Override
    public void delete() {

    }
}
