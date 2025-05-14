package Modules.Communication;

import java.io.Serializable;

public class NPCFriendship implements Serializable {
    private int xp;
    private int level;

    public int getXp() {
        return xp;
    }

    public void increaseXp(int amount) {}

    public void decreaseXp(int amount) {}

    public int getLevel() {
        return level;
    }

}
