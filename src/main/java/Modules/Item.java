package Modules;

import Modules.Animal.*;
import Modules.Crafting.*;
import Modules.Farming.*;
import Modules.Map.Tile;
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
        FishType fishType = FishType.getFishTypeByName(name);
        if(fishType != null){
            item = new Fish(fishType);
        }
        CookingRecipe cookingRecipe = CookingRecipe.getCookingRecipeByName(name);
        if(cookingRecipe != null){
            item = new Food(cookingRecipe);
        }
        CraftingRecipe craftingRecipe = CraftingRecipe.getCraftingRecipeByName(name);
        if(craftingRecipe != null){
            item = new CraftingItem(craftingRecipe);
        }
        CropType cropType = CropType.getCropTypeByName(name);
        if(cropType != null){
            item = new Crop(cropType);
        }

        SeedType seedType = SeedType.getSeedTypeByName(name);
        if(seedType != null){
            item = new Seed(seedType);
        }

        ToolType toolType = ToolType.getToolTypeByName(name);
        if(toolType != null){
            item = new Tool(toolType);
        }
        return item;
    }

    public abstract void use();
    public abstract void drop(Tile tile);
    public abstract void delete();
    public String getName() {
        return name;
    }

    public int getTakenSpace() {
        return takenSpace;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item other = (Item) obj;
        return getName().equalsIgnoreCase(other.getName());
    }

    @Override
    public int hashCode() {
        return getName().toLowerCase().hashCode();
    }
}
