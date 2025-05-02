package Modules.Crafting;

import Modules.Item;

public class Food extends Item {

    private CookingRecipe recipe;

    public Food(String name, int takenSpace, CookingRecipe recipe) {
        super(name, takenSpace);
        this.recipe = recipe;
    }

    public CookingRecipe getRecipe() {
        return recipe;
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
