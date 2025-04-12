package Modules.Crafting;

import Modules.Item;

import java.util.HashMap;

public enum CookingRecipe implements Recipe{
    ;

    private String productName;
    private HashMap<Item, Integer> ingredients;
    private int Price;
    private Buff buff;
    private int energy;

    CookingRecipe() {}

    public CookingRecipe getCookingRecipeByName(String name) {}

    public Buff getBuff() {
        return buff;
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public String getProductName() {

    }

    @Override
    public HashMap<Item, Integer> getIngredients() {

    }

    @Override
    public int getPrice() {

    }
}
