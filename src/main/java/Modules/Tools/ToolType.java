package Modules.Tools;

import Modules.App;
import Modules.Enums.SkillType;
import Modules.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ToolType {
    hoe("Hoe", Arrays.asList(
            new LevelInfo("initial", 5, 5),
            new LevelInfo("copper", 4, 4),
            new LevelInfo("iron", 3, 3),
            new LevelInfo("gold", 2, 2),
            new LevelInfo("iridium", 1, 1)
    )),

    pickaxe("Pickaxe", Arrays.asList(
            new LevelInfo("initial", 5, 4),
            new LevelInfo("copper", 4, 3),
            new LevelInfo("iron", 3, 2),
            new LevelInfo("gold", 2, 1),
            new LevelInfo("iridium", 1, 0)
    )),

    axe("Axe", Arrays.asList(
            new LevelInfo("initial", 5, 4),
            new LevelInfo("copper", 4, 3),
            new LevelInfo("iron", 3, 2),
            new LevelInfo("gold", 2, 1),
            new LevelInfo("iridium", 1, 0)
    )),

    fishingPole("FishingPole", Arrays.asList(
            new LevelInfo("training", 8, 8),
            new LevelInfo("bamboo", 8, 8),
            new LevelInfo("fiberglass", 6, 6),
            new LevelInfo("iridium", 4, 4)
    )),

    scythe("Scythe", List.of(
            new LevelInfo("initial", 2, 2)
    )),

    milkPail("MilkPail", List.of(
            new LevelInfo("initial", 4, 4)
    )),

    shear("Shear", List.of(
            new LevelInfo("initial", 4, 4)
    )),

    wateringCan("WateringCan", Arrays.asList(
            new LevelInfo("initial", 5, 5),
            new LevelInfo("copper", 4, 4),
            new LevelInfo("iron", 3, 3),
            new LevelInfo("gold", 2, 2),
            new LevelInfo("iridium", 1, 1)
    ));

    private final String name;
    private final ArrayList<LevelInfo> levels;

    ToolType(String name, List<LevelInfo> levels) {
        this.name = name;
        this.levels = new ArrayList<>(levels);
    }

    public LevelInfo getLevel(int level) {
        return levels.get(level);
    }

    public String getName() {
        return name;
    }

    public static ToolType getToolTypeByName(String name) {
        for (ToolType toolType : ToolType.values()) {
            if (toolType.getName().equals(name)) {
                return toolType;
            }
        }
        return null;
    }

    public ArrayList<LevelInfo> getLevels() {
        return levels;
    }
}