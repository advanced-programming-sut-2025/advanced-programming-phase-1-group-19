package Modules.Crafting;

import Modules.Item;

import java.util.HashMap;

public enum CraftingRecipe implements Recipe {
    cherryBomb("Cherry Bomb", , 50),
    bomb("Bomb", , 50),
    megaBomb("Mega Bomb", , 50),
    sprinkler("Sprinkler", , 0),
    qualitySprinkler("Quality Sprinkler", , 0),
    iridiumSprinkler("Iridium Sprinkler", , 0),
    charcoalKlin("Charcoal Klin",,0),
    furance("Furance",,0),
    scarecrow("Scarecrow",,0),
    deluxScarecrow("Delux Scarecrow",,0),
    beeHouse("Bee House",,0),
    cheesePress("Cheese Press",,0),
    keg("Keg",,0),
    loom("Loom",,0),
    mayonnaiseMachine("Mayonnaise Machine",,0),
    oilMaker("Oil Maker",,0),
    preservesJar("Preserves Jar",,0),
    dehydrator("Dehydrator",,0),
    grassStarter("Grass Starter",,0),
    fishSmoker("Fish Smoker",,0),
    mysticTreeSeed("Mystic Tree Seed",,0);
    ;

    private String productName;
    private HashMap<Item, Integer> ingredients;
    private int Price;

    CraftingRecipe(String productName, HashMap<Item, Integer> ingredients, int Price) {
        this.productName = productName;
        this.ingredients = ingredients;
        this.Price = Price;
    }

    public static CraftingRecipe getCraftingRecipeByName(String name) {
        switch (name) {
            case "Cherry Bomb": {
                return cherryBomb;
            }
            case "Bomb": {
                return bomb;
            }
            case "Mega Bomb": {
                return megaBomb;
            }
            case "Sprinkler": {
                return sprinkler;
            }
            case "Quality Sprinkler": {
                return qualitySprinkler;
            }
            case "Iridium Sprinkler": {
                return iridiumSprinkler;
            }
            case "Charcoal Klin": {
                return charcoalKlin;
            }
            case "Furance": {
                return furance;
            }
            case "Scarecrow": {
                return scarecrow;
            }
            case "Dehydrator": {
                return dehydrator;
            }
            case "BeeHouse": {
                return beeHouse;
            }
            case "Cheese Press": {
                return cheesePress;
            }
            case "Keg": {
                return keg;
            }
            case "Loom": {
                return loom;
            }
            case "Mayonnaise Machine": {
                return mayonnaiseMachine;
            }
            case "Oil Maker": {
                return oilMaker;
            }
            case "PreservesJar": {
                return preservesJar;
            }
            case "Grass Starter": {
                return grassStarter;
            }
            case "Fish Smoker": {
                return fishSmoker;
            }
            case "MysticTreeSeed": {
                return mysticTreeSeed;
            }
            default: {
                return null;
            }
        }
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
