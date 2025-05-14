package Modules.Crafting;

import Modules.App;
import Modules.Item;
import Modules.Player;

import java.io.Serializable;
import java.util.HashMap;

public class Refrigerator implements Serializable {
    private HashMap<Item, Integer> items;

    public Refrigerator() {
        this.items=new HashMap<>();
    }

    public void pickItem(Item item,int amount) {
        App app=App.getInstance();
        Player player=app.getCurrentGame().getCurrentPlayer();
        player.getBackPack().addItem(item,amount);
        this.items.remove(item,amount);
    }

    public boolean checkItemByName(String itemName) {
        for (Item item : items.keySet()) {
            if(item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public Item getItemByName(String itemName) {
        for (Item item : items.keySet()) {
            if(item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }


    public void putItem(Item item,int amount) {
        this.items.put(item,amount);
    }

    public boolean doesItemExist(Item item) {
        return this.items.containsKey(item);
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }
}
