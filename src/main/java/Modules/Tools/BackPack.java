package Modules.Tools;

import Modules.Farming.Seed;
import Modules.Farming.SeedType;
import Modules.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BackPack {
    private HashMap<Item, Integer> items = new HashMap<>();
    private static ArrayList<Integer> capacity = new ArrayList<>();
    private static final ArrayList<String> name = new ArrayList<>();
    private int level;
    private int maxCapacity;
    private int amount;
    static {
        capacity.add(12);
        capacity.add(24);
        capacity.add(1000000000);
        name.add("initial");
        name.add("big");
        name.add("deluxe");
    }
    public BackPack() {
        level = 0;
        maxCapacity = 12;
        amount = 0;
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void upgradeLevel() {
        if(level < 3){
            maxCapacity = capacity.get(level+1);
            level++;
        }
    }

    public void addItem(Item item, int count) {
        items.put(item, items.getOrDefault(item, 0) + count);
        amount = getCapacity();
    }

    public boolean removeItem(Item item, int count) {
        if (checkItem(item, count)) {
            items.put(item, items.get(item) - count);
            if (items.get(item) == 0) {
                items.remove(item);
            }
            amount = getCapacity();
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
    public Tool getToolByType(String name) {
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            if(item.getName().equals(name) && item instanceof Tool) {
                return (Tool) item;
            }
        }
        return null;
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
        int totalCapacity = 0;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            Integer count = entry.getValue();
            totalCapacity += count * item.getTakenSpace();
        }
        return totalCapacity;
    }
}
