package Modules.Map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Map {
    private ArrayList<Farm> farms;
    private NPCVillage npcVillage;

    public Map(ArrayList<Farm> farms) {
        this.farms = farms;
        this.npcVillage = new NPCVillage();
    }

    public ArrayList<Tile> getPath(Position start, Position end) {
        HashMap<Tile, Tile> father = new HashMap<>();
        ArrayList<Tile> queue = new ArrayList<>();
        Tile startTile = getTile(start);
        Tile endTile = getTile(end);
        if(startTile == null || endTile == null) {
            return null;
        }
        queue.add(startTile);
        father.put(startTile, startTile);
        while (!queue.isEmpty()) {
            Tile tile = queue.removeFirst();
            Position pos = tile.getPosition();
            ArrayList<Tile> neighbors = new ArrayList<>();
            neighbors.add(getTile(new Position(pos.x + 1, pos.y)));
            neighbors.add(getTile(new Position(pos.x - 1, pos.y)));
            neighbors.add(getTile(new Position(pos.x, pos.y + 1)));
            neighbors.add(getTile(new Position(pos.x, pos.y - 1)));
            for(Tile neighbor : neighbors) {
                if(neighbor != null && neighbor.isTotallyEmpty() && !father.containsKey(neighbor)) {
                    father.put(neighbor, tile);
                    queue.add(neighbor);
                }
            }
            if(father.containsKey(endTile)) {
                break;
            }
        }
        Tile node = endTile;
        ArrayList<Tile> path = new ArrayList<>();
        if (!father.containsKey(node)) {
            return null;
        }
        while (!father.get(node).equals(node)) {
            path.add(node);
            node = father.get(node);
            if(father.get(node) == null) {
                return null;
            }
        }
        Collections.reverse(path);
        return path;
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

    public void setTile(Position position, Tile tile) {
        if(position.x >= 0 && position.x < 100 && position.y >= 0 && position.y < 100) {
            if(farms.size() < 1) {
                return;
            }
            farms.get(0).setTile(position, tile);
        }
        else if (position.x >= 0 && position.x < 100 && position.y >= 150 && position.y < 250) {
            if(farms.size() < 2) {
                return;
            }
            farms.get(1).setTile(position, tile);
        }
        else if(position.x >= 150 && position.x < 250 && position.y >= 0 && position.y < 100) {
            if(farms.size() < 3) {
                return;
            }
            farms.get(2).setTile(position, tile);
        }
        else if(position.x >= 150 && position.x < 250 && position.y >= 150 && position.y < 250) {
            if (farms.size() < 4) {
                return;
            }
            farms.get(3).setTile(position, tile);
        }
        else if(position.x >= 100 && position.x < 150 && position.y >= 100 && position.y < 150) {
            return ;
        }
    }
}
