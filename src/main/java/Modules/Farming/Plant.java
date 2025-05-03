package Modules.Farming;

import Modules.Map.TileObject;
import Modules.Time;

public class Plant extends TileObject {

    private PlantType type;
    private Time harvestTime;
    private int regrownTimes;
    private int gianPosition;
//    0 1
//    2 3 and -1 if not gianted

    public Plant(PlantType type, Time harvestTime,) {
        super();
        this.type = type;
        this.harvestTime = harvestTime;
        this.regrownTimes = 0;
        this.gianPosition = -1;
    }

    public PlantType getType() {
        return type;
    }

    public Time getHarvestTime() {
        return harvestTime;
    }


    public int getRegrownTimes() {
        return regrownTimes;
    }

    public int getGianPosition() {
        return gianPosition;
    }

    public void setHarvestTime(Time harvestTime) {
        this.harvestTime = harvestTime;
    }

    public void setRegrownTimes(int regrownTimes) {
        this.regrownTimes = regrownTimes;
    }

    public void setGianPosition(int gianPosition) {
        this.gianPosition = gianPosition;
    }

    public String show() {
//        TODO: implement for showPlant command
    }

    public void harvest() {}
}
