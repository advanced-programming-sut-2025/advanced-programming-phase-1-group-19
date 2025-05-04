package Modules.Tools;

import java.util.ArrayList;

public enum ToolType {
    hoe,
    pickaxe,
    axe,
    fishingPole,
    scythe,
    milkPail,
    shear,
    wateringCan
    ;

    private String name;
    private ArrayList<LevelInfo> levels;

    ToolType() {
        levels = new ArrayList<>();
        switch (this.name()) {
            case "hoe": {
                name = "Hoe";
                levels.add(new LevelInfo("initial", 5, 5));
                levels.add(new LevelInfo("copper", 4, 4));
                levels.add(new LevelInfo("iron", 3, 3));
                levels.add(new LevelInfo("gold", 2, 2));
                levels.add(new LevelInfo("iridium", 1, 1));
                break;
            }
            case "pickaxe": {
                name = "Pickaxe";
                levels.add(new LevelInfo("initial", 5,  4));
                levels.add(new LevelInfo("copper", 4, 3));
                levels.add(new LevelInfo("iron", 3, 2));
                levels.add(new LevelInfo("gold", 2, 1));
                levels.add(new LevelInfo("iridium", 1, 0));
                // TODO: if mining max! one less energy!
                break;
            }
            case "axe": {
                name = "Axe";
                levels.add(new LevelInfo("initial", 5,  4));
                levels.add(new LevelInfo("copper", 4, 3));
                levels.add(new LevelInfo("iron", 3, 2));
                levels.add(new LevelInfo("gold", 2, 1));
                levels.add(new LevelInfo("iridium", 1, 0));
                // TODO: if foraging max! one less energy!
                break;
            }
            case "fishingPole": {
                name = "FishingPole";
                levels.add(new LevelInfo("training", 8,  8));
                levels.add(new LevelInfo("bamboo", 8, 8));
                levels.add(new LevelInfo("fiberglass", 6, 6));
                levels.add(new LevelInfo("iridium", 4, 4));
                // TODO: if fishing max! one less energy!
                break;
            }
            case "scythe": {
                name = "Scythe";
                levels.add(new LevelInfo("initial", 2,  2));
                break;
            }
            case "milkPail": {
                name = "MilkPail";
                levels.add(new LevelInfo("initial", 4,  4));
                break;
            }
            case "shear": {
                name = "Shear";
                levels.add(new LevelInfo("initial", 4,  4));
                break;
            }
            case "wateringCan": {
                name = "WateringCan";
                levels.add(new LevelInfo("initial", 5, 5));
                levels.add(new LevelInfo("copper", 4, 4));
                levels.add(new LevelInfo("iron", 3, 3));
                levels.add(new LevelInfo("gold", 2, 2));
                levels.add(new LevelInfo("iridium", 1, 1));
                // TODO: if farming max! one less energy!
                break;
            }
//            TODO: do it for others and set names
        }
    }

    public LevelInfo getLevel(int level) {
        return levels.get(level);
    }

    public String getName() {
        return name;
    }

    public static ToolType getToolTypeByName(String name) {
        for (ToolType toolType : ToolType.values()) {
            if (toolType.name().equals(name)) {
                return toolType;
            }
        }
        return null;
    }

    public ArrayList<LevelInfo> getLevels() {
        return levels;
    }
}
