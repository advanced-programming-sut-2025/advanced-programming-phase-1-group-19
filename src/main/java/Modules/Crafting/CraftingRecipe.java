package Modules.Crafting;

import Modules.Item;

import java.util.HashMap;

public enum CraftingRecipe implements Recipe {
    ;

    private String productName;
    private HashMap<Item, Integer> ingredients;
    private int Price;
    CraftingRecipe() {}

    public CraftingRecipe getCraftingRecipeByName(String name) {
//        TODO: fix this
        return null;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public HashMap<Item, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public int getPrice() {
        return Price;
    }
}
