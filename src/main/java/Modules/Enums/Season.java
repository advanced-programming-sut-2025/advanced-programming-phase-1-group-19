package Modules.Enums;

public enum Season {
    Spring,
    Summer,
    Fall,
    Winter
    ;
    private int id;

    Season() {
        this.id = this.ordinal();
    }

    public int getId() {
        return id;
    }

    public Season getNext() {
        Season[] values = Season.values();
        return values[(this.ordinal() + 1) % values.length];
    }

}
