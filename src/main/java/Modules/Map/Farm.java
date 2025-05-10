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

    private void setRandomObjects() {
//        TODO: implement this
    }

    public Farm(FarmMap farmMap, int turn) {
        switch (turn) {
            case 0: {
                topLeft = new Position(0, 0);
                break;
            }
            case 1: {
                topLeft = new Position(0, 150);
                break;
            }
            case 2: {
                topLeft = new Position(150, 0);
                break;
            }
            case 3: {
                topLeft = new Position(150, 150);
                break;
            }
            default: {
                topLeft = new Position(0, 0);
            }
        }
        size = farmMap.getSize();
        tiles = new ArrayList<>();
        ArrayList<Tile> lakeTiles = new ArrayList<>();
        Position lakeTopLeft = new Position(farmMap.getLakePosition().x, farmMap.getLakePosition().y);
        Position lakeBottomRight = new Position(lakeTopLeft.x + farmMap.getLakeSize().height, lakeTopLeft.y + farmMap.getLakeSize().width);
        lakeTopLeft.move(topLeft);
        lakeBottomRight.move(topLeft);

        ArrayList<Tile> greenHouseTiles = new ArrayList<>();
        Position greenHouseTopLeft = new Position(farmMap.getGreenHousePosition().x, farmMap.getGreenHousePosition().y);
        Position greenHouseBottomRight = new Position(greenHouseTopLeft.x + farmMap.getGreenHouseSize().height, greenHouseTopLeft.y + farmMap.getGreenHouseSize().width);
        greenHouseTopLeft.move(topLeft);
        greenHouseBottomRight.move(topLeft);

        ArrayList<Tile> houseTiles = new ArrayList<>();
        Position houseTopLeft = new Position(farmMap.getHousePosition().x, farmMap.getHousePosition().y);
        Position houseBottomRight = new Position(houseTopLeft.x + farmMap.getHouseSize().height, houseTopLeft.y + farmMap.getHouseSize().width);
        houseTopLeft.move(topLeft);
        houseBottomRight.move(topLeft);

        ArrayList<Tile> quarryTiles = new ArrayList<>();
        Position quarryTopLeft = new Position(farmMap.getQuarryPosition().x, farmMap.getQuarryPosition().y);
        Position quarryBottomRight = new Position(quarryTopLeft.x + farmMap.getQuarrySize().height, quarryTopLeft.y + farmMap.getQuarrySize().width);
        quarryTopLeft.move(topLeft);
        quarryBottomRight.move(topLeft);

        for(int i = topLeft.x; i < topLeft.x + size.height; i++) {
            for(int j = topLeft.y; j < topLeft.y + size.width; j++) {
                Tile tile = new Tile(new Position(i, j));
                if(i >= lakeTopLeft.x && i < lakeBottomRight.x
                && j >= lakeTopLeft.y && j < lakeBottomRight.y) {
                    lakeTiles.add(tile);
                }
                else if(i >= greenHouseTopLeft.x && i < greenHouseBottomRight.x
                && j >= greenHouseTopLeft.y && j < greenHouseBottomRight.y) {
                    greenHouseTiles.add(tile);
                }
                else if(i >= houseTopLeft.x && i < houseBottomRight.x
                && j >= houseTopLeft.y && j < houseBottomRight.y) {
                    houseTiles.add(tile);
                }
                else if(i >= quarryTopLeft.x && i < quarryBottomRight.x
                && j >= quarryTopLeft.y && j < quarryBottomRight.y) {
                    quarryTiles.add(tile);
                }
                tiles.add(tile);
            }
        }
        lake = new Lake(lakeTiles, farmMap.getLakeSize());
        for(Tile tile : lakeTiles) {
            tile.setBuilding(lake);
        }

        greenHouse = new GreenHouse(greenHouseTiles, farmMap.getGreenHouseSize());
        for (Tile tile : greenHouseTiles) {
            tile.setBuilding(greenHouse);
        }

        house = new House(houseTiles, farmMap.getHouseSize());
        for(Tile tile : houseTiles) {
            tile.setBuilding(house);
        }

        quarry = new Quarry(quarryTiles, farmMap.getQuarrySize());
        for (Tile tile : quarryTiles) {
            tile.setBuilding(quarry);
        }

        setRandomObjects();
    }

    public Position getTopLeft() {
        return topLeft;
    }

    public Size getSize() {
        return size;
    }

    public Tile getTile(Position position) {
        for (Tile tile : tiles) {
            if (position.equals(tile.getPosition())) {
                return tile;
            }
        }
        return null;
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
