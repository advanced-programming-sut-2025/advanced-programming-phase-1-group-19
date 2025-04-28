package Modules.Farming;

import Modules.Map.TileObject;
import Modules.Time;

public class Plant extends TileObject {

    private PlantType type;
    private Time HarvestTime;
    private Time lastWateringTime;
    private int regrownTimes;
    private int gianPosition;
//    0 1
//    2 3 and -1 if not gianted

    public Plant(PlantType type) {}

    public String show() {
//        TODO: implement for showPlant command
    }

    public void harvest() {}
}
