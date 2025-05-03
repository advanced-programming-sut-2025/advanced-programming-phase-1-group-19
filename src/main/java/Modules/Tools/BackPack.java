package Modules.Tools;

import Modules.Farming.Seed;
import Modules.Farming.SeedType;
import Modules.Item;

import java.util.HashMap;

public class BackPack {
    private HashMap<Item, Integer> items;
    private int level;
    private int maxCapacity;
    private int amount;

    public BackPack() {

    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void upgradeLevel() {
    }

    public void addItem(Item item, int count) {
        items.put(item, items.getOrDefault(item, 0) + count);
    }

    public boolean removeItem(Item item, int count) {
        if (checkItem(item, count)) {
            items.put(item, items.get(item) - count);
            if (items.get(item) == 0) {
                items.remove(item);
            }
            return true;
        }
        return false;
    }

    public boolean checkItem(Item item, int count) {
        if (!items.containsKey(item)) {
            return false;
        }
        return items.get(item) >= count;
    }

    public Seed getSeedInBachPack(SeedType type) {
        for(Item item : items.keySet()) {
            if(item instanceof Seed) {
                Seed seed = (Seed) item;
                if(seed.getType().equals(type)) {
                    return seed;
                }
            }
        }
        return null;
    }

    public int getCapacity() {
//        TODO: calculate free space
    }
}
