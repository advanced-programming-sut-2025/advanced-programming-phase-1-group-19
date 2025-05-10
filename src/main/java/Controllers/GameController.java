package Controllers;

import Modules.*;
import Modules.Animal.*;
import Modules.Communication.NPC;
import Modules.Enums.*;
import Modules.Interactions.Messages.*;
import Modules.Enums.Menu;
import Modules.Enums.Season;
import Modules.Enums.Weather;
import Modules.Farming.*;
import Modules.Interactions.Messages.GameMessage;
import Modules.Interactions.Messages.Message;
import Modules.Map.*;
import Modules.Tools.Tool;
import Modules.Tools.ToolType;
import Modules.Tools.BackPack;
import Modules.Tools.Tool;
import Modules.Tools.WateringCan;
import Views.*;
import Modules.Tools.BackPack;
import Views.GameMenu;

import java.util.ArrayList;
import java.util.Random;

public class GameController extends Controller {
    private static GameController instance;

    private GameController() {
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    @Override
    public GameMessage showCurrentMenu() {
        App app = App.getInstance();
        app.setCurrentMenu(Menu.MainMenu);
        return new GameMessage(null, "You are now in main menu");
    }

    private final App app = App.getInstance();

    private boolean hasActiveGame(User user) {
        return user.getCurrentGame() != null;
    }

    private boolean isMapIdValid(int mapId) {
    }

    private boolean hasEnoughEnergy(int amount) {
    }

    public GameMessage startNewGame(String[] usernames) {
        if (usernames.length > 3) {
            return new GameMessage(null, "Too many players!");
        }
        User[] users = new User[usernames.length + 1];
        users[0] = app.getCurrentUser();
        for (int i = 0; i < usernames.length; i++) {
            User user = app.getUserByUsername(usernames[i]);
            if (user == null) {
                return new GameMessage(null, "User " + usernames[i] + " not found!");
            }
            if (hasActiveGame(user)) {
                return new GameMessage(null, "User " + usernames[i] + " is already in a game!");
            }
            users[i + 1] = user;
        }
        GameMenu menu = GameMenu.getInstance();
        String mapsPreview = "Users are set successfully, here are the available maps.\n";
        for (FarmMap farmMap : FarmMap.values()) {
            mapsPreview += farmMap.name() + " map:\n";
            mapsPreview += farmMap.printMap() + "\n";
        }
        mapsPreview += "Press ENTER to continue...";
        menu.getSingleLine(mapsPreview);
        int[] mapIDs = new int[users.length];
        String error = "";
        for (int i = 0; i < users.length; i++) {
            String idString = menu.getSingleLine(error + "choose map for user " + users[i].getUsername());
            error = "";
            if (!idString.matches("[1-4]")) {
                error = "Wrong format, first map was selected!\n";
                mapIDs[i] = 1;
            } else {
                mapIDs[i] = Integer.parseInt(idString);
            }
        }
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Farm> farms = new ArrayList<>();
        for (int i = 0; i < users.length; i++) {
            Farm farm = new Farm(FarmMap.getFarmMap(mapIDs[i]), i);
            Player player = new Player(users[i], farm);
            farms.add(farm);
            players.add(player);
        }
        Map map = new Map(farms);
        Game game = new Game(players, map);

        app.addGame(game);
        app.setCurrentGame(game);
        app.setCurrentGameStarter(app.getCurrentUser());

        for (User user : users) {
            user.setCurrentGame(game);
        }

        return new GameMessage(null, error + "New game created successfully!!!");
    }

    public GameMessage loadGame() {
    }

    public GameMessage exitGame() {
        App app = App.getInstance();
        if (!app.getCurrentGameStarter().equals(app.getCurrentUser())) {
            return new GameMessage(null, "you can't exit game!");
        }
        // TODO: save game
        app.setCurrentGame(null);
        return new GameMessage(null, "exited game successfully!");
    }

    public GameMessage forceTerminate() {
        GameMenu gameMenu = GameMenu.getInstance();
        boolean quit = true;
        for (int i = 0; i < App.getInstance().getCurrentGame().getPlayers().size() - 1; i++) {
            String input;
            do {
                input = gameMenu.getSingleLine("");
                if (input.matches("^\\s*n\\s*$")) {
                    quit = false;
                } else if (input.matches("^\\s*y\\s*$")) {

                } else {
                    System.out.println("invalid vote");
                }
            }
            while (!input.matches("^\\s*n\\s*$") && !input.matches("^\\s*y\\s*$"));
        }
        if (quit) {
            for (Player player : App.getInstance().getCurrentGame().getPlayers()) {
                player.getUser().setCurrentGame(null);
            }
            App.getInstance().getGames().remove(App.getInstance().getCurrentGame());
            App.getInstance().setCurrentGame(null);
            App.getInstance().setCurrentGameStarter(null);
            return new GameMessage(null, "Game has been terminated!");
        } else {
            return new GameMessage(null, "Not enough votes");
        }
    }

    public GameMessage nextTurn() {
        Game game = App.getInstance().getCurrentGame();
        do {
            game.setNextPlayer();
            game.setInGameMenu(null);
        } while (game.getCurrentPlayer().isFainted());
        return new GameMessage(null, "next turn");
    }

    public GameMessage showTime() {
        int hour = App.getInstance().getCurrentGame().getTime().getHour();
        return new GameMessage(null, String.valueOf(hour));
    }

    public GameMessage showDate() {
        Time time = App.getInstance().getCurrentGame().getTime();
        int date = time.getDay();
        Season season = time.getSeason();
        return new GameMessage(null, date + " " + season.toString());
    }

    public GameMessage showDateTime() {
        Time time = App.getInstance().getCurrentGame().getTime();
        int hour = time.getHour();
        int date = time.getDay();
        Season season = time.getSeason();
        return new GameMessage(null, hour + " " + date + " " + season.toString());
    }

    public GameMessage showDayOfWeak() {
        return new GameMessage(null, App.getInstance().getCurrentGame().getTime().calculateWeekDay());
    }

    public GameMessage showSeason() {
        Time time = App.getInstance().getCurrentGame().getTime();
        Season season = time.getSeason();
        return new GameMessage(null, season.toString());
    }

    public GameMessage cheatAdvanceTime(int x) {
        for (int i = 0; i < x; i++)
            App.getInstance().getCurrentGame().nextHour();
        return new GameMessage(null, "time traveled " + x + " hours");
    }

    public GameMessage cheatAdvanceDate(int x) {
        for (int i = 0; i < 22 * x; i++)
            App.getInstance().getCurrentGame().nextHour();
        return new GameMessage(null, "time traveled " + x + " days");
    }

    public GameMessage cheatThor(int x, int y) {
        App.getInstance().getCurrentGame().thor(new Position(x, y));
        return new GameMessage(null, "you thor " + x + " " + y);
    }

    public GameMessage showWeather() {
        return new GameMessage(null, "today weather is : " + App.getInstance().getCurrentGame().getTodayWeather());
    }

    public GameMessage weatherForecast() {
        return new GameMessage(null, "tomorrow weather is forecasted : " + App.getInstance().getCurrentGame().getTomrrowWeather());
    }

    public GameMessage walk(Position end) {
        Game game = app.getCurrentGame();
        Map map = game.getMap();
        Player player = game.getCurrentPlayer();
        Position start = player.getPosition();
        if (start.equals(end)) {
            return new GameMessage(null, "Dude you are already there :/");
        }
        ArrayList<Tile> path = map.getPath(start, end);
        if (path == null) {
            return new GameMessage(null, "Ops, sorry you cant go there");
        }
        int moves = 0;
        String faintMessage = "";
        for (Tile tile : path) {
            player.setPosition(tile.getPosition());
            moves++;
            if (moves % 20 == 0) {
//                TODO: reduce energy by one;
//                TODO: go to next person and faint if energy == 0
//                TODO: set faintMessage to "Oh you have fainted middle way :(\n"
            }
        }
        return new GameMessage(null, faintMessage + "Your current position is (" + player.getPosition().x + ", " + player.getPosition().y + ")");
    }

    public GameMessage cheatForecast(String weather) {
        if (Weather.getWeatherType(weather) == null) {
            return new GameMessage(null, "not such weather type");
        }
        App.getInstance().getCurrentGame().setTomorrowWeather(Weather.getWeatherType(weather));
        return new GameMessage(null, "you successfully cheated weather forecast!");
    }

    public GameMessage showEnergy() {
        App app = App.getInstance();
        return new GameMessage(null, "Your energy is: " + app.getCurrentGame().getCurrentPlayer().getEnergy().getAmount());
    }

    public GameMessage showInventory() {
        StringBuilder stringBuilder = new StringBuilder();
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        for (java.util.Map.Entry<Item, Integer> entry : player.getBackPack().getItems().entrySet()) {
            Item item = entry.getKey();
            Integer value = entry.getValue();
            stringBuilder.append("Item: ").append(item.getName())
                    .append(", Quantity: ").append(value)
                    .append("\n");
        }
        return new GameMessage(null, "Your BackPack: \n" + stringBuilder.toString());
    }

    public GameMessage inventoryTrash(String itemName, int number, boolean delete) {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        if (delete) {
            for (java.util.Map.Entry<Item, Integer> entry : player.getBackPack().getItems().entrySet()) {
                Item item = entry.getKey();
                Integer amount = entry.getValue();
                if (item.getName().equals(itemName)) {
                    // TODO: add refund
                    player.addMoney((int)(item.getPrice() * amount * player.getTrashCan().calcRatio()));
                    player.getBackPack().getItems().remove(item);
                    break;
                }
            }
            return new GameMessage(null, "You fully trashed item " + itemName);
        }
        for (java.util.Map.Entry<Item, Integer> entry : player.getBackPack().getItems().entrySet()) {
            Item item = entry.getKey();
            Integer amount = entry.getValue();
            if (item.getName().equals(itemName)) {
                // TODO: add refund
                if(amount >= number) {
                    player.getBackPack().getItems().put(item, amount - number);
                    // TODO: is the code above true?
                    player.addMoney((int)(item.getPrice() * amount * player.getTrashCan().calcRatio()));
                    return new GameMessage(null, "You successfully trashed " + number + " of " + itemName);
                }
                else{
                    return new GameMessage(null, "you don't have enough of this product!")
                }
            }
        }
        return new GameMessage(null, "You don't have this product!");
//        for (Item item : player.getBackPack().getItems().keySet()) {
//            if (item.getName().equals(itemName)) {
//                //TODO: add refund!
//                int currentQty = player.getBackPack().getItems().get(item);
//                if (currentQty > number) {
//                    player.getBackPack().getItems().put(item, currentQty - number);
//                } else {
//                    player.getBackPack().getItems().remove(item);
//                }
//                break;
//            }
//        }
//        return new GameMessage(null, "You successfully trashed " + number + " of " + itemName);
    }

    public GameMessage cheatEnergySet(int amount) {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        player.addEnergy(amount - player.getEnergy().getAmount());
        return new GameMessage(null, "New energy set to " + amount);
    }

    public GameMessage unlimitedEnergySet() {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        player.getEnergy().setUnlimited(true);
        return new GameMessage(null, "Now your energy is unlimited");
    }

    public GameMessage buildGreenHouse() {
//        TODO: check if we have enough coin and wood!

    }

    public GameMessage equipTool(String toolName) {
        BackPack backPack = App.getInstance().getCurrentGame().getCurrentPlayer().getBackPack();
        boolean toolFound = false;
        for (java.util.Map.Entry<Item, Integer> entry : backPack.getItems().entrySet()) {
            Item item = entry.getKey();
            // only one tool of each type!
            if (item.getName().equals(toolName)) {
                App.getInstance().getCurrentGame().getCurrentPlayer().setCurrentTool((Tool) item);
                toolFound = true;
                break;
            }
        }
        if (!toolFound) {
            return new GameMessage(null, "you don't have that tool!");
        } else {
            return new GameMessage(null, "You have equipped " + toolName);
        }
    }

    public GameMessage showCurrentTool() {
        Tool tool = App.getInstance().getCurrentGame().getCurrentPlayer().getCurrentTool();
        if (tool == null) {
            return new GameMessage(null, "you don't have equipped tool!");
        } else {
            return new GameMessage(null, "You have equipped " + tool.getName());
        }
    }

    public GameMessage showAllTools() {
        BackPack backPack = App.getInstance().getCurrentGame().getCurrentPlayer().getBackPack();
        StringBuilder stringBuilder = new StringBuilder("All tools: \n");
        for (java.util.Map.Entry<Item, Integer> entry : backPack.getItems().entrySet()) {
            Item item = entry.getKey();
            if (item instanceof Tool tool) {
                stringBuilder.append("Tool: ").append(tool.getName()).append("\n");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return new GameMessage(null, stringBuilder.toString());
    }

    public GameMessage upgradeTool(String toolName) {
        // TODO:check if in blacksmith!
        // TODO: check if enough money and energy
    }

    public GameMessage useTool(String direction) {

        Tool tool = App.getInstance().getCurrentGame().getCurrentPlayer().getCurrentTool();
        switch (direction) {
            case "u": {

                break;
            }
            case "d": {

                break;
            }
            case "l": {

                break;
            }
            case "r": {
                break;
            }
            case "ur": {
                break;
            }
            case "ul": {
                break;
            }
            case "dr": {
                break;
            }
            case "dl": {
                break;
            }
            default: {

            }
        }
    }

    public GameMessage howMuchWater() {
        BackPack backPack = App.getInstance().getCurrentGame().getCurrentPlayer().getBackPack();
        WateringCan wateringCan = (WateringCan) backPack.getToolByType("wateringCan");
        return new GameMessage(null, "you have " + wateringCan.getCurrentCapacity() + " water in watering can");
    }

    public GameMessage printMap(Position position, int size) {
        if (size > 80) {
            return new GameMessage(null, "please use sizes smaller than 100");
        }
        Game game = App.getInstance().getCurrentGame();
        Map map = (Map) game.getMap();
        char[][] all = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char c = ' ';
                Tile tile = map.getTile(new Position(position.x + i, position.y + j));
                if (tile != null) {
                    Building building = tile.getBuilding();
                    if (building == null) {
                        c = '.';
                    } else if (building instanceof House) {
                        c = 'H';
                    } else if (building instanceof GreenHouse) {
                        c = 'G';
                    } else if (building instanceof Lake) {
                        c = 'L';
                    } else if (building instanceof Quarry) {
                        c = 'Q';
                    }
                }
                all[i][j] = c;
            }
        }
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ret.append(all[i][j]);
            }
            ret.append("\n");
        }
        return new GameMessage(null, ret.toString());
    }

    public GameMessage helpPrintMap() {
        String ret = "";
        ret += ". ~> empty tiles\n";
        ret += "\u001B[35mH\u001B[0m ~> house tiles\n";
        ret += "\u001B[32mG\u001B[0m ~> green house tiles\n";
        ret += "\u001B[34mL\u001B[0m ~> lake tiles\n";
        ret += "\u001B[33mQ\u001B[0m ~> quarry tiles\n";

//        TODO: fix this if needed to show other stuff
        return new GameMessage(null, ret);
    }

    public GameMessage openHouseMenu() {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
//        TODO:check if the player is near to the house or not
        Game game = app.getCurrentGame();
        game.setInGameMenu(InGameMenu.houseMenu);
        return new GameMessage(null, "You opened house menu");
    }

    public GameMessage petAnimal(String animalName) {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Animal animal1 = player.getFarm().getBarn().getAnimalByName(animalName);
        Animal animal2 = player.getFarm().getBarn().getAnimalByName(animalName);
        if (animal1 == null && animal2 == null) {
            return new GameMessage(null, "There is no such animal");
        }
        if (animal1 != null && Math.abs(player.getPosition().x - animal1.getPosition().x) <= 1 && Math.abs(player.getPosition().y - animal1.getPosition().y) <= 1) {
            animal1.increaseFriendship(15);
            animal1.setLastPettingTime(app.getCurrentGame().getTime());
            return new GameMessage(null, "You successfully pet " + animalName);
        }

        if (animal2 != null && Math.abs(player.getPosition().x - animal2.getPosition().x) <= 1 && Math.abs(player.getPosition().y - animal2.getPosition().y) <= 1) {
            animal2.increaseFriendship(15);
            animal2.setLastPettingTime(app.getCurrentGame().getTime());
            return new GameMessage(null, "You successfully pet " + animalName);
        }

        return new GameMessage(null, "You do not have access to this animal");
    }

    public GameMessage cheatFriendship(String animalName, int amount) {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getFarm().getBarn().getAnimalByName(animalName);
        if (animal == null) {
            return new GameMessage(null, "There is no such animal");
        }
        animal.setFriendship(amount);
        return new GameMessage(null, "You successfully set your friendship : " + amount);
    }

    public GameMessage getFriendship() {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        boolean hasBeenPetToday = false;
        boolean hasBeenFedToday = false;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You have :\n");
        if (player.getFarm().getBarn() != null) {
            stringBuilder.append("Barn Animals:\n");
            for (Animal animal1 : player.getFarm().getBarn().getAnimals()) {
                stringBuilder.append(animal1.getName() + "\n");
                hasBeenPetToday = game.getTime().getDay() == animal1.getLastPetingTime().getDay() &&
                        game.getTime().getSeason() == animal1.getLastPetingTime().getSeason();
                if (hasBeenPetToday) {
                    stringBuilder.append("Has Your Animal Been Pet Today : true\n\n");
                } else {
                    stringBuilder.append("Has Your Animal Been Pet Today : false\n\n");
                }
            }
        }
        if (player.getFarm().getCoop() != null) {
            stringBuilder.append("Coop Animals:\n");
            for (Animal animal1 : player.getFarm().getBarn().getAnimals()) {
                stringBuilder.append(animal1.getName() + "\n");
                hasBeenFedToday = game.getTime().getDay() == animal1.getLastFeedingTime().getDay() &&
                        game.getTime().getSeason() == animal1.getLastFeedingTime().getSeason();

                if (hasBeenFedToday) {
                    stringBuilder.append("Has Your Animal Been Fed Today : true\n\n");
                } else {
                    stringBuilder.append("Has Your Animal Been Fed Today : false\n\n");
                }
            }
        }

        return new GameMessage(null, stringBuilder.toString());
    }

    public GameMessage shepherdAnimals(String animalName, int x, int y) {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getFarm().getBarn().getAnimalByName(animalName);
        if (animal == null) {
            animal = player.getFarm().getCoop().getAnimalByName(animalName);
        }
        if (animal == null) {
            return new GameMessage(null, "There is no such animal");
        }
        if (!animal.isOutside()) {
            if (game.getTodayWeather() != Weather.sunny) {
                return new GameMessage(null, "The animal can not go outside in this weather");
            }
            animal.setOutside(true);
            animal.setPosition(new Position(x, y));
            return new GameMessage(null, "The animal successfully moved");
        } else {
            if (animal.getType().isInCage()) {
                animal.setPosition(player.getFarm().getCoop().getPlacedTile().getPosition());
                animal.setOutside(false);
                return new GameMessage(null, "The animal entered the cage");
            } else {
                animal.setPosition(player.getFarm().getBarn().getPlacedTile().getPosition());
                animal.setOutside(true);
                animal.setLastFeedingTime(game.getTime());
                animal.increaseFriendship(8);
                return new GameMessage(null, "The animal entered the barn");
            }

        }
    }

    public GameMessage feedingHay(String animalName) {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getFarm().getBarn().getAnimalByName(animalName);
        if (animal == null) {
            animal = player.getFarm().getCoop().getAnimalByName(animalName);
        }
        if (animal == null) {
            return new GameMessage(null, "There is no such animal");
        }
        if (animal.isOutside()) {
            return new GameMessage(null, "The animal must be in the coop to feed hay");
        }
        animal.setLastFeedingTime(game.getTime());
        return new GameMessage(null, "The animal successfully fed");
    }

    public GameMessage showProducts() {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        StringBuilder stringBuilder = new StringBuilder();
        if (player.getFarm().getBarn() != null) {
            for (Animal animal : player.getFarm().getBarn().getAnimals()) {
                if (animal.doesProduce()) {
                    stringBuilder.append(animal.getName() + " Produces:  ");
                    stringBuilder.append(animal.whichProduct().getName());
                    stringBuilder.append("\n");
                }
            }
        }
        if (player.getFarm().getCoop() != null) {
            for (Animal animal : player.getFarm().getCoop().getAnimals()) {
                if (animal.doesProduce()) {
                    stringBuilder.append(animal.getName() + " Produces:  ");
                    stringBuilder.append(animal.whichProduct().getName());
                    stringBuilder.append("\n");
                }
            }
        }
        return new GameMessage(null, stringBuilder.toString());
    }

    public GameMessage collectProducts(String animalName) {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getFarm().getBarn().getAnimalByName(animalName);
        if (animal == null) {
            animal = player.getFarm().getCoop().getAnimalByName(animalName);
        }
        if (animal == null) {
            return new GameMessage(null, "There is no such animal");
        }
        if (animal.getCurrentProduct() != null) {
            if (animal.getName().equals("cow") || animal.getName().equals("goat")) {
//               TODO:check if there is watering can in inventory
                player.decreaseEnergy(4);
                if (player.getBackPack().getCapacity() == 0) {
                    return new GameMessage(null, "There is no space in your backpack");
                }

                player.getBackPack().addItem(animal.getCurrentProduct(), 1);
                animal.setCurrentProduct(null);
                return new GameMessage(null, animal.getCurrentProduct().getName() + " collected");
            } else if (animal.getName().equals("sheep")) {
//               TODO:check if there is gheuchi in inventory
                player.decreaseEnergy(4);
                if (player.getBackPack().getCapacity() == 0) {
                    return new GameMessage(null, "There is no space in your backpack");
                }
                player.getBackPack().addItem(animal.getCurrentProduct(), 1);
                animal.setCurrentProduct(null);
                return new GameMessage(null, animal.getCurrentProduct().getName() + " collected");
            } else if (animal.getName().equals("pig")) {
                if (animal.isOutside()) {
                    if (player.getBackPack().getCapacity() == 0) {
                        return new GameMessage(null, "There is no space in your backpack");
                    }
                    player.getBackPack().addItem(animal.getCurrentProduct(), 1);
                    animal.setCurrentProduct(null);
                    return new GameMessage(null, animal.getCurrentProduct().getName() + " collected");
                }
            }
        }
        return null;
    }

    public GameMessage sellAnimal(String animalName) {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getFarm().getBarn().getAnimalByName(animalName);
        if (animal != null) {
            int price = animal.calSellingPrice();
//            TODO:increase player money
            player.addMoney(price);
            player.getFarm().getBarn().getAnimals().remove(animal);
            return new GameMessage(null, animal.getCurrentProduct().getName() + " sold");
        } else {
            animal = player.getFarm().getCoop().getAnimalByName(animalName);
            if (animal != null) {
                int price = animal.calSellingPrice();
//                TODO:increase player money
                player.addMoney(price);
                player.getFarm().getCoop().getAnimals().remove(animal);
                return new GameMessage(null, animal.getCurrentProduct().getName() + " sold");
            }
            return new GameMessage(null, "There is no such animal");
        }
    }

    public GameMessage meetNpc(String npcName) {
        // TODO: check if it's near!
        NPC npc = NPC.getNPCByName(npcName);
        if(npc == null) {
            return new GameMessage(null, "There is no such npc");
        }
        return new GameMessage(null, npc.getDialogue());
    }

    public GameMessage questNpc(){
        ArrayList<NPC> npcs = App.getInstance().getCurrentGame().getCurrentPlayer().getNpcs();
        StringBuilder stringBuilder = new StringBuilder("");
        for(NPC npc : npcs) {
            int activeQuest = npc.getQuest().getActiveQuest();
            stringBuilder.append(npc.getQuest().getRequests().get(activeQuest) + "\n");
        }
        return new GameMessage(null, stringBuilder.toString());
    }
    @Override
    public Message exit() {
//        TODO: save game and go back to main Menu
    }

}



