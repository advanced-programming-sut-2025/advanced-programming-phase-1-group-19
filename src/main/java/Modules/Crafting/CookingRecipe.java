package Modules.Crafting;

import Modules.Item;

import java.util.HashMap;

public enum CookingRecipe implements Recipe{
    friedEgg("Fried Egg",new HashMap<>(){{put()}})
    ;

    private String productName;
    private HashMap<Item, Integer> ingredients;
    private int Price;
    private Buff buff;
    private int energy;

    CookingRecipe(String productName, HashMap<Item, Integer> ingredients, int price, Buff buff, int energy) {
        this.productName = productName;
        this.ingredients = ingredients;
        Price = price;
        this.buff = buff;
        this.energy = energy;
    }

    public static CookingRecipe getCookingRecipeByName(String name) {

    }


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
        return ingredients;
    }

    @Override
    public int getPrice() {

    }
}
