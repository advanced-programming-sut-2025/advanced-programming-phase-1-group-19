package Modules;

import Modules.Crafting.*;
import Modules.Farming.*;
import Modules.Map.TileObject;
import Modules.Tools.Tool;
import Modules.Tools.ToolType;

public abstract class Item extends TileObject {

    protected final String name;
    protected final int takenSpace;
    protected final boolean isEdible;
    abstract public int getPrice();

    public Item(String name, int takenSpace, boolean isEdible) {
        this.name = name;
        this.takenSpace = takenSpace;
        this.isEdible = isEdible;
    }

    public static Item getItemByName(String name){
        Item item = null;
        MaterialType materialType = MaterialType.getMaterialTypeByName(name);
        if(materialType != null){
            item = new Material(materialType);
        }
        CookingRecipe cookingRecipe = CookingRecipe.getCookingRecipeByName(name);
        if(cookingRecipe != null){
            item = new Food(cookingRecipe);
        }
//        TODO:after completing crafting recipe
        CropType cropType = CropType.getCropTypeByName(name);
        if(cropType != null){
            item = new Crop(cropType);
        }

        SeedType seedType = SeedType.getSeedTypeByName(name);
        if(seedType != null){
            item = new Seed(seedType);
        }
        return item;
    }

    public abstract void use();
    public abstract void drop();
    public abstract void delete();
    public String getName() {
        return name;
    }

    public int getTakenSpace() {
        return takenSpace;
    }

}
