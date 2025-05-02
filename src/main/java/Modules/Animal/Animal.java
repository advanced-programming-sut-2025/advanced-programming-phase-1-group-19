package Modules.Animal;

import Modules.App;
import Modules.Game;
import Modules.Map.Position;
import Modules.Map.Tile;
import Modules.Player;
import Modules.Time;

public class Animal {

    private final String name;
    private final AnimalType type;
    private final Player owner;
    private int friendship;
    private Position position;
    private boolean isOutside;
    private Time lastFeedingTime;
    private Time lastPetingTime;
    private Time lastProducingTime;

    public Animal(AnimalType type, Player owner,String name) {
        this.type = type;
        this.owner = owner;
        this.name = name;
        this.isOutside = false;
    }

    public AnimalType getType() {
        return type;
    }

    public void setPosition(Position position) {
        this.position = position;
    }



    public void increaseFriendship(int amount) {
        if(friendship+amount<1000){
            friendship+=amount;
        }
    }

    public void decreaseFriendship(int amount) {}

    public int getFriendship() { return friendship; }

    public void setFriendship(int friendship) { this.friendship = friendship; }

    public Player getOwner() { return owner; }

    public Position getPosition() { return position; }

    public void move(Tile newTile) {
    }

    public void setOutside(boolean outside) {
        isOutside = outside;
    }

    public boolean isOutside() {
        return isOutside;
    }

    public void produce() {
//        TODO: check if its time to produce new product or not
        App app = App.getInstance();
        Game game=app.getCurrentGame();
        if(lastFeedingTime.getSeason() == game.getTime().getSeason()){
            if(lastPetingTime.getDay() - game.getTime().getDay()==-1){

            }
        }
    }

    public void collect() {}

    public int calSellingPrice() {}

    public String getName() {
        return name;
    }

    public void setLastFeedingTime(Time lastFeedingTime) {
        this.lastFeedingTime = lastFeedingTime;
    }

    public void setLastPettingTime(Time lastPetingTime) {
        this.lastPetingTime = lastPetingTime;
    }

    public void setLastProducingTime(Time lastProducingTime) {
        this.lastProducingTime = lastProducingTime;
    }

    public Time getLastFeedingTime() {
        return lastFeedingTime;
    }

    public Time getLastPetingTime() {
        return lastPetingTime;
    }

    public Time getLastProducingTime() {
        return lastProducingTime;
    }
}
