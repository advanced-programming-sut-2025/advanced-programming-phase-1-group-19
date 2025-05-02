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
import Modules.Interactions.Messages.Message;
import Modules.Item;
import Modules.Map.Map;
import Modules.Map.Position;
import Modules.Map.Tile;
import Modules.Player;

public class HouseController extends Controller {

    public GameMessage showAllHouseMenus() {}

    public GameMessage goToMenu(InGameMenu menu) {}

    @Override
    public Message exit() {

    }

    public GameMessage refrigerator(String itemName,int amount,boolean put) {
        App app = App.getInstance();
        Player player=app.getCurrentGame().getCurrentPlayer();
        boolean doesExist=false;
        Item item=null;
        for (Item item1 : player.getBackPack().getItems().keySet()) {
            if(item1.getName().equals(itemName)) {
                doesExist=true;
                item=item1;
            }
        }
        if(put && !doesExist) {
            return new GameMessage(null,"You don't have this item in your backpack");
        }
        if(!put && !player.getFarm().getHouse().getRefrigerator().doesItemExist(item)) {
            return new GameMessage(null,"You don't have this item in your refrigerator");
        }
        if(put){
            player.getFarm().getHouse().getRefrigerator().putItem(item,amount);
            return new GameMessage(null,"You successfully put the "+item.getName()+" into the refrigerator");
        }
        player.getFarm().getHouse().getRefrigerator().pickItem(item,amount);
        return new GameMessage(null,"You successfully put the "+item.getName()+" into your backpack");
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
        for(int i=0;i<player.getKnownCookingRecipes().size();i++){
            CookingRecipe recipe=player.getKnownCookingRecipes().get(i);
            if(recipe.name().equals(recipeName)){
                //TODO: check how aboo has wrote recipes
                for(Item item:recipe.getIngredients().keySet()){
                    if(!player.getBackPack().getItems().containsKey(item)  && !player.getFarm().getHouse().getRefrigerator().doesItemExist(item)) {
                        return new GameMessage(null,"You don't have needed ingredients");
                    }
                    if(player.getBackPack().getItems().containsKey(item)){
                        if(player.getBackPack().getItems().get(item) < recipe.getIngredients().get(item)){
                            return new GameMessage(null,"You don't have enough quantity of needed ingredients");
                        }
                        if(player.getBackPack().getCapacity() == 0){
                            return new GameMessage(null,"You don't have enough capacity for needed ingredients");
                        }
                    }
                    if(player.getFarm().getHouse().getRefrigerator().doesItemExist(item)){
                        if(player.getFarm().getHouse().getRefrigerator().getItems().get(item) < recipe.getIngredients().get(item)){
                            return new GameMessage(null,"You don't have enough quantity of needed ingredients");
                        }
                    }
                }
                for(Item item:player.getBackPack().getItems().keySet()){
                    if(item.getName().equals(recipe.getProductName())){
                        player.getBackPack().getItems().put(item,player.getBackPack().getItems().get(item)+1);
                        player.decreaseEnergy(3);
                        return new GameMessage(null,"You successfully cooked");
                    }
                }
                player.getBackPack().addItem(new Food(null,0,recipe),1);
                //TODO:look for the food to add it to your inventory
                if(player.getEnergy().getAmount()<3){
                    player.setFainted(true);
                }
                else {
                    player.decreaseEnergy(3);
                }
                if(player.getBackPack().getCapacity()>0){
                    player.getBackPack().addItem(new Food(recipe.getProductName(),1,recipe),1);
                }
                else {
                    return new GameMessage(null,"You don't have any space in your backpack");
                }
                return new GameMessage(null,"You successfully cooked");
            }
        }
        return new GameMessage(null,"You do not have this recipe");
    }

    public GameMessage eatFood(String foodName){
        App app = App.getInstance();
        Player player=app.getCurrentGame().getCurrentPlayer();
        for(Item item : player.getBackPack().getItems().keySet()){
            if(item.getName().equals(foodName)){
                player.getBackPack().getItems().put(item,player.getBackPack().getItems().get(item)-1);
                Food food=(Food) item;
                //TODO:apply Buff
            }
        }

    }

    public GameMessage buildBarn(String type,int x,int y) {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Position position = new Position(x, y);
        if (player.getFarm().getTile(position) == null) {
            return new GameMessage(null, "This position is out of your farm");
        }
        Tile tile = player.getFarm().getTile(position);
        if (!tile.isTotallyEmpty()) {
            return new GameMessage(null, "This position is not empty");
        }
//        TODO:check if the player has enough money
        if (type.equals("Barn")) {
            Barn barn = new Barn();
            player.getFarm().setBarn(barn);
            tile.setObject(barn);
            return new GameMessage(null, "You successfully build barn");
        }
        else if (type.equals("Coop")) {
            Coop coop = new Coop();
            player.getFarm().setCoop(coop);
            tile.setObject(coop);
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
        if(animalType1.isInCage()){
            if(player.getFarm().getCoop()==null){
                return new GameMessage(null,"There is no coop");
            }
//                TODO:check if there is the needed cage for this animal
            player.getFarm().getCoop().animals.add(new Animal(animalType1,player,animalName));
        }
        else {
            if(player.getFarm().getBarn()==null){
                return new GameMessage(null,"There is no barn");
            }
//            TODO:check if there is the needed barn
            player.getFarm().getBarn().addAnimal(new Animal(animalType1,player,animalName));
        }
        return new GameMessage(null,"You successfully bought animal");
    }

}
