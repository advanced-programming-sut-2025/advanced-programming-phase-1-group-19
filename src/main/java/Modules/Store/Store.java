package Modules.Store;

import Modules.App;
import Modules.Crafting.CookingRecipe;
import Modules.Crafting.CraftingRecipe;
import Modules.Crafting.Food;
import Modules.Crafting.Recipe;
import Modules.Enums.Season;
import Modules.Farming.Crop;
import Modules.Farming.Seed;
import Modules.Farming.SeedType;
import Modules.Item;
import Modules.Map.Building;
import Modules.Map.Map;
import Modules.Map.Position;
import Modules.Map.Tile;
import Modules.Tools.Tool;
import Modules.Tools.ToolType;

import java.util.ArrayList;

import Modules.Enums.Season;
import Modules.Farming.Seed;
import Modules.Farming.SeedType;
import Modules.Tools.BackPack;

import java.util.ArrayList;

public class Store extends Building {
    private int openingTime;
    private int closingTime;
    private String ownerName;
    private ArrayList<StoreItem> items = new ArrayList<>();
    private ArrayList<StoreRecipes> recipes = new ArrayList<>();
    public ArrayList<StoreItem> getItems() {
        return items;
    }

//    TODO: add tiles arraylist into its constructor
    public Store(String ownerName) {
        this.ownerName = ownerName;
        switch (ownerName){
            case "Pierre":{
                this.openingTime = 9;
                this.closingTime = 17;
                this.items.add(new StoreItem(new Seed(SeedType.parsnip),30,5, Season.spring,20));
                this.items.add(new StoreItem(new Seed(SeedType.beanStarter),90,5, Season.spring,60));
                this.items.add(new StoreItem(new Seed(SeedType.cauliflower),120,5, Season.spring,80));
                this.items.add(new StoreItem(new Seed(SeedType.potato),75,5, Season.spring,50));
                this.items.add(new StoreItem(new Seed(SeedType.tulipBulb),30,5, Season.spring,20));
                this.items.add(new StoreItem(new Seed(SeedType.kale),30,5, Season.spring,20));
                this.items.add(new StoreItem(new Seed(SeedType.jazz),45,5, Season.spring,30));
                this.items.add(new StoreItem(new Seed(SeedType.garlic),60,5, Season.spring,40));
                this.items.add(new StoreItem(new Seed(SeedType.riceShoot),60,5, Season.spring,40));
                this.items.add(new StoreItem(new Seed(SeedType.riceShoot),60,5, Season.spring,40));
                this.items.add(new StoreItem(new Seed(SeedType.melon),120,5,Season.summer,80));
                this.items.add(new StoreItem(new Seed(SeedType.tomato),75,5,Season.summer,50));
                this.items.add(new StoreItem(new Seed(SeedType.blueberry),120,5,Season.summer,80));
                this.items.add(new StoreItem(new Seed(SeedType.pepper),60,5,Season.summer,40));
                this.items.add(new StoreItem(new Seed(SeedType.wheat),15,5,Season.summer,10));
                this.items.add(new StoreItem(new Seed(SeedType.radish),60,5,Season.summer,40));
                this.items.add(new StoreItem(new Seed(SeedType.poppy),150,5,Season.summer,100));
                this.items.add(new StoreItem(new Seed(SeedType.spangle),75,5,Season.summer,50));
                this.items.add(new StoreItem(new Seed(SeedType.hopsStarter),90,5,Season.summer,60));
                this.items.add(new StoreItem(new Seed(SeedType.corn),225,5,Season.summer,150));
                this.items.add(new StoreItem(new Seed(SeedType.sunflower),300,5,Season.summer,200));
                this.items.add(new StoreItem(new Seed(SeedType.redCabbage),150,5,Season.summer,100));
                this.items.add(new StoreItem(new Seed(SeedType.eggplant),30,5,Season.fall,20));
                this.items.add(new StoreItem(new Seed(SeedType.corn),225,5,Season.fall,150));
                this.items.add(new StoreItem(new Seed(SeedType.pumpkin),150,5,Season.fall,100));
                this.items.add(new StoreItem(new Seed(SeedType.bokChoy),75,5,Season.fall,50));
                this.items.add(new StoreItem(new Seed(SeedType.yam),90,5,Season.fall,60));
                this.items.add(new StoreItem(new Seed(SeedType.cranberry),360,5,Season.fall,240));
                this.items.add(new StoreItem(new Seed(SeedType.sunflower),300,5,Season.summer,200));
                this.items.add(new StoreItem(new Seed(SeedType.fairy),300,5,Season.fall,200));
                this.items.add(new StoreItem(new Seed(SeedType.amaranth),105,5,Season.fall,70));
                this.items.add(new StoreItem(new Seed(SeedType.grapeStarter),90,5,Season.fall,60));
                this.items.add(new StoreItem(new Seed(SeedType.wheat),15,5,Season.fall,10));
                this.items.add(new StoreItem(new Seed(SeedType.artichoke),45,5,Season.fall,30));
                this.recipes.add(new StoreRecipes(CraftingRecipe.dehydrator,10000,1,null,10000));
                this.recipes.add(new StoreRecipes(CraftingRecipe.grassStarter,1000,1,null,1000));
                break;
            }
        }
        switch (ownerName){
            case "Pierre":{
                openingTime = 9;
                closingTime = 17;
                items.add(new StoreItem(new Seed(SeedType.parsnip),30,5, Season.spring,20));
                items.add(new StoreItem(new Seed(SeedType.beanStarter),90,5, Season.spring,60));
                items.add(new StoreItem(new Seed(SeedType.cauliflower),120,5, Season.spring,80));
                items.add(new StoreItem(new Seed(SeedType.potato),75,5, Season.spring,50));
                items.add(new StoreItem(new Seed(SeedType.tulipBulb),30,5, Season.spring,20));
                items.add(new StoreItem(new Seed(SeedType.kale),30,5, Season.spring,20));
                items.add(new StoreItem(new Seed(SeedType.jazz),45,5, Season.spring,30));
                items.add(new StoreItem(new Seed(SeedType.garlic),60,5, Season.spring,40));
                items.add(new StoreItem(new Seed(SeedType.riceShoot),60,5, Season.spring,40));
                items.add(new StoreItem(new Seed(SeedType.melon),120,5,Season.summer,80));
                items.add(new StoreItem(new Seed(SeedType.tomato),75,5,Season.summer,50));
                items.add(new StoreItem(new Seed(SeedType.blueberry),120,5,Season.summer,80));
                items.add(new StoreItem(new Seed(SeedType.pepper),60,5,Season.summer,40));
                items.add(new StoreItem(new Seed(SeedType.wheat),15,5,Season.summer,10));
                items.add(new StoreItem(new Seed(SeedType.radish),60,5,Season.summer,40));
                items.add(new StoreItem(new Seed(SeedType.poppy),150,5,Season.summer,100));
                items.add(new StoreItem(new Seed(SeedType.spangle),75,5,Season.summer,50));
                items.add(new StoreItem(new Seed(SeedType.hopsStarter),90,5,Season.summer,60));
                items.add(new StoreItem(new Seed(SeedType.corn),225,5,Season.summer,150));
                items.add(new StoreItem(new Seed(SeedType.sunflower),300,5,Season.summer,200));
                items.add(new StoreItem(new Seed(SeedType.redCabbage),150,5,Season.summer,100));
                items.add(new StoreItem(new Seed(SeedType.eggplant),30,5,Season.fall,20));
                items.add(new StoreItem(new Seed(SeedType.corn),225,5,Season.fall,150));
                items.add(new StoreItem(new Seed(SeedType.pumpkin),150,5,Season.fall,100));
                items.add(new StoreItem(new Seed(SeedType.bokChoy),75,5,Season.fall,50));
                items.add(new StoreItem(new Seed(SeedType.yam),90,5,Season.fall,60));
                items.add(new StoreItem(new Seed(SeedType.cranberry),360,5,Season.fall,240));
                items.add(new StoreItem(new Seed(SeedType.sunflower),300,5,Season.summer,200));
                items.add(new StoreItem(new Seed(SeedType.fairy),300,5,Season.fall,200));
                items.add(new StoreItem(new Seed(SeedType.amaranth),105,5,Season.fall,70));
                items.add(new StoreItem(new Seed(SeedType.grapeStarter),90,5,Season.fall,60));
                items.add(new StoreItem(new Seed(SeedType.wheat),15,5,Season.fall,10));
                items.add(new StoreItem(new Seed(SeedType.artichoke),45,5,Season.fall,30));
                break;
            }
            case "Robin":{
                openingTime = 9;
                closingTime = 20;
                items.add(new StoreItem(
                        new Seed(SeedType.ancient),
                        500,
                        1,
                        null,
                        500
                ));
                items.add(new StoreItem(
                    new Seed(SeedType.cauliflower),
                        100,
                        5,
                        Season.spring,
                        100
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.parsnip),
                        25,
                        5,
                        Season.spring,
                        25
                ));
                items.add(new StoreItem(
                   new Seed(SeedType.potato),
                        62,
                        5,
                        Season.spring,
                        62
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.strawberry),
                        100,
                        5,
                        Season.spring,
                        100
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.tulipBulb),
                        25,
                        5,
                        Season.spring,
                        25
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.kale),
                        87,
                        5,
                        Season.spring,
                        87
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.coffeeBean),
                        200,
                        1,
                        Season.spring,
                        200
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.carrot),
                        5,
                        10,
                        Season.spring,
                        5
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.rhubarb),
                        100,
                        5,
                        Season.spring,
                        100
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.jazz),
                        37,
                        5,
                        Season.spring,
                        37
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.tomato),
                        62,
                        5,
                        Season.summer,
                        62
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.pepper),
                        50,
                        5,
                        Season.summer,
                        50
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.wheat),
                        12,
                        10,
                        Season.summer,
                        12
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.summerSquash),
                        10,
                        10,
                        Season.summer,
                        10
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.radish),
                        50,
                        5,
                        Season.summer,
                        50
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.melon),
                        100,
                        5,
                        Season.summer,
                        100
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.hopsStarter),
                        75,
                        5,
                        Season.summer,
                        75
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.poppy),
                        125,
                        5,
                        Season.summer,
                        125
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.spangle),
                        62,
                        5,
                        Season.summer,
                        62
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.starfruit),
                        400,
                        5,
                        Season.summer,
                        400
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.coffeeBean),
                        200,
                        1,
                        Season.summer,
                        200
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.sunflower),
                        125,
                        5,
                        Season.summer,
                        125
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.corn),
                        187,
                        5,
                        Season.fall,
                        187
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.eggplant),
                        25,
                        5,
                        Season.fall,
                        25
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.pumpkin),
                        125,
                        5,
                        Season.fall,
                        125
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.broccoli),
                        15,
                        5,
                        Season.fall,
                        15
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.amaranth),
                        87,
                        5,
                        Season.fall,
                        87
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.grapeStarter),
                        75,
                        5,
                        Season.fall,
                        75
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.beet),
                        20,
                        5,
                        Season.fall,
                        20
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.yam),
                        75,
                        5,
                        Season.fall,
                        75
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.bokChoy),
                        62,
                        5,
                        Season.fall,
                        62
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.cranberry),
                        300,
                        5,
                        Season.fall,
                        300
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.sunflower),
                        125,
                        5,
                        Season.fall,
                        125
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.fairy),
                        250,
                        5,
                        Season.fall,
                        250
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.rare),
                        1000,
                        1,
                        Season.fall,
                        1000
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.wheat),
                        12,
                        5,
                        Season.fall,
                        12
                ));
                items.add(new StoreItem(
                        new Seed(SeedType.powdermelon),
                        20,
                        10,
                        Season.winter,
                        20
                ));
                break;
            }
            case "Gus":{
                openingTime = 12;
                closingTime = 24;
                items.add(new StoreItem(
                    new Food(CookingRecipe.bread),
                        120,
                        100000000,
                        null,
                        120
                ));
                items.add(new StoreItem(
                        new Food(CookingRecipe.spaghetti),
                        240,
                        100000000,
                        null,
                        240
                ));
                items.add(new StoreItem(
                        new Food(CookingRecipe.pizza),
                        600,
                        100000000,
                        null,
                        600
                ));
                recipes.add(new StoreRecipes(
                        CookingRecipe.omelet,100,
                        1,
                        null,
                        100
                ));
                recipes.add(new StoreRecipes(
                        CookingRecipe.hashBrowns,
                        50,
                        1,
                        null,
                        50
                ));
                recipes.add(new StoreRecipes(
                        CookingRecipe.pancakes,
                        100,
                        1,
                        null,
                        100
                ));
                recipes.add(new StoreRecipes(
                        CookingRecipe.bread,
                        100,
                        1,
                        null,
                        100
                ));
                recipes.add(new StoreRecipes(
                        CookingRecipe.tortilla,
                        100,
                        1,
                        null,
                        100
                ));
                recipes.add(new StoreRecipes(
                        CookingRecipe.pizza,
                        150,
                        1,
                        null,
                        150
                ));
                recipes.add(new StoreRecipes(
                        CookingRecipe.tripleShotEspresso,
                        5000,
                        1,
                        null,
                        5000
                ));
                recipes.add(new StoreRecipes(
                        CookingRecipe.cookie,
                        300,
                        1,
                        null,
                        300
                ));

                break;
            }
            case "Marine":{
                openingTime = 9;
                closingTime = 16;
                items.add(new StoreItem(
                        new Tool(ToolType.milkPail),
                        1000,
                        1,
                        null,
                        1000
                ));
                items.add(new StoreItem(
                        new Tool(ToolType.shear),
                        1000,
                        1,
                        null,
                        1000
                ));
                break;
            }
            case "Willy":{
                openingTime = 9;
                closingTime = 17;
                Tool bamboo = new Tool(ToolType.fishingPole);
                bamboo.setLevel(1);
                Tool training = new Tool(ToolType.fishingPole);
                Tool fiberGlass = new Tool(ToolType.fishingPole);
                fiberGlass.setLevel(2);
                Tool iridium = new Tool(ToolType.fishingPole);
                iridium.setLevel(3);
                items.add(new StoreItem(
                        bamboo, 500, 1,null, 500
                ));
                items.add(new StoreItem(
                        training, 25, 1,null, 25
                ));
                items.add(new StoreItem(fiberGlass, 1800, 2,null, 1800));
                items.add(new StoreItem(iridium, 7500, 4, null, 7500));
                break;
            }
        }
    }

    public int getOpeningTime() {
        return openingTime;
    }

    public int getClosingTime() {
        return closingTime;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public static Store getStoreByName(String name) {
        switch (name) {
            case "Blacksmith":{
                return new Store("Clint");
            }
            case "Joja Mart":{
                return new Store("Morris");
            }
            case "Pierre's General Store":{
                return new Store("Pierre");
            }
            case "Carpenter's Shop":{
                return new Store("Robin");
            }
            case "Fish Shop":{
                return new Store("Willy");
            }
            case "Marnie's Ranch":{
                return new Store("Marnie");
            }
            case "The Stardrop Saloon":{
                return new Store("Gus");
            }
        }
        return null;
    }

    public String getName(){
        switch (ownerName){
            case "Clint":{
                return "Blacksmith";
            }
            case "Morris":{
                return "Joja Mart";
            }
            case "Pierre":{
                return "Pierre's General Store";
            }
            case "Robin":{
                return "Carpenter's Shop";
            }
            case "Willy":{
                return "Fish Shop";
            }
            case "Marnie":{
                return "Marnie's Ranch";
            }
            case "Gus":{
                return "The Stardrop Saloon";
            }
        }
        return null;
    }

    public StoreItem getItemByName(String name) {
        for (StoreItem item : items) {
            if(item.getItem().getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    public StoreRecipes getRecipeByName(String name) {
        for (StoreRecipes recipe : recipes) {
            if(recipe.getRecipe().getProductName().equals(name)){
                return recipe;
            }
        }
        return null;
    }

    public boolean isNear(Position position) {
        Map map = App.getInstance().getCurrentGame().getMap();
        int x = position.x;
        int y = position.y;
        for(int i = 0; i < 10; i++) {
            int dx = (i / 3) - 1;
            int dy = (i % 3) - 1;
            if(dx == 0 && dy == 0){
                continue;
            }
            Tile tile = map.getTile(new Position(x + dx, y + dy));
            if(tile == null){
                continue;
            }
            if(tile.getBuilding() instanceof Store && tile.getBuilding().equals(this)) {
                return true;
            }
        }
        return false;
    }

}
