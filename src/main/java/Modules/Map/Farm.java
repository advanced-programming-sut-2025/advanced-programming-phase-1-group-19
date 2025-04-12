package Modules.Map;

import Modules.Animal.Barn;
import Modules.Animal.Coop;

import java.util.ArrayList;

public class Farm {

    private final Position topLeft;
    private final Size size;
    private final ArrayList<Tile> tiles;
    private final Lake lake;
    private final GreenHouse greenHouse;
    private final House house;
    private final Quarry quarry;
    private Barn barn;
    private Coop coop;

    public Farm(int id) {
        switch (id) {
//            TODO: build initial farm based on farm map
        }
    }

    public Position getTopLeft() {
        return topLeft;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public GreenHouse getGreenHouse() {
        return greenHouse;
    }

    public Lake getLake() {
        return lake;
    }

    public House getHouse() {
        return house;
    }

    public Quarry getQuarry() {
        return quarry;
    }

    public Barn getBarn() {
        return barn;
    }

    public void setBarn(Barn barn) {
        this.barn = barn;
    }

    public Coop getCoop() {
        return coop;
    }

    public void setCoop(Coop coop) {
        this.coop = coop;
    }
}
