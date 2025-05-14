package Modules;

import Modules.Animal.Animal;
import Modules.Enums.InGameMenu;
import Modules.Enums.Season;
import Modules.Enums.Weather;
import Modules.Map.Farm;
import Modules.Map.Map;
import Modules.Map.Position;

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
//                TODO:watering plants automatically
//                TODO:1.5x while using tools
                break;
            }
            case storm -> {
//                TODO:has rain affects and also break trees
                break;
            }
            case snow -> {
//                TODO:1.5x while using tools
                break;
            }
        }

        if(currentPlayer.getFarm().getBarn() != null){
            for (Animal animal : currentPlayer.getFarm().getBarn().getAnimals()) {
                if(animal.isOutside()){
                    animal.decreaseFriendship(20);
                }
            }
        }

        if(currentPlayer.getFarm().getCoop() != null){
            for (Animal animal : currentPlayer.getFarm().getCoop().getAnimals()) {
                if(animal.isOutside()){
                    animal.decreaseFriendship(20);
                }
            }
        }

        if(currentPlayer.getFarm().getBarn() != null){
            for (Animal animal : currentPlayer.getFarm().getBarn().getAnimals()) {
                if(!animal.hasBeenFedToday(time)){
                    animal.decreaseFriendship(20);
                }
            }
        }

        if(currentPlayer.getFarm().getCoop() != null){
            for (Animal animal : currentPlayer.getFarm().getCoop().getAnimals()) {
                if(!animal.hasBeenFedToday(time)){
                    animal.decreaseFriendship(20);
                }
            }
        }

        if(currentPlayer.getFarm().getBarn() != null){
            for (Animal animal : currentPlayer.getFarm().getBarn().getAnimals()) {
                if(!animal.hasBeenPetToday(time)){
                    animal.decreaseFriendship(20);
                }
            }
        }

        if(currentPlayer.getFarm().getCoop() != null){
            for (Animal animal : currentPlayer.getFarm().getCoop().getAnimals()) {
                if(animal.hasBeenPetToday(time)){
                    animal.decreaseFriendship(20);
                }
            }
        }

        if(time.getDay() == 28){
            time.nextSeason();
        }
        time.nextDay();

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
        Farm farm = currentPlayer.getFarm();
        int height = farm.getSize().height;
        int width = farm.getSize().width;
        int x = farm.getTopLeft().x;
        int y = farm.getTopLeft().y;
        for(int i = 0; i < 3; i++){
//            TODO set newx and newy and then call thor(new Position(newx, newy));
        }
    }

    public void thor(Position position){
//        TODO: check if tile is tree it burns and gives coal while harvesting!
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
}
