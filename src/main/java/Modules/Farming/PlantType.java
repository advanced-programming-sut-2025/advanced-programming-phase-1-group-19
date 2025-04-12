package Modules.Farming;

import Modules.Enums.Season;

import java.util.ArrayList;

public enum PlantType {
    ;

    private final String name;
    private final SeedType seed;
    private final Crop crop;
    private final ArrayList<Integer> stages;
    private final int totalTime;
    private final int reGrowth;
//    TODO: -1 if not regrowtable
    private final int initialPrice;
    private final int energy;
    private final boolean isEdible;
    private final ArrayList<Season> seasonsAvailable;
    private final boolean canBeComeGiant;
    private final boolean isTree;

    CropType() {
//        TODO: set initial values
    }

    public String getName() {
        return name;
    }

    public SeedType getSeed() {
        return seed;
    }

    public ArrayList<Integer> getStages() {
        return stages;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public int getReGrowth() {
        return reGrowth;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public ArrayList<Season> getSeasonsAvailable() {
        return seasonsAvailable;
    }

    public boolean isCanBeComeGiant() {
        return canBeComeGiant;
    }

    public Crop getCrop() {
        return crop;
    }

    public boolean isTree() {
        return isTree;
    }
}
