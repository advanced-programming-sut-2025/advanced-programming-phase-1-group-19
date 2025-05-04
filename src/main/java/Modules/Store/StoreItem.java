package Modules.Store;

import Modules.Enums.Season;
import Modules.Item;

public class StoreItem {
    private Item item;
    private int price;
    private int dailyLimit;// 2000 if unlimited
    private Season season;
    private int seasonPrice;

    public StoreItem(Item item, int price,int dailyLimit, Season season,int seasonPrice) {
        this.item = item;
        this.price = price;
        this.season = season;
        this.seasonPrice = seasonPrice;
    }

    public Item getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }
}
