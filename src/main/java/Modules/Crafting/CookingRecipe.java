package Modules.Crafting;

import Modules.Item;

import java.util.HashMap;

public enum CookingRecipe implements Recipe{

    ;

    private String productName;
    private HashMap<Item, Integer> ingredients;
    private int price;
    private Buff buff;
    private int energy;

    CookingRecipe(String productName, HashMap<Item, Integer> ingredients, int price, Buff buff, int energy) {
        this.productName = productName;
        this.ingredients = ingredients;
        this.price = price;
        this.buff = buff;
        this.energy = energy;
    }

    public static CookingRecipe getCookingRecipeByName(String name) {
//        TODO: fix this
        return null;
    }


    public Buff getBuff() {
        return buff;
    }

    public int getEnergy() {
        return energy;
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
        return price;
    }
}
