package Modules;

import Modules.Crafting.Material;
import Modules.Crafting.MaterialType;
import Modules.Enums.InGameMenu;
import Modules.Enums.Season;
import Modules.Enums.Weather;
import Modules.Farming.Plant;
import Modules.Map.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.RandomAccess;

public class Game {
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Map map;
    private Time time;
    private Weather todayWeather;
    private Weather tomrrowWeather;
    private InGameMenu inGameMenu;
    public final static Time startingTime = new Time();

    public Game(ArrayList<Player> players, Map map) {
        this.players = players;
        this.currentPlayer = players.getFirst();
        this.map = map;
        this.time = new Time();
        this.inGameMenu = null;
        todayWeather = Weather.getRandomWeather(Season.spring);
        tomrrowWeather = Weather.getRandomWeather(Season.spring);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setNextPlayer() {
//        TODO: get position from arraylist and set to next player
//        TODO: check if it was the last player nextHour()
//        TODO: check if the player is fainted
        int index = players.indexOf(currentPlayer);
        if(index == players.size() - 1) {
            index = 0;
            nextHour();
        }
        else{
            index++;
        }
        setCurrentPlayer(players.get(index));
    }

    public void nextSeason(){
        time.nextSeason();
    }

    public void nextDay() {
//        TODO: handle crow attack and thor attack of the new day
//        TODO: fix fainted players
//        TODO: handle special effect for each Weather!
        // todayWeather for nextDay is tomrrowWeather for today!
        todayWeather = tomrrowWeather;
        setTomorrowWeather();
        switch (tomrrowWeather){
            case rain -> {
//                watering plants automatically
                    autoWaterPlant();
//                TODO:1.5x while using tools
            }
            case storm -> {
//                TODO:has rain affects and also break trees
                autoWaterPlant();
            }
            case snow -> {
//                TODO:1.5x while using tools
                break;
            }
        }
        if(time.getDay() == 28){
            time.nextSeason();
        }
        time.nextDay();
        // check all plants
        for(int i = 0; i < 250; i++){
            for(int j = 0; j < 250; j++){
                Tile tile = map.getTile(new Position(i, j));
                if(tile != null){
                    TileObject tileObject = tile.getObject();
                    if(tileObject instanceof Plant){
                        if(((Plant) tileObject).isDestroyed()){
                            Position position = new Position(i, j);
                            map.setTile(position, new Tile(position));
                        }
                        else{
                            ((Plant) tileObject).grow();
                        }
                    }
                }
            }
        }
        thor();
    }

    public void nextHour() {
        if(time.getHour() == 22) {
            nextDay();
        }
        time.nextHour();
    }
    public Time getTime() {
        return time;
    }

    public void thor(){
        for (Player player : players) {
            for(int i = 0; i < 3; i++){
                int x = player.getFarm().getTopLeft().x + 2 + (int)(Math.random() * 70);
                int y = player.getFarm().getTopLeft().y + 2 + (int)(Math.random() * 70);
                thor(new Position(x, y));
            }
        }
    }

    public void thor(Position position){
        if(map.getTile(position) == null){
            return;
        }
        TileObject tileObject = map.getTile(position).getObject();
        if(tileObject instanceof Plant){
            map.setTile(position, new Tile(position));
            map.getTile(position).setObject(new Material(MaterialType.coal));
        }
    }

    public Weather getTodayWeather() {
        return todayWeather;
    }

    public Weather getTomrrowWeather() {
        return tomrrowWeather;
    }

    public void setTomorrowWeather() {
        if(time.getDay() == 28) {
            tomrrowWeather = Weather.getRandomWeather(time.getSeason().getNext());
        }
        else{
            tomrrowWeather = Weather.getRandomWeather(time.getSeason());
        }
    }

    public void setTomorrowWeather(Weather tomorrowWeather) {
        this.tomrrowWeather = tomorrowWeather;
    }


    public InGameMenu getInGameMenu() {
        return inGameMenu;
    }

    public void setInGameMenu(InGameMenu inGameMenu) {
        this.inGameMenu = inGameMenu;
    }

    public Player getPlayerByUsername(String username) {
        for (Player player : players) {
            if(player.getUser().getUsername().equals(username)) {
                return player;
            }
        }
        return null;
    }

    public void autoWaterPlant(){
        for(int i = 0; i < 250; i++){
            for(int j = 0; j < 250; j++){
                Tile tile = map.getTile(new Position(i, j));
                if(tile != null){
                    TileObject tileObject = tile.getObject();
                    if(tileObject instanceof Plant){
                        ((Plant) tileObject).setLastWateringTime(new Time(time));
                    }
                }
            }
        }
    }
}
