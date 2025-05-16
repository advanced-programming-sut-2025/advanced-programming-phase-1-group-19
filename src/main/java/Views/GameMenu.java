package Views;

import Controllers.*;
import Modules.Animal.Animal;
import Modules.Animal.AnimalProduct;
import Modules.Animal.AnimalProductType;
import Modules.Animal.AnimalType;
import Modules.Farming.Seed;
import Modules.Farming.SeedType;
import Modules.App;
import Modules.Enums.InGameMenu;
import Modules.Game;
import Modules.Interactions.Commands.*;
import Modules.Interactions.Messages.*;
import Modules.Item;
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
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.cheatAdvanceTime(Integer.parseInt(matcher.group("X"))).message());
                break;
            }
            case cheatAdvanceDate: {
                Pattern pattern = Pattern.compile("^\\s*cheat advance date (?<X>\\d+)d\\s*$");
                Matcher matcher = pattern.matcher(input);

                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
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
            case loadGame: {
                System.out.println(gameController.loadGame().message());
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

                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                int x = Integer.parseInt(matcher.group("x"));
                int y = Integer.parseInt(matcher.group("y"));
                System.out.println(gameController.walk(new Position(x, y)).message());
                break;
            }
            case printMap: {
                Pattern pattern = Pattern.compile("^print map -l (?<x>\\d+) (?<y>\\d+) -s (?<size>\\d+)$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
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
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.cheatForecast(matcher.group("type")).message());
                break;
            }
            case buildGreenHouse:{
                System.out.println(gameController.buildGreenHouse().message());
                break;
            }
            case cheatThor:{
                Pattern pattern = Pattern.compile("^\\s*cheat Thor -l (?<x>\\d+) (?<y>\\d+)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.cheatThor(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y"))).message());
                break;
            }
            case showEnergy: {
                System.out.println(gameController.showEnergy().message());
                break;
            }
            case cheatEnergy: {
                String pattern="^\\s*energy set -v (?<value>.+?)\\s*$";
                Pattern pattern1 = Pattern.compile(pattern);
                Matcher matcher1 = pattern1.matcher(input);
                if(!matcher1.matches()) {
                    System.out.println("invalid command!");
                }
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
                if(!matcher1.matches()) {
                    System.out.println("invalid command!");
                }
                if(input.contains("-n")) {
                    System.out.println(gameController.inventoryTrash(matcher1.group("itemName"),Integer.parseInt(matcher1.group("number")) ,false).message());
                }
                else {
                    System.out.println(gameController.inventoryTrash(matcher1.group("itemName"), 0,true).message());

                }
                break;
            }
            case sellProduct:{
                Pattern pattern = Pattern.compile("\\s*sell (?<productName>.*) -n (?<count>\\d+)\\s*");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.shippingBinSell(matcher.group("productName"), Integer.parseInt(matcher.group("count"))).message());
                break;
            }
            case toolEquip:{
                Pattern pattern  = Pattern.compile("^\\s*tools equip (?<toolName>.+)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.equipTool(matcher.group("toolName")).message());
                break;
            }
            case toolCurrentShow:{
                Pattern pattern = Pattern.compile("^\\s*tools show current\\s*$");
                System.out.println(gameController.showCurrentTool().message());
                break;
            }
            case toolShowAvailable:{
                Pattern pattern = Pattern.compile("^\\s*tools show available\\s*$");
                System.out.println(gameController.showAllTools().message());
                break;
            }
            case toolUpgrade:{
                Pattern pattern = Pattern.compile("^\\s*tools upgrade (?<toolName>.+)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.upgradeTool(matcher.group("toolName")).message());
                break;
            }
            case backPackUpgrade:{
                Pattern pattern = Pattern.compile("\\s*upgrade backpack\\s*");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.upgradeBackPack().message());
                break;
            }
            case trashCanUpgrade:{
                Pattern pattern = Pattern.compile("\\s*upgrade trashcan\\s*");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.upgradeTrashCan().message());
                break;
            }
            case toolUse:{
                Pattern pattern = Pattern.compile("^\\s*tools use -d (?<direction>.+)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.useTool(Direction.getDirection(matcher.group("direction"))).message());
                break;
            }

            case goToHouseMenu:{
                System.out.println(gameController.openHouseMenu().message());
                break;
            }
            case cookingRefrigerator:{
                Pattern pattern = Pattern.compile("^\\s*cooking refrigerator (?<putOrPick>.+?) (?<item>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                if(matcher.group(1).equals("put")) {
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
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(houseController.cookingPrepare(matcher.group(1)).message());
                break;
            }
            case eatFood:{
                Pattern pattern=Pattern.compile("^\\s*eat (?<foodName>.+?)\\s*$");
                Matcher matcher=pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(houseController.eatFood(matcher.group(1)).message());
                break;
            }
            case buildBarn:{
                Pattern pattern=Pattern.compile("^\\s*build -a (?<buildingName>.+?) -l (?<x>\\d+) (?<y>\\d+)\\s*$");
                Matcher matcher=pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(houseController.buildBarn(matcher.group(1),Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)) ).message());
                break;
            }
            case buyAnimal:{
                Pattern pattern=Pattern.compile("^\\s*buy animal -a (?<animal>.+?) -n (?<name>.+?)\\s*$");
                Matcher matcher=pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(houseController.buyAnimal(matcher.group(1),matcher.group(2)).message());
                break;
            }
            case petting:{
                Pattern pattern=Pattern.compile("^\\s*pet -n (?<name>.+?)\\s*$");
                Matcher matcher=pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.petAnimal(matcher.group(1)).message());
                break;
            }
            case cheatAnimalFriendship:{
                Pattern pattern=Pattern.compile("cheat set friendship -n (?<animalName>.+?) -c (?<amount>.+?)");
                Matcher matcher=pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.cheatFriendship(matcher.group(1),Integer.parseInt(matcher.group(2)) ).message());
                break;
            }
            case showAnimals:{
                System.out.println(gameController.getFriendship().message());
                break;
            }
            case shepherdAnimals:{
                Pattern pattern=Pattern.compile("^\\s*shepherd animals -n (?<animalName>.+?) -l (?<x>\\d+) (?<y>\\d+)\\s*$");
                Matcher matcher=pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.shepherdAnimals(matcher.group("animalName"),Integer.parseInt(matcher.group("x")),Integer.parseInt(matcher.group("y")) ).message());
                break;
            }
            case feedHay:{
                Pattern pattern=Pattern.compile("^\\s*feed hay -n (?<animalName>.+?)\\s*$");
                Matcher matcher=pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.feedingHay(matcher.group(1)).message());
                break;
            }
            case fishing:{
                Pattern pattern=Pattern.compile("fishing -p (?<fishingPole>.+?)");
                Matcher matcher=pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.fishing(matcher.group(1)).message());
                break;
            }
            case collectProducts:{
                Pattern pattern=Pattern.compile("^\\s*collect produce -n (?<name>.+?)\\s*$");
                Matcher matcher=pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.collectProducts(matcher.group(1)).message());
                break;
            }
            case sellAnimal:{
                Pattern pattern=Pattern.compile("^\\s*sell animal -n (?<name>.+?)\\s*$");
                Matcher matcher=pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(gameController.sellAnimal(matcher.group(1)).message());
                break;
            }
            case craftInfo: {
                Pattern pattern = Pattern.compile("^craftinfo -n (?<name>.+)$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String name = matcher.group("name");
                System.out.println(gameController.craftInfo(name).message());
                break;
            }
            case plant: {
                Pattern pattern = Pattern.compile("^plant -s (?<seed>\\S+) -d (?<direction>\\S+)$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String seedName = matcher.group("seed");
                String directionName = matcher.group("direction");
                SeedType seed = SeedType.getSeedTypeOrNull(seedName);
                Direction direction = Direction.getDirection(directionName);
                System.out.println(gameController.plant(seed, direction).message());
                break;
            }
            case showPlant: {
                Pattern pattern = Pattern.compile("^show plant <(?<x>\\d+) , (?<y>\\d+)>$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String xString = matcher.group("x");
                String yString = matcher.group("y");
                int x = Integer.parseInt(xString);
                int y = Integer.parseInt(yString);
                System.out.println(gameController.showPlant(new Position(x, y)));
                break;
            }
            case howMuchWater:{
                System.out.println(gameController.howMuchWater().message());
                break;
            }
            case talk:{
                Pattern pattern = Pattern.compile("^\\s*talk -u (?<username>.+?) -m (?<message>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String username = matcher.group(1);
                String message = matcher.group(2);
                System.out.println(gameController.talk(username, message).message());
                break;
            }
            case showFriendships:{
                System.out.println(gameController.showFriendships().message());
                break;
            }
            case talkHistory:{
                Pattern pattern = Pattern.compile("^\\s*talk history -u (?<username>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String username = matcher.group("username");
                System.out.println(gameController.talkHistory(username).message());
                break;
            }
            case gifting:{
                Pattern pattern = Pattern.compile("^\\s*gift -u (?<username>.+?) -i (?<item>.+?) -a (?<amount>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String username = matcher.group("username");
                String item = matcher.group("item");
                int amount = Integer.parseInt(matcher.group("amount"));
                System.out.println(gameController.gifting(username, item, amount).message());
                break;
            }
            case ratingGift:{
                Pattern pattern = Pattern.compile("^\\s*gift rate -u (?<username>.+?) -i (?<giftNumber>.+?) -r (?<rate>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String username = matcher.group("username");
                int giftNumber = Integer.parseInt(matcher.group("giftNumber"));
                int rate = Integer.parseInt(matcher.group("rate"));
                System.out.println(gameController.ratingGift(username,giftNumber,rate).message());
                break;
            }
            case giftList:{
                Pattern pattern = Pattern.compile("^\\s*gift list\\s*$");
                System.out.println(gameController.listingGift().message());
                break;
            }
            case startTrade:{
                System.out.println(tradeController.startTrade().message());
                break;
            }
            case giftHistory:{
                Pattern pattern = Pattern.compile("^\\s*gift history -u (?<username>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String username = matcher.group("username");
                System.out.println(gameController.giftHistory(username).message());
                break;
            }
            case openCraftingMenu:{
                System.out.println(gameController.openCraftingMenu().message());
                break;
            }
            case closeHouseMenu:{
                System.out.println(gameController.closeHouseMenu().message());
                break;
            }
            case closeCraftingMenu:{
                System.out.println(gameController.closeCraftingMenu().message());
                break;
            }
            case trade:{
                Pattern pattern = Pattern.compile("^\\s*^trade\\s+-u\\s+(?<username>.+?)\\s+-t\\s+(?<type>.+?)\\s+-i\\s+(?<item>.+?)\\s+-a\\s+(?<amount>\\S+)(?:\\s+-p\\s+(?<price>.+?))?(?:\\s+-ti\\s+(?<targetItem>.+?)\\s+-ta\\s+(?<targetAmount>.+?))?$\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String username = matcher.group("username");
                String type = matcher.group("type");
                String item = matcher.group("item");
                int amount = Integer.parseInt(matcher.group("amount"));
                if(matcher.group("price") != null) {
                    System.out.println(tradeController.trading(username,type,item,amount,Integer.parseInt(matcher.group("price")),null,0).message());
                }
                else{
                    String targetItem = matcher.group("targetItem");
                    int targetAmount = Integer.parseInt(matcher.group("targetAmount"));
                    System.out.println(tradeController.trading(username,type,item,amount,0,targetItem,targetAmount).message());
                }
                break;
            }
            case tradeList:{
                System.out.println(tradeController.tradeList().message());
                break;
            }
            case showAllProduct:{
                Pattern pattern = Pattern.compile("^\\s*show all products\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(storeController.showAllProduct().message());
                break;
            }
            case showAvailableProduct:{
                Pattern pattern = Pattern.compile("^\\s*show available products\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(storeController.showAvailableProduct().message());
                break;
            }
            case purchaseItem:{
                Pattern pattern = Pattern.compile("^\\s*purchase (?<productName>.*) -n (?<count>.*)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(storeController.purchaseItem(matcher.group("productName").trim(),Integer.parseInt(matcher.group("count").trim())).message());
                break;
            }
            case purchaseItemAll: {
                Pattern pattern = Pattern.compile("^\\s*purchase (?<productName>.*)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(storeController.purchaseItem(matcher.group("productName").trim(),-1).message());
                break;
            }
            case cheatAddMoney:{
                Pattern pattern = Pattern.compile("^\\s*cheat add (?<countMoney>\\d+) dollars\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                if(matcher.matches()) {
                    System.out.println(storeController.cheatAddMoney(Integer.parseInt(matcher.group("countMoney").trim())).message());
                }
                break;
            }
            case sellItem:{
                Pattern pattern = Pattern.compile("^\\s*sell (?<productName>.*) -n (?<count>.*)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(storeController.sellItem(matcher.group("productName").trim(),Integer.parseInt(matcher.group("count").trim())).message());
                break;
            }
            case showCraftingRecipes:{
                CraftingController craftingController = CraftingController.getInstance();
                System.out.println(craftingController.showCraftingRecipe().message());
                break;
            }
            case crafting:{
                Pattern pattern = Pattern.compile("^\\s*crafting craft (?<itemName>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                CraftingController craftingController = CraftingController.getInstance();
                System.out.println(craftingController.crafting(matcher.group("itemName").trim()).message());
                break;
            }
            case placeItem:{
                Pattern pattern = Pattern.compile("^\\s*place item -n (?<itemName>.+?) -d (?<direction>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String itemName = matcher.group("itemName");
                String direction = matcher.group("direction");
                System.out.println(gameController.placeItem(itemName,direction).message());
                break;
            }
            case cheatAddItem:{
                Pattern pattern = Pattern.compile("^\\s*cheat add item -n (?<itemName>.+?) -c (?<count>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String itemName = matcher.group("itemName");
                int count = Integer.parseInt(matcher.group("count"));
                System.out.println(gameController.cheatAddItem(itemName,count).message());
                break;
            }
            case askMarriage:{
                Pattern pattern = Pattern.compile("^\\s*ask marriage -u (?<username>.+?) -r (?<ring>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String username = matcher.group("username");
                System.out.println(gameController.marriage(username).message());
                break;
            }
            case showMarriageRequests:{
                System.out.println(gameController.marriageRequests().message());
                break;
            }
            case answerMarriage:{
                Pattern pattern = Pattern.compile("^\\s*respond (?<acceptOrRejec>.+?) -u (?<username>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String username = matcher.group("username");
                if(matcher.group("acceptOrRejec").equals("accept")) {
                    System.out.println(gameController.answeringMarriage(username,true).message());
                }
                else if(matcher.group("acceptOrRejec").equals("reject")) {
                    System.out.println(gameController.answeringMarriage(username,false).message());
                }
                else {
                    System.out.println("invalid answer!");
                }
                break;
            }
            case hug:{
                Pattern pattern = Pattern.compile("^\\s*hug -u (?<username>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String username = matcher.group("username");
                System.out.println(gameController.hugging(username).message());
                break;
            }
            case enterStore:{
                Pattern pattern = Pattern.compile("^\\s*enter store -n (?<storeName>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String storeName = matcher.group("storeName");
                System.out.println(gameController.enterStore(storeName).message());
                break;
            }
            case exitStore:{
                Pattern pattern = Pattern.compile("^\\s*exit store -n (?<storeName>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String storeName = matcher.group("storeName");
                System.out.println(gameController.exitStore(storeName).message());
                break;
            }
            case buyRecipe:{
                Pattern pattern = Pattern.compile("^\\s*buy recipe (?<recipeName>.+?) -n (?<count>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String recipeName = matcher.group("recipeName");
                int count = Integer.parseInt(matcher.group("count"));
                System.out.println(storeController.buyRecipe(recipeName,count).message());
                break;
            }
            case cheatAddRecipe:{
                Pattern pattern = Pattern.compile("^\\s*cheat add recipe -n (?<recipeName>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String recipeName = matcher.group("recipeName");
                System.out.println(gameController.cheatAddRecipe(recipeName).message());
                break;
            }
            case showAnimalProducts:{
                System.out.println(gameController.showProducts().message());
                break;
            }
            case givingFlower:{
                Pattern pattern = Pattern.compile("^\\s*flower -u (?<userName>.+?)\\s*$");
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                String userName = matcher.group("userName");
                System.out.println(gameController.givingFlower(userName).message());
            }
            case Hay:{
                System.out.println(gameController.showHay().message());
                break;
            }
        }
    }


    private final AppView appView = AppView.getInstance();
    private final Scanner scanner = appView.getScanner();
    private final GameController gameController = GameController.getInstance();
    private final StoreController storeController = StoreController.getInstance();
    private final TradeController tradeController = TradeController.getInstance();
    private final CraftingController craftingController = CraftingController.getInstance();

    private  HouseController houseController = new HouseController();

    @Override
    public void checkCommand() {
        String input = scanner.nextLine().trim();
        if(input.matches("game new -u (?<usernames>.+?)")) {
            runCommand(GameCommand.startNewGame, input);
        }
        else if(input.matches("^\\s*time\\s*$")){
            runCommand(GameCommand.showTime, "");
        }
        else if(input.matches("^\\s*date\\s*$")){
            runCommand(GameCommand.showDate, "");
        }
        else if(input.matches("^\\s*datetime\\s*$")){
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
        else if(input.matches("^load game$")) {
            runCommand(GameCommand.loadGame, input);
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
        else if(input.matches("^\\s*inventory show\\s*$")){
            runCommand(GameCommand.inventoryShow, input);
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
        else if(input.matches("^\\s*cheat Thor -l (?<x>\\d+) (?<y>\\d+)\\s*$")){
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
        else if(input.matches("^\\s*shepherd animals -n (?<animalName>.+?) -l (?<x>\\d+) (?<y>\\d+)\\s*$")){
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
        else if(input.matches("\\s*upgrade backpack\\s*")){
            runCommand(GameCommand.backPackUpgrade, input);
        }
        else if(input.matches("\\s*upgrade trashcan\\s*")){
            runCommand(GameCommand.trashCanUpgrade, input);
        }
        else if(input.matches("^\\s*tools use -d (?<direction>.+)\\s*$")){
            runCommand(GameCommand.toolUse, input);
        }
        else if(input.matches("\\s*sell (?<productName>.*) -n (?<count>\\d+)\\s*")){
            runCommand(GameCommand.sellProduct, input);
        }
        else if(input.matches("\\s*how much water\\s*")){
            runCommand(GameCommand.howMuchWater, input);
        }
        else if(input.matches("start trade")){
            runCommand(GameCommand.startTrade,"");
        }
        else if(input.matches("^\\s*^trade\\s+-u\\s+(\\S+)\\s+-t\\s+(\\S+)\\s+-i\\s+(\\S+)\\s+-a\\s+(\\S+)(?:\\s+-p\\s+(\\S+))?(?:\\s+-ti\\s+(\\S+)\\s+-ta\\s+(\\S+))?$\\s*$")){
            runCommand(GameCommand.trade,input);
        }
        else if(input.matches("trade list")){
            runCommand(GameCommand.tradeList,"");
        }
        else if(input.matches("^\\s*show all products\\s*$")){
            runCommand(GameCommand.showAllProduct, input);
        }
        else if(input.matches("^\\s*show all available products\\s*$")){
            runCommand(GameCommand.showAvailableProduct, input);
        }
        else if(input.matches("^\\s*purchase (?<productName>.*) -n (?<count>.*)\\s*$")){
            runCommand(GameCommand.purchaseItem, input);
        }
        else if(input.matches("^\\s*purchase (?<productName>.*)\\s*$")){
            runCommand(GameCommand.purchaseItemAll, input);
        }
        else if(input.matches("^\\s*cheat add (?<count>\\d+) dollars\\s*$")){
            runCommand(GameCommand.cheatAddMoney, input);
        }
        else if(input.matches("^\\s*sell (?<productName>.*) -n (?<count>.*)\\s*$")){
            runCommand(GameCommand.sellItem, input);
        }
        else if(input.matches("^\\s*talk -u (?<username>.+?) -m (?<message>.+?)\\s*$")){
            runCommand(GameCommand.talk,input);
        }
        else if(input.matches("^\\s*talk history -u (?<username>.+?)\\s*$")){
            runCommand(GameCommand.talkHistory,input);
        }
        else if(input.matches("^\\s*gift -u (?<username>.+?) -i (?<item>.+?) -a (?<amount>.+?)\\s*$")){
            runCommand(GameCommand.gifting,input);
        }
        else if(input.matches("^\\s*gift list\\s*$")) {
            runCommand(GameCommand.giftList, input);
        }
        else if(input.matches("^\\s*gift rate -u (?<username>.+?) -i (?<giftNumber>.+?) -r (?<rate>.+?)\\s*$")){
            runCommand(GameCommand.ratingGift,input);
        }
        else if(input.matches("^\\s*gift history -u (?<username>.+?)\\s*$")){
            runCommand(GameCommand.giftHistory,input);
        }
        else if(input.matches("^\\s*friendships\\s*$")){
            runCommand(GameCommand.showFriendships,"");
        }
        else if(input.matches("^\\s*open crafting menu\\s*$")){
            runCommand(GameCommand.openCraftingMenu,"");
        }
        else if(input.matches("^\\s*close house menu\\s*$")){
            runCommand(GameCommand.closeHouseMenu,"");
        }
        else if(input.matches("^\\s*close crafting menu\\s*$")){
            runCommand(GameCommand.closeCraftingMenu,"");
        }
        else if(input.matches("^\\s*crafting show recipes\\s*$")){
            runCommand(GameCommand.showCraftingRecipes,"");
        }
        else if(input.matches("^\\s*crafting craft (?<itemName>.+?)\\s*$")){
            runCommand(GameCommand.crafting,input);
        }
        else if(input.matches("^\\s*place item -n (?<itemName>.+?) -d (?<direction>.+?)\\s*$")){
            runCommand(GameCommand.placeItem,input);
        }
        else if(input.matches("^\\s*cheat add item -n (?<itemName>.+?) -c (?<count>.+?)\\s*$")){
            runCommand(GameCommand.cheatAddItem,input);
        }
        else if(input.matches("show position")){
            App app = App.getInstance();
            Game game = app.getCurrentGame();
            Player player = game.getCurrentPlayer();
            System.out.println(player.getPosition().x + " " + player.getPosition().y);
        }
        else if(input.matches("^\\s*ask marriage -u (?<username>.+?) -r (?<ring>.+?)\\s*$")){
            runCommand(GameCommand.askMarriage,input);
        }
        else if(input.matches("^\\s*respond (?<acceptOrRejec>.+?) -u (?<username>.+?)\\s*$")){
            runCommand(GameCommand.answerMarriage,input);
        }
        else if(input.matches("^\\s*show marriage requests\\s*$")){
            runCommand(GameCommand.showMarriageRequests,input);
        }
        else if(input.matches("^\\s*hug -u (?<username>.+?)\\s*$")){
            runCommand(GameCommand.hug,input);
        }
        else if(input.matches("^\\s*enter store -n (?<storeName>.+?)\\s*$")){
            runCommand(GameCommand.enterStore,input);
        }
        else if(input.matches("^\\s*exit store -n (?<storeName>.+?)\\s*$")){
            runCommand(GameCommand.exitStore,input);
        }
        else if(input.matches("^\\s*buy recipe (?<recipeName>.+?) -n (?<count>.+?)\\s*$")){
            runCommand(GameCommand.buyRecipe,input);
        }
        else if(input.matches("^\\s*cheat add recipe -n (?<recipeName>.+?)\\s*$")){
            runCommand(GameCommand.cheatAddRecipe,input);
        }
        else if(input.matches("^\\s*produces\\s*$")){
            runCommand(GameCommand.showAnimalProducts,"");
        }
        else if(input.matches("^\\s*flower -u (?<userName>.+?)\\s*$")){
            runCommand(GameCommand.givingFlower,input);
        }
        else if(input.matches("^\\s*show hay\\s*$")){
            runCommand(GameCommand.Hay,"");
        }
        else{
            System.out.println("invalid command");
        }
    }
}
