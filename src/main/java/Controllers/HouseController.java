package Controllers;

import Modules.Animal.Animal;
import Modules.Animal.AnimalType;
import Modules.Animal.Barn;
import Modules.Animal.Coop;
import Modules.App;
import Modules.Crafting.CookingRecipe;
import Modules.Crafting.Food;
import Modules.Enums.InGameMenu;
import Modules.Game;
import Modules.Interactions.Messages.GameMessage;
import Modules.Interactions.Messages.MainMessage;
import Modules.Interactions.Messages.Message;
import Modules.Item;
import Modules.Map.Position;
import Modules.Map.Tile;
import Modules.Player;

import java.util.Map;

public class HouseController extends Controller {

    public GameMessage showAllHouseMenus() {
//        TODO: show all inGame menus related to houseMenu
        return null;
    }

    public GameMessage goToMenu(InGameMenu menu) {
//        TODO: fix this
        return null;
    }

    @Override
    public Message showCurrentMenu() {
        return new GameMessage(null, InGameMenu.houseMenu.toString());
    }

    @Override
    public Message exit() {
//        TODO: fix this
        return null;
    }

    public GameMessage refrigerator(String itemName,int amount,boolean put) {
        App app = App.getInstance();
        Player player=app.getCurrentGame().getCurrentPlayer();
        Item item = player.getBackPack().getItemByName(itemName);
        if(put && (item == null)) {
            return new GameMessage(null,"You don't have this item in your backpack");
        }
        if(!put && !player.getFarm().getHouse().getRefrigerator().checkItemByName(itemName.trim())) {
            return new GameMessage(null,"You don't have this item in your refrigerator");
        }
        if(put){
            player.decreaseEnergy(3);
            player.getBackPack().removeItem(item,1);
            player.getFarm().getHouse().getRefrigerator().putItem(item,amount);
            return new GameMessage(null,"You successfully put the "+item.getName()+" into the refrigerator");
        }
        if((player.getBackPack().getCapacity() >= player.getBackPack().getMaxCapacity())){
            return new GameMessage(null,"You don't have enough space in your backpack");
        }
        player.decreaseEnergy(3);
        item = player.getFarm().getHouse().getRefrigerator().getItemByName(itemName.trim());
        player.getFarm().getHouse().getRefrigerator().pickItem(item,amount);
        return new GameMessage(null,"You successfully put the "+itemName+" into your backpack");
    }

    public GameMessage showCookingRecipe() {
        App app = App.getInstance();
        Player player=app.getCurrentGame().getCurrentPlayer();
        StringBuilder str = new StringBuilder();
        str.append("You have :\n");
        for(int i=0;i<player.getKnownCookingRecipes().size();i++){
            CookingRecipe recipe=player.getKnownCookingRecipes().get(i);
            str.append(recipe.getProductName()+"\n");
        }
        return new GameMessage(null,str.toString()+"\n");
    }

    public GameMessage cookingPrepare(String recipeName) {
        App app = App.getInstance();
        Player player=app.getCurrentGame().getCurrentPlayer();
        CookingRecipe cookingRecipe = CookingRecipe.getCookingRecipeByName(recipeName);
        if(cookingRecipe == null){
            return new GameMessage(null,"There is no such recipe");
        }
        for (CookingRecipe knownCookingRecipe : player.getKnownCookingRecipes()) {
            if(knownCookingRecipe.getProductName().equals(cookingRecipe.getProductName())){
                for (Map.Entry<Item, Integer> itemIntegerEntry : knownCookingRecipe.getIngredients().entrySet()) {
                    if(!player.getBackPack().checkItem(itemIntegerEntry.getKey(),itemIntegerEntry.getValue())){
                        if(!player.getFarm().getHouse().getRefrigerator().checkItem(itemIntegerEntry.getKey(),itemIntegerEntry.getValue())){
                            return new GameMessage(null,"You don't have enough ingredients in your backpack");
                        }
                    }
                }
                for (Map.Entry<Item, Integer> itemIntegerEntry : knownCookingRecipe.getIngredients().entrySet()) {
                    if(player.getBackPack().checkItem(itemIntegerEntry.getKey(),itemIntegerEntry.getValue())){
                        player.getBackPack().removeItem(itemIntegerEntry.getKey(),itemIntegerEntry.getValue());
                    }
                    else if(player.getFarm().getHouse().getRefrigerator().checkItem(itemIntegerEntry.getKey(),itemIntegerEntry.getValue())){
                        player.getFarm().getHouse().getRefrigerator().removeItem(itemIntegerEntry.getKey(),itemIntegerEntry.getValue());
                    }
                }
                player.decreaseEnergy(3);
                player.getBackPack().addItem(new Food(knownCookingRecipe),1);
                return new GameMessage(null,"You successfully cooked the "+cookingRecipe.getProductName()+" into your backpack");
            }
        }
        return new GameMessage(null,"You do not have this recipe");
    }

    public GameMessage eatFood(String foodName){
        App app = App.getInstance();
        Player player=app.getCurrentGame().getCurrentPlayer();
        Item item = player.getBackPack().getItemByName(foodName);
        if(item == null){
            return new GameMessage(null,"You don't have this item in your backpack");
        }
        player.getBackPack().removeItem(item,1);
        Food food = (Food) item;
        player.addEnergy(food.getRecipe().getEnergy());
//        TODO:apply buff
        return new GameMessage(null,"You successfully eat the "+foodName);
    }

    public GameMessage buildBarn(String type,int x,int y) {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Position position = new Position(x, y);
        Position position1 = new Position(x+1,y);
        Position position2 = new Position(x, y+1);
        Position position3 = new Position(x+1, y+1);
        if (player.getFarm().getTile(position) == null) {
            return new GameMessage(null, "This position is out of your farm");
        }
        if (player.getFarm().getTile(position1) == null) {
            return new GameMessage(null, "This position is out of your farm");
        }
        if (player.getFarm().getTile(position2) == null) {
            return new GameMessage(null, "This position is out of your farm");
        }
        if (player.getFarm().getTile(position3) == null) {
            return new GameMessage(null, "This position is out of your farm");
        }
        Tile tile = player.getFarm().getTile(position);
        if (!tile.isTotallyEmpty()) {
            return new GameMessage(null, "This position is not empty");
        }
        Tile tile1 = player.getFarm().getTile(position1);
        Tile tile2 = player.getFarm().getTile(position2);
        Tile tile3 = player.getFarm().getTile(position3);
        if(!tile1.isTotallyEmpty()){
            return new GameMessage(null, "This position is not empty");
        }
        if(!tile2.isTotallyEmpty()){
            return new GameMessage(null, "This position is not empty");
        }
        if(!tile3.isTotallyEmpty()){
            return new GameMessage(null, "This position is not empty");
        }
//        TODO:check if the player has enough money
        if (type.equals("Barn")) {
            Barn barn = new Barn();
            player.getFarm().setBarn(barn);
            tile.setObject(barn);
            tile1.setObject(barn);
            tile2.setObject(barn);
            tile3.setObject(barn);
            barn.setPlacedTile(tile);
            return new GameMessage(null, "You successfully build barn");
        }
        else if (type.equals("Coop")) {
            Coop coop = new Coop();
            player.getFarm().setCoop(coop);
            tile.setObject(coop);
            tile1.setObject(coop);
            tile2.setObject(coop);
            tile3.setObject(coop);
            coop.setPlacedTile(tile);
            return new GameMessage(null, "You successfully build coop");
        }
        else {
            return new GameMessage(null, "this is an invalid type");
        }
    }

    public GameMessage buyAnimal(String animalType,String animalName){
        App app = App.getInstance();
        Player player=app.getCurrentGame().getCurrentPlayer();
        AnimalType animalType1=AnimalType.getAnimalTypeByName(animalType);
        if(animalType1==null){
            return new GameMessage(null,"There is no such animal");
        }
        Animal animal = new Animal(animalType1,player,animalName);
        if(animalType1.isInCage()){
            if(player.getFarm().getCoop()==null){
                return new GameMessage(null,"There is no coop");
            }
//                TODO:check if there is the needed cage for this animal
            animal.setPosition(player.getFarm().getCoop().getPlacedTile().getPosition());
            player.getFarm().getCoop().animals.add(animal);
        }
        else {
            if(player.getFarm().getBarn()==null){
                return new GameMessage(null,"There is no barn");
            }
//            TODO:check if there is the needed barn
            animal.setPosition(player.getFarm().getBarn().getPlacedTile().getPosition());
            player.getFarm().getBarn().addAnimal(animal);
        }
        return new GameMessage(null,"You successfully bought animal");
    }

}
