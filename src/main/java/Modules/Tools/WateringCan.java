package Modules.Tools;

import java.util.ArrayList;

public class WateringCan extends Tool {

    private static ArrayList<LevelInfo> levels = new ArrayList<>();
    private static ArrayList<Integer> maxCapacity = new ArrayList<>();
    private int amount;

    static {
        maxCapacity.add(40);
        maxCapacity.add(55);
        maxCapacity.add(70);
        maxCapacity.add(85);
        maxCapacity.add(100);
        levels.add(new LevelInfo("initial", 5, 5));
        levels.add(new LevelInfo("copper", 4, 4));
        levels.add(new LevelInfo("iron", 3, 3));
        levels.add(new LevelInfo("gold", 2, 2));
        levels.add(new LevelInfo("iridium", 1, 1));
        // TODO: if farming max! one less energy!
    }

    public WateringCan() {

    }

    public void fill() {

    }

    public int getCurrentCapacity() {

    }

    public void decreaseCapacity(int amount) {}

    @Override
    public LevelInfo getLevelInfo() {

    }

    @Override
    public void use() {

    }

    @Override
    public String getName() {
        return "Watering Can";
//        TODO:add its type
    }
}
