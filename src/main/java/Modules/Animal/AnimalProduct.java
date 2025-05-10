package Modules.Animal;

import Modules.Item;

public class AnimalProduct extends Item {

    private AnimalProductType type;
    private Quality quality;

    public AnimalProduct(AnimalProductType type) {
        super(type.getName(), 1, type.isEdible());
        this.type = type;
    }

    public AnimalProductType getType() {
        return type;
    }

    public Quality getQuality() {
        return quality;
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

    @Override
    public String getName() {
        return type.getName();
    }
}
