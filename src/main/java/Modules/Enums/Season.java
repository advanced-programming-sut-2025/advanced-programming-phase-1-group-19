package Modules.Enums;

public enum Season {
    ;
    private int id;

    Season() {
        this.id = this.ordinal();
    }

    public int getId() {
        return id;
    }

    public Season getNext() {}
}
