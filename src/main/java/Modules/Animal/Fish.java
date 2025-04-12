package Modules.Animal;

import Modules.Enums.Weather;
import Modules.Item;

public class Fish extends Item {
    public static int getFishingCount(Weather weather, int skillLevel) {}
    public static int getFishingQuality(Weather weather, int skillLevel) {}

    private FishType type;
    private Quality quality;

    public Fish(FishType type, Quality quality) {}

    @Override
    public void use() {

    }

    @Override
    public void drop() {

    }

    @Override
    public void delete() {

    }
}
