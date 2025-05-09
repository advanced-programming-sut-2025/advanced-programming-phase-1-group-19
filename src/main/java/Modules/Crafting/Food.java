package Modules.Crafting;

import Modules.Item;

public class Food extends Item {

    private CookingRecipe recipe;

    public Food(CookingRecipe recipe) {
        super(recipe.getProductName(), 1,true);
        this.recipe = recipe;
    }

    public CookingRecipe getRecipe() {
        return recipe;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public void use() {

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
