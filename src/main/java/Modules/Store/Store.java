package Modules.Store;

import Modules.Crafting.CookingRecipe;
import Modules.Crafting.Food;
import Modules.Enums.Season;
import Modules.Farming.Crop;
import Modules.Farming.Seed;
import Modules.Farming.SeedType;
import Modules.Map.Building;
import Modules.Map.Size;
import Modules.Map.Tile;
import Modules.Tools.Tool;
import Modules.Tools.ToolType;

import java.util.ArrayList;

public class Store extends Building {
    private int openingTime;
    private int closingTime;
    private String ownerName;
    private ArrayList<StoreItem> items = new ArrayList<>();

    public ArrayList<StoreItem> getItems() {
        return items;
    }

    public Store(String ownerName, ArrayList<Tile> tiles) {
        this.tiles = tiles;
        this.size = new Size(1, 1);
        this.ownerName = ownerName;
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
}
