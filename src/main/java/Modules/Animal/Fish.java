package Modules.Animal;

import Modules.App;
import Modules.Enums.Season;
import Modules.Enums.SkillType;
import Modules.Enums.Weather;
import Modules.Game;
import Modules.Item;
import Modules.Player;
import Modules.Tools.LevelInfo;
import Modules.Tools.Tool;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Fish extends Item {
    public static int getFishingCount(Weather weather, int skillLevel) {
        App app = App.getInstance();
        Game game=app.getCurrentGame();
        Player player=game.getCurrentPlayer();
        if(weather == Weather.rain) {
            double x=(double)player.getSkill(SkillType.fishing).getAmount()+2;
            x*=1.2;
            double random = ThreadLocalRandom.current().nextDouble(0.0, 1.0);
            x*=random;
            return (int)x;
        }
        else if(weather == Weather.snow){
            double x=(double)player.getSkill(SkillType.fishing).getAmount()+2;
            x*=1;
            double random = ThreadLocalRandom.current().nextDouble(0.0, 1.0);
            x*=random;
            return (int)x;
        }
        else if(weather == Weather.storm){
            double x=(double)player.getSkill(SkillType.fishing).getAmount()+2;
            x*=0.5;
            double random = ThreadLocalRandom.current().nextDouble(0.0, 1.0);
            x*=random;
            return (int)x;
        }
        else if(weather == Weather.sunny){
            double x=(double)player.getSkill(SkillType.fishing).getAmount()+2;
            x*=1.5;
            double random = ThreadLocalRandom.current().nextDouble(0.0, 1.0);
            x*=random;
            return (int)x;
        }
        else return 0;
    }
    public static int getFishingQuality(Weather weather, int skillLevel, LevelInfo levelInfo) {
        App app = App.getInstance();
        Game game=app.getCurrentGame();
        Player player=game.getCurrentPlayer();
        double x=(double)player.getSkill(SkillType.fishing).getAmount()+2;
        double random = ThreadLocalRandom.current().nextDouble(0.0, 1.0);
        x*=random;
//        TODO: check the names with shayan!!
        if(levelInfo.levelName().equals("Training")){
            x*=0.1;
        }
        else if(levelInfo.levelName().equals("Bamboo")){
            x*=0.5;
        }
        else if(levelInfo.levelName().equals("Fiberglass")){
            x*=0.9;
        }
        else if(levelInfo.levelName().equals("Iridium")){
            x*=1.2;
        }
        if(weather == Weather.rain) {
            x/=5.8;
        }
        else if(weather == Weather.snow){
            x/=6;
        }
        else if(weather == Weather.sunny){
            x/=5.5;
        }
        else {
            x/=6.5;
        }
    }

    private FishType type;
    private Quality quality;

    public Fish(FishType type, Quality quality) {}

    @Override
    public void use() {

    }

    @Override
    public void drop() {

    }

    @Override
    public void delete() {

    }

    @Override
    public String getName() {
        return type.getName();
    }
}
