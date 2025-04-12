package Modules.Communication;

import Modules.Item;

public class Gift {
    private Item item;
    private int amount;
    private int rate;

    public Gift(Item item, int amount) {}

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public int getRate() {
        return rate;
    }
}
