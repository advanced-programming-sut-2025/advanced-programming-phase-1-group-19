package Controllers;

import Modules.Animal.Animal;
import Modules.Animal.AnimalType;
import Modules.Animal.Barn;
import Modules.App;
import Modules.Crafting.CookingRecipe;
import Modules.Crafting.Food;
import Modules.Enums.InGameMenu;
import Modules.Interactions.Messages.GameMessage;
import Modules.Interactions.Messages.Message;
import Modules.Item;
import Modules.Map.Position;
import Modules.Map.Tile;
import Modules.Player;

public class HouseController extends Controller {

    public GameMessage showAllHouseMenus() {}

    public GameMessage goToMenu(InGameMenu menu) {}

    @Override
    public Message exit() {

    }

    public GameMessage refrigerator(Item item,int amount,boolean put) {
        App app = App.getInstance();
        Player player=app.getCurrentGame().getCurrentPlayer();
        if(put && !player.getBackPack().getItems().containsKey(item)) {
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
            if(recipe.toString().equals(recipeName)){
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
                player.decreaseEnergy(3);
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

    public GameMessage buildBarn(int x,int y){
        App app = App.getInstance();
        Player player=app.getCurrentGame().getCurrentPlayer();
        Position position=new Position(x,y);
        if(player.getFarm().getTile(position) == null) {
            return new GameMessage(null,"This position is out of your farm");
        }
        Tile tile=player.getFarm().getTile(position);
        if(!tile.isTotallyEmpty()){
            return new GameMessage(null,"This position is not empty");
        }
//        TODO:check if the player has enough money
        Barn barn=new Barn();
        player.getFarm().setBarn(barn);
        tile.setObject(barn);
        return new GameMessage(null,"You successfully build barn");
    }

    public GameMessage buyAnimal(String animalType,String animalName){
        AnimalType animalType1=AnimalType.getAnimalTypeByName(animalType);
        if(animalType1==null){
            return new GameMessage(null,"There is no such animal");
        }
//        if(animalType1.){}
    }

}
