package Modules.Animal;

import Modules.Enums.Season;

public enum FishType {
    ;

    private String name;
    private int basePrice;
    private Season season;
    private boolean isLegendary;

    FishType() {}

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public Season getSeason() {
        return season;
    }

    public boolean isLegendary() {
        return isLegendary;
    }
}
