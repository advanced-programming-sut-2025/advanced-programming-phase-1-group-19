package Modules.Tools;

import Modules.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class BackPack {
    private HashMap<Item, Integer> items;
    private int level;
    private int maxCapacity;
    private int amount;

    public BackPack() {
        this.level = 0;
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void upgradeLevel() {}

    public void addItem(Item item, int count) {

    }

    public int getCapacity() {
        int capacity = maxCapacity-items.size();
        return capacity;
//        TODO: calculate free space
    }
}
