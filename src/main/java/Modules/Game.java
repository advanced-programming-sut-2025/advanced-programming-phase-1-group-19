package Modules;

import Modules.Enums.InGameMenu;
import Modules.Enums.Weather;
import Modules.Map.Farm;
import Modules.Map.Map;
import Modules.Map.Position;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Map map;
    private Time time;
    private Weather todayWeather;
    private Weather tomrrowWeather;
    private InGameMenu inGameMenu;

    public Game(ArrayList<User> users, ArrayList<Farm> farms) {
//        TODO: fix players here
//        TODO: create map heer
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
    }

    public void nextSeason(){}

    public void nextDay() {
//        TODO: handle crow attack and thor attack of the new day
    }

    public void nextHour() {

    }
    public Time getTime() {
        return time;
    }

    public void thor(){}

    public void thor(Position position){}

    public Weather getTodayWeather() {
        return todayWeather;
    }

    public Weather getTomrrowWeather() {
        return tomrrowWeather;
    }

    public void setTomorrowWeather() {
//        TODO : predict
    }

    public void setTomorrowWeather(Weather tomorrowWeather) {}


    public InGameMenu getInGameMenu() {
        return inGameMenu;
    }

    public void setInGameMenu(InGameMenu inGameMenu) {
        this.inGameMenu = inGameMenu;
    }
}
