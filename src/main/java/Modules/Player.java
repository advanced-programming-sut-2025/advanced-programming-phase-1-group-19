package Modules;

import Modules.Communication.FriendShip;
import Modules.Crafting.Buff;
import Modules.Crafting.CookingRecipe;
import Modules.Crafting.CraftingRecipe;
import Modules.Enums.SkillType;
import Modules.Map.Farm;
import Modules.Map.Position;
import Modules.Tools.BackPack;
import Modules.Tools.TrashCan;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
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

    public Player(User user, Farm farm) {
        this.user = user;
        this.farm = farm;
        this.position = new Position(farm.getTopLeft().x + 50, farm.getTopLeft().y + 50);
        this.energy = new Energy();
        backPack = new BackPack();
        trashCan = new TrashCan();
        skills = new HashMap<>();
        knownCraftingRecipes = new ArrayList<>();
        knownCookingRecipes = new ArrayList<>();
        lastBuffTime = new Time[5];
        for(int i = 0; i < 5; i++) {
            lastBuffTime[i] = Game.startingTime;
        }
        buffedEnergy = 0;
        friendShips = new ArrayList<>();
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

    public void addEnergy(int amount) {}

    public void decreaseEnergy(int amount) {}

    public void setFainted(boolean fainted) {
        isFainted = fainted;
    }

    public void Walk(Position position) {

    }

    public ArrayList<Position> findPath(Position start, Position end) {
//        TODO: find path to walk step by step
    }

    public BackPack getBackPack() {
        return backPack;
    }

    public TrashCan getTrashCan() {
        return trashCan;
    }

    public Skill getSkill(SkillType skillType) {}

    public boolean knowCraftingRecipe(CraftingRecipe recipe) {
        return knownCraftingRecipes.contains(recipe);
    }

    public void addKnownCraftingRecipe(CraftingRecipe recipe) {
//        TODO: first check if the player can learn the recipe or not
    }

    public ArrayList<CraftingRecipe> getKnownCraftingRecipes() {}

    public boolean knowCookingRecipe(CookingRecipe recipe) {
        return knownCookingRecipes.contains(recipe);
    }

    public void addKnownCookingRecipe(CookingRecipe recipe) {
//        TODO: first check if the player can learn the recipe or not
    }

    public void applyBuff(Buff buff) {}

    public boolean isBuffed(SkillType skillType) {}

    public void disableEnergyBuff() {
//        decrease energy capacity if the buff was over
    }
    public FriendShip getFriendShipByPlayer(Player player) {

    }
}
