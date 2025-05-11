package Modules.Farming;

import Modules.Map.TileObject;
import Modules.Time;

public class Plant extends TileObject {

    private PlantType type;
    private Time plantingTime;
    private int regrownTimes;
    private int gianPosition;
//    0 1
//    2 3 and -1 if not gianted

    public Plant(PlantType type, Time plantingTime) {
        super();
        this.type = type;
        this.plantingTime = plantingTime;
        this.regrownTimes = 0;
        this.gianPosition = -1;
    }

    public PlantType getType() {
        return type;
    }

    public Time getPlantingTime() {
        return plantingTime;
    }


    public int getRegrownTimes() {
        return regrownTimes;
    }

    public int getGianPosition() {
        return gianPosition;
    }

    public void setPlantingTime(Time plantingTime) {
        this.plantingTime = plantingTime;
    }

    public void setRegrownTimes(int regrownTimes) {
        this.regrownTimes = regrownTimes;
    }

    public void setGianPosition(int gianPosition) {
        this.gianPosition = gianPosition;
    }

    public void harvest() {}


}
