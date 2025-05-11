package Modules.Enums;

public enum Season {
    spring,
    summer,
    fall,
    winter,
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
