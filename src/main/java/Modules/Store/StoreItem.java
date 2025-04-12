package Modules.Store;

import Modules.Item;

public class StoreItem {
    private Item item;
    private int price;
    private int dailyLimit;// 2000 if unlimited

    public StoreItem(Item item, int price) {

    }

    public Item getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }
}
