package Modules.Tools;

import Modules.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class BackPack {
    private HashMap<Item, Integer> items;
    private static ArrayList<Integer> capacity = new ArrayList<>();
    private static ArrayList<String> name = new ArrayList<>();
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
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void upgradeLevel() {
        if(level < )
    }

    public void addItem(Item item, int count) {}

    public int getCapacity() {
//        TODO: calculate free space
    }
}
