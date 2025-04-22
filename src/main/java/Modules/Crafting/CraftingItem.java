package Modules.Crafting;

import Modules.Item;

public class CraftingItem extends Item {

    private final CraftingRecipe recipe;

    public CraftingItem(CraftingRecipe recipe) {}

    public CraftingRecipe getRecipe() { return recipe; }

    @Override
    public void use() {
//        TODO: check for different crafting recipes
    }

    @Override
    public void drop() {

    }

    @Override
    public void delete() {

    }

    @Override
    public String getName() {
        return recipe.getProductName();
    }
}
