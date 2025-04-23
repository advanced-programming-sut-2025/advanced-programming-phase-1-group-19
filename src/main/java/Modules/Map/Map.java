package Modules.Map;

import Modules.Player;

import java.util.ArrayList;

public class Map {
    private ArrayList<Farm> farms = new ArrayList<>();
    private NPCVillage npcVillage;

    public Map(ArrayList<Farm> farms) {
        this.farms = farms;
        this.npcVillage = new NPCVillage();
    }

    public boolean isWalkable(Position start, Position end) {
//        TODO: search the farms for the tile and check if you can go to that tile or not
    }

    public Tile getTile(Position position) {
        if(position.x >= 0 && position.x < 100 && position.y >= 0 && position.y < 100) {
            if(farms.size() < 1) {
                return null;
            }
            return farms.get(0).getTile(position);
        }
        else if (position.x >= 0 && position.x < 100 && position.y >= 150 && position.y < 250) {
            if(farms.size() < 2) {
                return null;
            }
            return farms.get(1).getTile(position);
        }
        else if(position.x >= 150 && position.x < 250 && position.y >= 0 && position.y < 100) {
            if(farms.size() < 3) {
                return null;
            }
            return farms.get(2).getTile(position);
        }
        else if(position.x >= 150 && position.x < 250 && position.y >= 150 && position.y < 250) {
            if (farms.size() < 4) {
                return null;
            }
            return farms.get(3).getTile(position);
        }
        else if(position.x >= 100 && position.x < 150 && position.y >= 100 && position.y < 150) {
            return npcVillage.getTile(position);
        }
        return null;
//        TODO: search farms for that tile
    }

}
