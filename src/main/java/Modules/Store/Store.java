package Modules.Store;

import Modules.Enums.Season;
import Modules.Farming.Seed;
import Modules.Farming.SeedType;
import Modules.Tools.BackPack;

import java.util.ArrayList;

public class Store {
    private int openingTime;
    private int closingTime;
    private String ownerName;
    private ArrayList<StoreItem> items;

    public Store(String ownerName) {
        this.ownerName = ownerName;
        this.items = new ArrayList<>();
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

    public ArrayList<StoreItem> getItems() {
        return items;
    }
}
