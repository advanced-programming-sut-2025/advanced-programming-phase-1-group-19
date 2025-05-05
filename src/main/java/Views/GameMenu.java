package Views;

import Controllers.*;
import Modules.Farming.Seed;
import Modules.Farming.SeedType;
import Modules.App;
import Modules.Enums.InGameMenu;
import Modules.Game;
import Modules.Interactions.Commands.*;
import Modules.Interactions.Messages.*;
import Modules.Map.Direction;
import Modules.Map.Position;
import Modules.Player;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu implements AppMenu {
    private static GameMenu instance;

    private GameMenu() {
    }

    public static GameMenu getInstance() {
        if (instance == null) {
            instance = new GameMenu();
        }
        return instance;
    }

    public String getSingleLine(String output) {
        if(!output.isEmpty()) {
            System.out.println(output);
        }
        return scanner.nextLine().trim();
    }

    private void runCommand(GameCommand command, String input) {
        switch (command) {
            case startNewGame: {
                Pattern pattern = Pattern.compile("game new -u (?<usernames>.+?)");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String allUsernames = matcher.group("usernames").trim();
                if (allUsernames.isEmpty()) {
                    System.out.println("You did not enter any usernames!");
                }
                String[] usernamesArray = allUsernames.split("\\s+");
                GameMessage result = gameController.startNewGame(usernamesArray);
                System.out.println(result.message());
                break;
            }
            case showTime: {
                System.out.println(gameController.showTime().message());
                break;
            }
            case showDate: {
                System.out.println(gameController.showDate().message());
                break;
            }
            case showDateTime: {
                System.out.println(gameController.showDateTime().message());
                break;
            }
            case showDayOfWeek: {
                System.out.println(gameController.showDayOfWeak().message());
                break;
            }
            case cheatAdvanceTime: {
                Pattern pattern = Pattern.compile("^\\s*cheat advance time (?<X>\\d+)h\\s*$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(gameController.cheatAdvanceTime(Integer.parseInt(matcher.group("X"))).message());
                break;
            }
            case cheatAdvanceDate: {
                Pattern pattern = Pattern.compile("^\\s*cheat advance date (?<X>\\d+)d\\s*$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(gameController.cheatAdvanceDate(Integer.parseInt(matcher.group("X"))).message());
                break;
            }
            case season: {
                System.out.println(gameController.showSeason().message());
                break;
            }
            case exitGame: {
                System.out.println(gameController.exitGame().message());
                break;
            }
            case nextTurn: {
                System.out.println(gameController.nextTurn().message());
                break;
            }
            case forceTerminate: {
                System.out.println(gameController.forceTerminate().message());
                break;
            }
            case walk: {
                Pattern pattern = Pattern.compile("^walk -l (?<x>\\d+) (?<y>\\d+)");
                Matcher matcher = pattern.matcher(input);
                int x = Integer.parseInt(matcher.group("x"));
                int y = Integer.parseInt(matcher.group("y"));
                System.out.println(gameController.walk(new Position(x, y)).message());
                break;
            }
            case printMap: {
                Pattern pattern = Pattern.compile("^print map -l (?<x>\\d+) (?<y>\\d+) -s (?<size>\\d+)$");
                Matcher matcher = pattern.matcher(input);
                int x = Integer.parseInt(matcher.group("x"));
                int y = Integer.parseInt(matcher.group("y"));
                int size = Integer.parseInt(matcher.group("size"));

                String out = gameController.printMap(new Position(x, y), size).message();
                out = out.replace("H", "\u001B[35mH\u001B[0m");
                out = out.replace("G", "\u001B[32mG\u001B[0m");
                out = out.replace("L", "\u001B[34mL\u001B[0m");
                out = out.replace("Q", "\u001B[33mQ\u001B[0m");
                System.out.println(out);
                break;
            }
            case helpPrintMap: {
                System.out.println(gameController.helpPrintMap().message());
                break;
            }
            case weather:{
                System.out.println(gameController.showWeather().message());
                break;
            }
            case weatherForecast:{
                System.out.println(gameController.weatherForecast().message());
                break;
            }
            case cheatWeatherForecast:{
                Pattern pattern = Pattern.compile("^cheat weather set (?<type>.*)$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(gameController.cheatForecast(matcher.group("type")).message());
                break;
            }
            case buildGreenHouse:{
                System.out.println(gameController.buildGreenHouse().message());
                break;
            }
            case cheatThor:{
                Pattern pattern = Pattern.compile("^cheat Thor -l <(?<x>\\d+) , (?<y>\\d+)>$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(gameController.cheatThor(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y"))).message());
            }
            case showEnergy: {
                System.out.println(gameController.showEnergy().message());
                break;
            }
            case cheatEnergy: {
                String pattern="^\\s*energy set -v (?<value>.+?)\\s*$";
                Pattern pattern1 = Pattern.compile(pattern);
                Matcher matcher1 = pattern1.matcher(input);
                System.out.println(gameController.cheatEnergySet(Integer.parseInt(matcher1.group(1))).message());
                break;
            }
            case energyUnlimited: {
                System.out.println(gameController.unlimitedEnergySet().message());
                break;
            }
            case inventoryShow:{
                System.out.println(gameController.showInventory().message());
                break;
            }
            case inventoryTrash: {
                String pattern="^\\s*inventory trash -i (?<itemName>.+?)(?: -n (?<number>\\d+))?\\s*$";
                Pattern pattern1 = Pattern.compile(pattern);
                Matcher matcher1 = pattern1.matcher(input);
                if(input.contains("-n")) {
                    System.out.println(gameController.inventoryTrash(matcher1.group(1),Integer.parseInt(matcher1.group(2)) ,false).message());
                }
                else {
                    System.out.println(gameController.inventoryTrash(matcher1.group(1), 0,true).message());

                }
                break;
            }
            case toolEquip:{
                Pattern pattern  = Pattern.compile("^\\s*tools equip (?<toolName>.+)\\s*$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(gameController.equipTool(matcher.group("toolName")).message());
                break;
            }
            case toolCurrentShow:{
                Pattern pattern = Pattern.compile("^\\s*tools show current\\s*$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(gameController.showCurrentTool().message());
                break;
            }
            case toolShowAvailable:{
                Pattern pattern = Pattern.compile("^\\s*tools show available\\s*$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(gameController.showAllTools().message());
                break;
            }
            case toolUpgrade:{
                Pattern pattern = Pattern.compile("^\\s*tools upgrade (?<toolName>.+)\\s*$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(gameController.upgradeTool(matcher.group("toolName")).message());
                break;
            }
            case toolUse:{
                Pattern pattern = Pattern.compile("^\\s*tools use -d (?<direction>.+)\\s*$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(gameController.useTool(matcher.group("direction")).message());
                break;
            }

            case goToHouseMenu:{
                System.out.println(gameController.openHouseMenu().message());
                break;
            }
            case cookingRefrigerator:{
                Pattern pattern = Pattern.compile("^\\s*cooking refrigerator (?<putOrPick>.+?) (?<item>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(matcher.group(1).equals("Put")) {
                    System.out.println(houseController.refrigerator(matcher.group(2),1,true).message());
                }
                else {
                    System.out.println(houseController.refrigerator(matcher.group(2),1,false).message());
                }
                break;
            }
            case showCookingRecipe:{
                System.out.println(houseController.showCookingRecipe().message());
                break;
            }
            case cooking:{
                Pattern pattern = Pattern.compile("^\\s*cooking prepare (?<recipeName>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(houseController.cookingPrepare(matcher.group(1)).message());
                break;
            }
            case eatFood:{
                Pattern pattern=Pattern.compile("^\\s*eat (?<foodName>.+?)\\s*$");
                Matcher matcher=pattern.matcher(input);
                System.out.println(houseController.eatFood(matcher.group(1)).message());
                break;
            }
            case buildBarn:{
                Pattern pattern=Pattern.compile("^\\s*build -a (?<buildingName>.+?) -l (?<x>\\d+) (?<y>\\d+)\\s*$");
                Matcher matcher=pattern.matcher(input);
                System.out.println(houseController.buildBarn(matcher.group(1),Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)) ).message());
                break;
            }
            case buyAnimal:{
                Pattern pattern=Pattern.compile("^\\s*buy animal -a (?<animal>.+?) -n (?<name>.+?)\\s*$");
                Matcher matcher=pattern.matcher(input);
                System.out.println(houseController.buyAnimal(matcher.group(1),matcher.group(2)).message());
                break;
            }
            case petting:{
                Pattern pattern=Pattern.compile("^\\s*pet -n (?<name>.+?)\\s*$");
                Matcher matcher=pattern.matcher(input);
                System.out.println(gameController.petAnimal(matcher.group(1)).message());
                break;
            }
            case cheatAnimalFriendship:{
                Pattern pattern=Pattern.compile("cheat set friendship -n (?<animalName>.+?) -c (?<amount>.+?)");
                Matcher matcher=pattern.matcher(input);
                System.out.println(gameController.cheatFriendship(matcher.group(1),Integer.parseInt(matcher.group(2)) ).message());
                break;
            }
            case showAnimals:{
                System.out.println(gameController.getFriendship().message());
                break;
            }
            case shepherdAnimals:{
                Pattern pattern=Pattern.compile("^\\s*shepherd animals -n (?<animalName>.+?) -l (?<x>\\d+) (?<y>\\d+)\n\\s*$");
                Matcher matcher=pattern.matcher(input);
                System.out.println(gameController.shepherdAnimals(matcher.group(1),Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3)) ).message());
                break;
            }
            case feedHay:{
                Pattern pattern=Pattern.compile("^\\s*feed hay -n (?<animalName>.+?)\\s*$");
                Matcher matcher=pattern.matcher(input);
                System.out.println(gameController.feedingHay(matcher.group(1)).message());
                break;
            }
            case fishing:{
                Pattern pattern=Pattern.compile("fishing -p (?<fishingPole>.+?)");
                Matcher matcher=pattern.matcher(input);
                System.out.println(gameController.fishing(matcher.group(1)).message());
                break;
            }
            case collectProducts:{
                Pattern pattern=Pattern.compile("^\\s*collect produce -n (?<name>.+?)\\s*$");
                Matcher matcher=pattern.matcher(input);
                System.out.println(gameController.collectProducts(matcher.group(1)).message());
                break;
            }
            case sellAnimal:{
                Pattern pattern=Pattern.compile("^\\s*sell animal -n (?<name>.+?)\\s*$");
                Matcher matcher=pattern.matcher(input);
                System.out.println(gameController.sellAnimal(matcher.group(1)).message());
                break;
            }
            case craftInfo: {
                Pattern pattern = Pattern.compile("^craftinfo -n (?<name>.+)$");
                Matcher matcher = pattern.matcher(input);
                String name = matcher.group("name");
                System.out.println(gameController.craftInfo(name).message());
                break;
            }
            case plant: {
                Pattern pattern = Pattern.compile("^plant -s (?<seed>\\S+) -d (?<direction>\\S+)$");
                Matcher matcher = pattern.matcher(input);
                String seedName = matcher.group("seed");
                String directionName = matcher.group("direction");
                SeedType seed = SeedType.valueOf(seedName);
                Direction direction = Direction.getDirection(directionName);
                System.out.println(gameController.plant(seed, direction));
                break;
            }
            case showPlant: {
                Pattern pattern = Pattern.compile("^show plant <(?<x>\\d+) , (?<y>\\d+)>$");
                Matcher matcher = pattern.matcher(input);
                String xString = matcher.group("x");
                String yString = matcher.group("y");
                int x = Integer.parseInt(xString);
                int y = Integer.parseInt(yString);
                System.out.println(gameController.showPlant(new Position(x, y)));
                break;
            }
        }
    }


    private final AppView appView = AppView.getInstance();
    private final Scanner scanner = appView.getScanner();
    private final GameController gameController = GameController.getInstance();
    private  HouseController houseController = new HouseController();

    @Override
    public void checkCommand() {
        String input = scanner.nextLine().trim();
        if(input.matches("game new -u (?<usernames>.+?)")) {
            runCommand(GameCommand.startNewGame, input);
        }
        else if(input.matches("^\\s*Time\\s*$")){
            runCommand(GameCommand.showTime, "");
        }
        else if(input.matches("^\\s*Date\\s*$")){
            runCommand(GameCommand.showDate, "");
        }
        else if(input.matches("^\\s*DateTime\\s*$")){
            runCommand(GameCommand.showDateTime, "");
        }
        else if(input.matches("^\\s*day of the week\\s*$")){
            runCommand(GameCommand.showDayOfWeek, "");
        }
        else if(input.matches("^\\s*cheat advance time (?<X>\\d+)h\\s*$")){
            runCommand(GameCommand.cheatAdvanceTime, input);
        }
        else if(input.matches("^\\s*cheat advance date (?<X>\\d+)d\\s*$")){
            runCommand(GameCommand.cheatAdvanceDate, input);
        }
        else if(input.matches("^\\s*season\\s*$")){
            runCommand(GameCommand.season, "");
        }
        else if(input.matches("^\\s*next turn\\s*$")){
            runCommand(GameCommand.nextTurn, "");
        }
        else if(input.matches("^\\s*force terminate\\s*$")){
            runCommand(GameCommand.forceTerminate, "");
        }
        else if(input.matches("^walk -l (?<x>\\d+) (?<y>\\d+)$")) {
            runCommand(GameCommand.walk, input);
        }
        else if(input.matches("^print map -l (?<x>\\d+) (?<y>\\d+) -s (?<size>\\d+)$")) {
            runCommand(GameCommand.printMap, input);
        }
        else if(input.matches("^help reading map$")) {
            runCommand(GameCommand.helpPrintMap, input);
        }
        else if(input.matches("^\\s*exit game\\s*$")){
            runCommand(GameCommand.exitGame, "");
        }
        else if(input.matches("^\\s*energy show\\s*$")){
            runCommand(GameCommand.showEnergy,"");
        }
        else if(input.matches("^\\s*energy set -v (?<value>.+?)\\s*$")){
            runCommand(GameCommand.cheatEnergy,input);
        }
        else if(input.matches("^\\s*energy unlimited\\s*$")){
            runCommand(GameCommand.energyUnlimited,"");
        }
        else if(input.matches("^\\s*inventory trash -i (?<itemName>.+?) -n (?<number>.+?)\\s*$")){
            runCommand(GameCommand.inventoryTrash,input);
        }
        else if(input.matches("^weather$")){
            runCommand(GameCommand.weather, "");
        }
        else if(input.matches("^weather forecast$")){
            runCommand(GameCommand.weatherForecast, "");
        }
        else if(input.matches("^cheat weather set (?<type>.*)$")){
            runCommand(GameCommand.cheatWeatherForecast, input);
        }
        else if(input.matches("^greenhouse build$")){
            runCommand(GameCommand.buildGreenHouse, "");
        }
        else if(input.matches("^cheat Thor -l <(?<x>\\d+) , (?<y>\\d+)>$")){
            runCommand(GameCommand.cheatThor, input);
        }
        else if(input.matches("^\\s*go to house menu\\s*$")){
            runCommand(GameCommand.goToHouseMenu, "");
        }
        else if(input.matches("^\\s*cooking refrigerator (?<putOrPick>.+?) (?<item>.+?)\\s*$")){
            App app=App.getInstance();
            Game game=app.getCurrentGame();
            if(game.getInGameMenu() == InGameMenu.houseMenu){
                runCommand(GameCommand.cookingRefrigerator, input);
            }
        }
        else if(input.matches("^\\s*cooking show recipes\\s*$")) {
            App app = App.getInstance();
            Game game = app.getCurrentGame();
            if (game.getInGameMenu() == InGameMenu.houseMenu) {
                runCommand(GameCommand.showCookingRecipe, "");
            }
        }
        else if(input.matches("^\\s*cooking prepare (?<recipeName>.+?)\\s*$")) {
            App app = App.getInstance();
            Game game = app.getCurrentGame();
            if (game.getInGameMenu() == InGameMenu.houseMenu) {
                runCommand(GameCommand.cooking, input);
            }
        }
        else if(input.matches("^\\s*eat (?<foodName>.+?)\\s*$")) {
            App app = App.getInstance();
            Game game = app.getCurrentGame();
            if (game.getInGameMenu() == InGameMenu.houseMenu) {
                runCommand(GameCommand.eatFood, input);
            }
        }
        else if(input.matches("^\\s*build -a (?<buildingName>.+?) -l (?<x>\\d+) (?<y>\\d+)\\s*$")){
            App app = App.getInstance();
            Game game = app.getCurrentGame();
            if (game.getInGameMenu() == InGameMenu.houseMenu) {
                runCommand(GameCommand.buildBarn, input);
            }
        }
        else if(input.matches("^\\s*buy animal -a (?<animal>.+?) -n (?<name>.+?)\\s*$")){
            App app = App.getInstance();
            Game game = app.getCurrentGame();
            if (game.getInGameMenu() == InGameMenu.houseMenu) {
                runCommand(GameCommand.buyAnimal, input);
            }
        }
        else if(input.matches("^\\s*pet -n (?<name>.+?)\\s*$")){
            App app = App.getInstance();
            Game game = app.getCurrentGame();
            runCommand(GameCommand.petting, input);
        }
        else if(input.matches("cheat set friendship -n (?<animalName>.+?) -c (?<amount>.+?)")){
            runCommand(GameCommand.cheatAnimalFriendship, input);
        }
        else if(input.matches("^\\s*animals\\s*$")){
            runCommand(GameCommand.showAnimals, "");
        }
        else if(input.matches("^\\s*shepherd animals -n (?<animalName>.+?) -l (?<x>\\d+) (?<y>\\d+)\n\\s*$")){
            runCommand(GameCommand.shepherdAnimals, input);
        }
        else if(input.matches("^\\s*feed hay -n (?<animalName>.+?)\\s*$")){
            runCommand(GameCommand.feedHay, input);
        }
        else if(input.matches("fishing -p (?<fishingPole>.+?)")){
            runCommand(GameCommand.fishing, input);
        }
        else if(input.matches("^\\s*collect produce -n (?<name>.+?)\\s*$")){
            runCommand(GameCommand.collectProducts,input);
        }
        else if(input.matches("^\\s*sell animal -n (?<name>.+?)\\s*$")){
            runCommand(GameCommand.sellAnimal, input);
        }
        else if(input.matches("^craftinfo -n (?<name>.+?)$")) {
            runCommand(GameCommand.craftInfo, input);
        }
        else if(input.matches("^plant -s (?<seed>\\S+) -d (?<direction>\\S+)$")) {
            runCommand(GameCommand.plant, input);
        }
        else if(input.matches("^show plant <(?<x>\\d+) , (?<y>\\d+)>$")) {
            runCommand(GameCommand.showPlant, input);
        }
        else if(input.matches("^\\s*tools equip (?<toolName>.+)\\s*$")){
            runCommand(GameCommand.toolEquip, input);
        }
        else if(input.matches("^\\s*tools show current\\s*$")){
            runCommand(GameCommand.toolCurrentShow, input);
        }
        else if(input.matches("^\\s*tools show available\\s*$")){
            runCommand(GameCommand.toolShowAvailable, input);
        }
        else if(input.matches("^\\s*tools upgrade (?<toolName>.+)\\s*$")){
            runCommand(GameCommand.toolUpgrade, input);
        }
        else if(input.matches("^\\s*tools use -d (?<direction>.+)\\s*$")){
            runCommand(GameCommand.toolUse, input);
        }
    }
}
