package Modules.Crafting;

import Modules.App;
import Modules.Item;
import Modules.Player;

import java.util.HashMap;

public class Refrigerator {
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
