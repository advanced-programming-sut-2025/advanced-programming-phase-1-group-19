package Controllers;

import Modules.App;
import Modules.Game;
import Modules.Interactions.Messages.GameMessage;
import Modules.Item;
import Modules.Player;
import Modules.Store.Store;
import Modules.Store.StoreItem;
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
        Store currentStore = new Store("Robin");
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
        }
        return new GameMessage(null, stringBuilder.toString());
    }
    public GameMessage showAvailableProduct(){
        Store currentStore = new Store("Robin");
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
        }
        return new GameMessage(null, stringBuilder.toString());
    }

    public GameMessage purchaseItem(String itemName, int count){
        Store currentStore = new Store("Robin");// TODO:fix this!
        for(StoreItem item : currentStore.getItems()){
            Item currentItem = item.getItem();
            if(currentItem.toString().equals(itemName)){
                if(item.getDailyLimit() < count){
                    return new GameMessage(null, "not enough product to purchase");
                }
                else{
                    item.removeDailyLimit(count);
                    App.getInstance().getCurrentGame().getCurrentPlayer().getBackPack().addItem(currentItem, count);
                    return new GameMessage(null, "product purchased successfully");
                }
            }
        }
        return new GameMessage(null, "the product does not exist!");
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
}
