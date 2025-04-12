package Modules.Crafting;

import Modules.Item;

import java.util.HashMap;

public interface Recipe {

    public String getProductName();
    public HashMap<Item, Integer> getIngredients();
    public int getPrice();
}
