package Modules.Crafting;

import Modules.Item;

public class Material extends Item {


    public Material(String name, int takenSpace, boolean isEdible) {
        super(name, takenSpace, isEdible);
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
