package Models.Crafting;

import Models.Item;

import java.io.Serializable;
import java.util.HashMap;

public interface Recipe {

    public String getProductName();
    public HashMap<Item, Integer> getIngredients();
    public int getPrice();
}
