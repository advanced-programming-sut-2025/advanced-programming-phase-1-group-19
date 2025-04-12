package Modules.Map;

import Modules.Farming.Fertilizer;
import Modules.Farming.Plant;
import Modules.Time;

import java.awt.image.BufferedImage;

public class Tile {

    private final Position position;
    private TileObject object;
    private Building building;
    private boolean isPlowed;
    private boolean containsPlant;
    private Time lastWateringTime;

    public Tile(Position position) {

    }

    public Tile(Position position, TileObject object) {

    }

    public Position getPosition() {
        return position;
    }

    public TileObject getObject() {
        return object;
    }

    public void setObject(TileObject object) {
        this.object = object;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public boolean isPlowed() {
        return isPlowed;
    }

    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
    }

    public Time getLastWateringTime() {
        return lastWateringTime;
    }

    public boolean containsPlant() {
        return containsPlant;
    }

    public void plant(Plant plant) {}

    public void fertilize(Fertilizer fertilizer) {}

    public void water() {}
}
