package Modules.Communication;

import Modules.Player;

import java.util.ArrayList;

public class FriendShip {
    private Player player;
    private int xp;
    private int level;
    private ArrayList<String> talkLog;
    private ArrayList<Gift> giftLog;
    private ArrayList<Trade> tradeLog;
    public FriendShip(Player player) {
    }

    public Player getPlayer() {
        return player;
    }

    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    public void increaseXp(int amount) {}

    public void decreaseXp(int amount) {}

    public void addMessage(String message) {}
    public ArrayList<String> getMessageLog() {
        return talkLog;
    }

    public void addGift(Gift gift) {}
    public ArrayList<Gift> getGiftLog() {
        return giftLog;
    }

    public int getTradeId(Trade trade) {
//        TODO: fix this
        return 0;
    }
    public void addTrade(Trade trade) {}
    public ArrayList<Trade> getTradeLog() {
        return tradeLog;
    }
}
