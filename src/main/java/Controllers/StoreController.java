package Controllers;

import Modules.App;
import Modules.Crafting.CookingRecipe;
import Modules.Crafting.CraftingRecipe;
import Modules.Game;
import Modules.Interactions.Messages.GameMessage;
import Modules.Interactions.Messages.Message;
import Modules.Item;
import Modules.Player;
import Modules.Store.Store;
import Modules.Store.StoreItem;
import Modules.Store.StoreRecipes;
import Modules.Tools.Tool;

import java.util.ArrayList;

public class StoreController extends Controller {
    private static StoreController instance;

    private StoreController() {
    }

    public static StoreController getInstance() {
        if (instance == null) {
            instance = new StoreController();
        }
        return instance;
    }
    public GameMessage showAllProduct(){
        // TODO check if at store by tile!
        Store currentStore = App.getInstance().getCurrentGame().getCurrentPlayer().getCurrentStore();
        if(currentStore == null){
            return new GameMessage(null, "You are not at Store");
        }
        ArrayList<StoreItem> items = currentStore.getItems();
        StringBuilder stringBuilder = new StringBuilder();
        for(StoreItem item : items){
            Item currentItem = item.getItem();
            if(item.getSeason() == null || item.getSeason() != App.getInstance().getCurrentGame().getTime().getSeason()){
                stringBuilder.append(currentItem.toString() + " " + item.getSeasonPrice());
            }
            else{
                stringBuilder.append(currentItem.toString() + " " + item.getPrice());
            }
            stringBuilder.append("\n");
        }
        return new GameMessage(null, stringBuilder.toString());
    }
    public GameMessage showAvailableProduct(){
        Store currentStore = App.getInstance().getCurrentGame().getCurrentPlayer().getCurrentStore();
        if(currentStore == null){
            return new GameMessage(null, "You are not at Store");
        }
        ArrayList<StoreItem> items = currentStore.getItems();
        StringBuilder stringBuilder = new StringBuilder();
        for(StoreItem item : items){
            Item currentItem = item.getItem();
            if(item.getDailyLimit() == 0){
                continue;
            }
            if(item.getSeason() == null || item.getSeason() != App.getInstance().getCurrentGame().getTime().getSeason()){
                stringBuilder.append(currentItem.toString() + " " + item.getSeasonPrice());
            }
            else{
                stringBuilder.append(currentItem.toString() + " " + item.getPrice());
            }
            stringBuilder.append("\n");
        }
        return new GameMessage(null, stringBuilder.toString());
    }


    public GameMessage cheatAddMoney(int amount){
        App.getInstance().getCurrentGame().getCurrentPlayer().addMoney(amount);
        return new GameMessage(null, "this " + amount + " money has been added bro!");
    }

    public GameMessage sellItem(String itemName, int amount){
        // TODO shipping bin!
        Player player = App.getInstance().getCurrentGame().getCurrentPlayer();
        for (java.util.Map.Entry<Item, Integer> entry : player.getBackPack().getItems().entrySet()) {
            Item item = entry.getKey();
            Integer number = entry.getValue();
            if(item.getName().equals(itemName)){
                if(amount > number){
                    return new GameMessage(null, "not enough product to sell");
                }
                // TODO add shipping bin value
                player.setFeatureMoney(amount * item.getPrice());
                player.getBackPack().getItems().put(item, number - amount);
                return new GameMessage(null, "sold out of " + amount + " " + item.getName());
            }
        }
        return new GameMessage(null, "this product does not exist!");
    }

    public GameMessage purchaseItem(String itemName, int count){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Store currentStore = player.getCurrentStore();


        if(currentStore == null){
            return new GameMessage(null, "You are not at Store");
        }
        StoreItem item = currentStore.getItemByName(itemName);
        if(item == null){
            return new GameMessage(null, "There is no item with this name in this store!");
        }
        if(count == -1) {
            count = item.getDailyLimit();
        }
        if(item.getDailyLimit() < count){
            return new GameMessage(null, "You do not have enough daily limit");
        }
        item.removeDailyLimit(count);
        if(player.getBackPack().getMaxCapacity() - player.getBackPack().getCapacity() < item.getItem().getTakenSpace() * count){
            return new GameMessage(null, "You do not have enough space to take this item");
        }
        player.getBackPack().addItem(item.getItem(), count);
        if(item.getSeason()!= null && game.getTime().getSeason().equals(item.getSeason())){
            if(player.getMoney() < item.getSeasonPrice()){
                return new GameMessage(null, "you don't have enough money to buy");
            }
            player.decreaseMoney(item.getSeasonPrice());
        }
        else{
            if(player.getMoney() < item.getPrice()){
                return new GameMessage(null, "you don't have enough money to buy");
            }
            player.decreaseMoney(item.getPrice());
        }
        return new GameMessage(null, itemName+" has been purchased");
    }

    public GameMessage buyRecipe(String recipeName, int amount){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Store currentStore = player.getCurrentStore();
        if(currentStore == null){
            return new GameMessage(null, "You are not int any store");
        }
        StoreRecipes recipes = currentStore.getRecipeByName(recipeName);
        if(recipes == null){
            return new GameMessage(null, "There is no recipe with this name in this store");
        }
        if(recipes.getDailyLimit() < amount){
            return new GameMessage(null, "You do not have enough daily limit");
        }
        recipes.removeDailyLimit(amount);
        if(recipes.getRecipe() instanceof CookingRecipe){
            player.addKnownCookingRecipe((CookingRecipe) recipes.getRecipe());
        }
        else if(recipes.getRecipe() instanceof CraftingRecipe){
            player.addKnownCraftingRecipe((CraftingRecipe) recipes.getRecipe());
        }
        return new GameMessage(null, recipeName+" has been purchased");
    }



    @Override
    public Message showCurrentMenu() {
        return null;
    }

    @Override
    public Message exit() {
        return null;
    }
}
