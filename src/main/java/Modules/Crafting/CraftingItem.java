package Modules.Crafting;

import Modules.Item;
import Modules.Map.Tile;

import java.util.HashMap;

public class CraftingItem extends Item {

    private  CraftingRecipe recipe;

    public CraftingItem(CraftingRecipe recipe) {
        super(recipe.getProductName(),1,false);
        this.recipe = recipe;
    }

    public CraftingRecipe getRecipe() { return recipe; }

    @Override
    public void use() {
//        TODO: check for different crafting recipes
    }

    @Override
    public void drop(Tile tile) {
        tile.setObject(CraftingItem.this);
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
