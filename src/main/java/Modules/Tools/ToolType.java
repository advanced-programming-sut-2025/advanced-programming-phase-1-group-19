package Modules.Tools;

import java.util.ArrayList;

public enum ToolType {
    hoe,
    pickaxe,
    axe,
    fishingPole,
    scythe,
    milkPail,
    shear;

    private final String name;
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
//            TODO: do it for others and set names
        }
    }

    public LevelInfo getLevel(int level) {
        return levels.get(level);
    }

    public String getName() {
        return name;
    }

}
