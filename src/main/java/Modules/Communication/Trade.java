package Modules.Communication;

import Modules.Item;
import Modules.Player;

public class Trade {
    private Player player;
    private boolean type; // 0: offer 1: request
    private Item item;
    private int amount;
    private int price;
    private Item targetItem;
    private int targetAmount;

    public Trade(Player player, boolean type, Item item, int amount, int price) {}

    public Trade(Player player, boolean type, Item item, Item targetItem, int targetAmount, int amount) {}

    public Player getPlayer() {
        return player;
    }

    public boolean isType() {
        return type;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public Item getTargetItem() {
        return targetItem;
    }

    public int getTargetAmount() {
        return targetAmount;
    }
}
