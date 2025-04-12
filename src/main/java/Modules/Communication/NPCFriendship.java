package Modules.Communication;

public class NPCFriendship {
    private NPC npc;
    private int xp;
    private int level;

    public NPC getNpc() {
        return npc;
    }

    public int getXp() {
        return xp;
    }

    public void increaseXp(int amount) {}

    public void decreaseXp(int amount) {}

    public int getLevel() {
        return level;
    }

}
