package Modules.Communication;

import Modules.Item;

import java.util.HashMap;

public class NPCQuest {
    private NPC npc;
    private HashMap<Item,Integer> requests;
    private HashMap<Item,Integer> rewards;

    public NPCQuest(NPC npc) {}

    public NPC getNpc() {
        return npc;
    }

    public HashMap<Item, Integer> getRequests() {
        return requests;
    }

    public HashMap<Item, Integer> getRewards() {
        return rewards;
    }
}
