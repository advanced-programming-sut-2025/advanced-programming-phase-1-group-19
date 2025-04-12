package Modules.Map;

import Modules.Player;

import java.util.ArrayList;

public class Map {
    private ArrayList<Farm> farms = new ArrayList<>();
    private NPCVillage npcVillage;

    private void setRandomObjects() {

    }

    public Map(ArrayList<Farm> farms) {
//        TODO: design all maps here
    }

    public boolean isWalkable(Player player, Position destination) {
//        TODO: search the farms for the tile and check if you can go to that tile or not
    }

    public Tile getTile(Position position) {
//        TODO: search farms for that tile
    }

}
