package Modules.Farming;

import Modules.Item;

public class ForagingCrop extends Item {
    private ForagingCropType type;

    public ForagingCrop(ForagingCropType type) {
        super(type.getName(), 1, true);
        this.type = type;
    }

    @Override
    public int getPrice() {
        return type.getBaseValue();
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
