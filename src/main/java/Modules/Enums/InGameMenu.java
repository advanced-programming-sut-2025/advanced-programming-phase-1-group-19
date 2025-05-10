package Modules.Enums;

public enum InGameMenu {
    houseMenu("house"),
    craftingMenu("crafting");

    private final String name;

    InGameMenu(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + " menu";
    }
}
