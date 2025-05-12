package Modules;

import Modules.Map.TileObject;

public abstract class Item extends TileObject {

    protected final String name;
    protected final int takenSpace;
    protected final boolean isEdible;
    abstract public int getPrice();

    public Item(String name, int takenSpace, boolean isEdible) {
        this.name = name;
        this.takenSpace = takenSpace;
        this.isEdible = isEdible;
    }

    public abstract void use();
    public abstract void drop();
    public abstract void delete();
    public String getName() {
        return name;
    }

    public int getTakenSpace() {
        return takenSpace;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item other = (Item) obj;
        return getName().equalsIgnoreCase(other.getName());
    }

    @Override
    public int hashCode() {
        return getName().toLowerCase().hashCode();
    }
}
