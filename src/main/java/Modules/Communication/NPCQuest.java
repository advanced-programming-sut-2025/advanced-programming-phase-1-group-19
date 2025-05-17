package Modules.Communication;

import Modules.Animal.AnimalProduct;
import Modules.Animal.Fish;
import Modules.Animal.FishType;
import Modules.Crafting.CookingRecipe;
import Modules.Crafting.Food;
import Modules.Crafting.Material;
import Modules.Crafting.MaterialType;
import Modules.Farming.Crop;
import Modules.Farming.CropType;
import Modules.Farming.Plant;
import Modules.Farming.PlantType;
import Modules.Item;
import Modules.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class NPCQuest implements Serializable {
    private ArrayList<Pair<Item, Integer> > requests;
    private ArrayList<Pair<Item, Integer> > rewards;
    private NPC npc;
    private int activeQuest;
    private boolean isDone = false;
    public NPCQuest(NPC npc) {
        this.npc = npc;
        requests = new ArrayList<>();
        rewards = new ArrayList<>();
        activeQuest = 0;
        switch (npc.getName()){
            case "Sebastian":{
                requests.add(new Pair<>(new Material(MaterialType.ironOre), 50));
                requests.add(new Pair<>(new Food(CookingRecipe.pumpkinPie), 1));
                requests.add(new Pair<>(new Material(MaterialType.stone), 150));
                break;
            }
            case "Abigail":{
                requests.add(new Pair<>(new Material(MaterialType.goldBar), 1));
                requests.add(new Pair<>(new Crop(CropType.pumpkin), 1));
                requests.add(new Pair<>(new Crop(CropType.wheat), 50));
                break;
            }
            case "Harvey":{
                requests.add(new Pair<>(new Crop(CropType.pumpkin), 12));
                requests.add(new Pair<>(new Fish(FishType.salmon), 1));
                requests.add(new Pair<>(new Material(MaterialType.wine), 1));
                break;
            }
            case "Lia":{
                requests.add(new Pair<>(new Material(MaterialType.hardWood), 10));
                requests.add(new Pair<>(new Fish(FishType.salmon), 1));
                requests.add(new Pair<>(new Material(MaterialType.wood), 200));
                break;
            }
            case "Robin":{
                requests.add(new Pair<>(new Material(MaterialType.wood), 80));
                requests.add(new Pair<>(new Material(MaterialType.ironBar), 10));
                requests.add(new Pair<>(new Material(MaterialType.wood), 1000));
                break;
            }
        }
        this.activeQuest = 0;
    }

    public int getActiveQuest() {
        return activeQuest;
    }

    public void addActiveQuest() {
        this.activeQuest++;
        this.activeQuest = Math.max(this.activeQuest, 2);
    }

    public NPC getNpc() {
        return npc;
    }

    public ArrayList<Pair<Item, Integer>> getRequests() {
        return requests;
    }

    public ArrayList<Pair<Item, Integer>> getRewards() {
        return rewards;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }
}
