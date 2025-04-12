package Modules.Animal;

import Modules.Map.Position;
import Modules.Map.Tile;
import Modules.Player;
import Modules.Time;

public class Animal {

    private final AnimalType type;
    private final Player owner;
    private int friendship;
    private Tile position;
    private boolean isOutside;
    private Time lastFeedingTime;
    private Time lastPetingTime;
    private Time lastProducingTime;

    public Animal(AnimalType type) {}

    public AnimalType getType() {}

    public void increaseFriendship(int amount) {}

    public void decreaseFriendship(int amount) {}

    public int getFriendship() { return friendship; }

    public void setFriendship(int friendship) { this.friendship = friendship; }

    public Player getOwner() { return owner; }

    public Position getPosition() { return position; }

    public void move(Tile newTile) {
    }

    public boolean isOutside() {}

    public void produce() {
//        TODO: check if its time to produce new product or not
    }

    public void collect() {}

    public int calSellingPrice() {}
}
