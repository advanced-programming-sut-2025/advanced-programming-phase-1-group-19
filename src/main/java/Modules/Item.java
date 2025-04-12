package Modules;

import Modules.Map.TileObject;

public abstract class Item extends TileObject {

    protected final String name;
    protected final int takenSpace;
    protected final boolean isEdible;

    public Item(String name, int takenSpace) {}

    public abstract void use();
    public abstract void drop();
    public abstract void delete();
}
