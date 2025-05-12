package Modules.Crafting;

public enum MaterialType {
    egg("egg"),
    milk("milk"),
    sugar("sugar"),
    cheese("cheese"),
    fiber("fiber"),
    oil("oil"),
    wheatFlour("wheat flour"),
    coffee("coffee"),
    bouquet("bouquet"),
    weddingRing("wedding ring"),
    wood("wood"),
    stone("stone"),
    ironOre("iron"),
    ironBar("iron"),
    copperOre("copper ore"),
    copperBar("copper bar"),
    goldOre("gold"),
    goldBar("gold"),
    iridiumOre("iridium ore"),
    iridiumBar("iridium bar"),
    coal("coal"),
    pickle("pickle"),
    wine("wine"),
    hardWood("hard wood"),
    diamond("diamond"),
    quartz("quartz"),

    ;

    private String name = null;

    MaterialType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public static MaterialType getMaterialTypeByName(String name) {
        for (MaterialType mt : MaterialType.values()) {
            if (mt.name.equals(name)) {
                return mt;
            }
        }
        return null;
    }

}
