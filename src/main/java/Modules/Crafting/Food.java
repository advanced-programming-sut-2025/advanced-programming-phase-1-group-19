package Modules.Crafting;

import Modules.Item;
import Modules.Map.Tile;

import java.io.Serializable;

public class Food extends Item implements Serializable {

    private CookingRecipe recipe;

    public Food(CookingRecipe recipe) {
        super(recipe.getProductName(), 1,true);
        this.recipe = recipe;
    }

    public CookingRecipe getRecipe() {
        return recipe;
    }

    @Override
    public void use() {

    }

    @Override
    public void drop(Tile tile) {
        tile.setObject(Food.this);
    }

    @Override
    public void delete() {

    }

    @Override
    public String getName() {
        return recipe.getProductName();
    }

    @Override
    public int getPrice() {
        return recipe.getPrice();
    }
}
