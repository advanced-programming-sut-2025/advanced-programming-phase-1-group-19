package Modules;

import Modules.Communication.FriendShip;
import Modules.Communication.NPC;
import Modules.Crafting.Buff;
import Modules.Crafting.CookingRecipe;
import Modules.Crafting.CraftingRecipe;
import Modules.Enums.SkillType;
import Modules.Farming.Seed;
import Modules.Farming.SeedType;
import Modules.Map.Farm;
import Modules.Map.Position;
import Modules.Store.Store;
import Modules.Tools.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private int money;
    private int featureMoney;
    private User user;
    private Farm farm;
    private Position position;
    private Energy energy;
    private boolean isFainted = false;
    private final BackPack backPack;
    private final TrashCan trashCan;
    private final HashMap<SkillType, Skill> skills;
    private final ArrayList<CraftingRecipe> knownCraftingRecipes;
    private final ArrayList<CookingRecipe> knownCookingRecipes;
    private Time[] lastBuffTime; // 0:farming, 1: extraction, 2: foraging, 3: fishing, 4: energy;
    private int buffedEnergy;
    private ArrayList<FriendShip> friendShips;
    private Tool currentTool = null;
    private ArrayList<NPC> npcs;
    private Store currentStore;
    public Player(User user, Farm farm) {
        this.money = 0;
        this.user = user;
        this.farm = farm;
        this.position = new Position(farm.getTopLeft().x + 50, farm.getTopLeft().y + 50);
        this.energy = new Energy();
        backPack = new BackPack();
        Tool scythe = new Tool(ToolType.scythe);
        WateringCan wateringCan = new WateringCan(ToolType.wateringCan);
        Tool axe = new Tool(ToolType.axe);
        backPack.addItem(scythe, 1);
        backPack.addItem(wateringCan, 1);
        backPack.addItem(axe, 1);
        // add this bullshit to backpack
        backPack.addItem(new Seed(SeedType.carrot), 10);
        trashCan = new TrashCan();
        skills = new HashMap<>();
        skills.put(SkillType.mining, new Skill(SkillType.mining));
        skills.put(SkillType.farming, new Skill(SkillType.farming));
        skills.put(SkillType.fishing, new Skill(SkillType.fishing));
        skills.put(SkillType.foraging, new Skill(SkillType.foraging));
        knownCraftingRecipes = new ArrayList<>();
        knownCookingRecipes = new ArrayList<>();
        lastBuffTime = new Time[5];
        for(int i = 0; i < 5; i++) {
            lastBuffTime[i] = Game.startingTime;
        }
        buffedEnergy = 0;
        friendShips = new ArrayList<>();
        npcs = new ArrayList<>();
//        npcs.add(new NPC("Sebastian"));
//        npcs.add(new NPC("Abigail"));
//        npcs.add(new NPC("Harvey"));
//        npcs.add(new NPC("Lia"));
//        npcs.add(new NPC("Robin"));


    }

    public ArrayList<FriendShip> getFriendShips() {
        return friendShips;
    }

    public User getUser() {
        return user;
    }

    public Farm getFarm() {
        return farm;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isFainted() {
        return isFainted;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void addEnergy(int amount) {
            this.energy.addEnergy(amount);
    }

    public void decreaseEnergy(int amount) {
        this.energy.decreaseEnergy(amount);
    }

    public void setFainted(boolean fainted) {
        isFainted = fainted;
    }

    public void addFriendShip(FriendShip friendShip) {
        friendShips.add(friendShip);
    }

    public void Walk(Position position) {

    }

    public BackPack getBackPack() {
        return backPack;
    }

    public TrashCan getTrashCan() {
        return trashCan;
    }

    public Skill getSkill(SkillType skillType) {
        return skills.get(skillType);
    }

    public boolean knowCraftingRecipe(CraftingRecipe recipe) {
        return knownCraftingRecipes.contains(recipe);
    }

    public void addKnownCraftingRecipe(CraftingRecipe recipe) {
//        TODO: first check if the player can learn the recipe or not
    }

    public ArrayList<CookingRecipe> getKnownCookingRecipes() {
        return knownCookingRecipes;
    }

    public ArrayList<CraftingRecipe> getKnownCraftingRecipes() {
        return knownCraftingRecipes;
    }

    public boolean knowCookingRecipe(CookingRecipe recipe) {
        return knownCookingRecipes.contains(recipe);
    }

    public void addKnownCookingRecipe(CookingRecipe recipe) {
//        TODO: first check if the player can learn the recipe or not
    }

    public void applyBuff(Buff buff) {

    }

    public boolean isBuffed(SkillType skillType) {
//        TODO: fix this
        return true;
    }

    public void disableEnergyBuff() {
//        decrease energy capacity if the buff was over
    }
    public FriendShip getFriendShipByPlayer(Player player) {
        for (FriendShip friendShip : this.friendShips) {
            if(friendShip.getPlayer().equals(player)) {
                return friendShip;
            }
        }
        return null;
    }

    public Tool getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void decreaseMoney(int amount) {
        this.money -= amount;
    }

    public int getFeatureMoney() {
        return featureMoney;
    }

    public void setFeatureMoney(int featureMoney) {
        this.featureMoney += featureMoney;
    }

    public ArrayList<NPC> getNpcs() {
        return npcs;
    }

    public void setCurrentStore(Store currentStore) {
        this.currentStore = currentStore;
    }

    public Store getCurrentStore() {
        return currentStore;
    }
}
