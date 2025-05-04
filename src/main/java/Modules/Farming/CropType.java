package Modules.Farming;

import Modules.Enums.Season;

import java.util.ArrayList;

public enum CropType {
    blueJazz,
    carrot,
    cauliflower,
    coffeeBean,
    garlic,
    greenBean,
    kale,
    parsnip,
    potato,
    rhubarb,
    strawberry,
    tulip,
    unmilledRice,
    blueberry,
    corn,
    hops,
    hotPepper,
    melon,
    poppy,
    radish,
    redCabbage,
    starfruit,
    summerSpangle,
    summerSquash,
    sunflower,
    tomato,
    wheat,
    amaranth,
    artichoke,
    beet,
    bokChoy,
    broccoli,
    cranberries,
    eggplant,
    fairyRose,
    grape,
    pumpkin,
    yam,
    sweetGemBerry,
    powdermelon,
    ancientFruit,
    apricot,
    cherry,
    banana,
    mango,
    orange,
    peach,
    apple,
    pomegranate,
    oak,
    maple,
    pine,
    mahogany,
    mushroom,
    mystic,
    ;

    private final String name;
    private final SeedType seed;

    private final int initialPrice;

    private final int energy;

    private final boolean isEdible;


    CropType() {
        switch (this.name()) {
            case "blueJazz": {
                name = "blue jazz";
                seed = SeedType.jazz;
                initialPrice = 50;
                energy = 45;
                isEdible = true;
                break;
            }
            case "carrot": {
                name = "carrot";
                seed = SeedType.carrot;
                initialPrice = 35;
                energy = 75;
                isEdible = true;
                break;
            }
            case "cauliflower": {
                name = "cauliflower";
                seed = SeedType.cauliflower;
                initialPrice = 175;
                energy = 75;
                isEdible = true;
                break;
            }
            case "coffeeBean": {
                name = "coffee bean";
                seed = SeedType.coffeeBean;
                initialPrice = 15;
                energy = 0;
                isEdible = false;
                break;
            }
            case "garlic": {
                name = "garlic";
                seed = SeedType.garlic;
                initialPrice = 60;
                energy = 20;
                isEdible = true;
                break;
            }
            case "greenBean": {
                name = "green bean";
                seed = SeedType.beanStarter;
                initialPrice = 40;
                energy = 25;
                isEdible = true;
                break;
            }
            case "kale": {
                name = "kale";
                seed = SeedType.kale;
                initialPrice = 110;
                energy = 50;
                isEdible = true;
                break;
            }
            case "parsnip": {
                name = "parsnip";
                seed = SeedType.parsnip;
                initialPrice = 35;
                energy = 25;
                isEdible = true;
                break;
            }
            case "potato": {
                name = "potato";
                seed = SeedType.potato;
                initialPrice = 80;
                energy = 25;
                isEdible = true;
                break;
            }
            case "rhubarb": {
                name = "rhubarb";
                seed = SeedType.rhubarb;
                initialPrice = 220;
                energy = 0;
                isEdible = false;
                break;
            }
            case "strawberry": {
                name = "strawberry";
                seed = SeedType.strawberry;
                initialPrice = 120;
                energy = 50;
                isEdible = true;
                break;
            }
            case "tulip": {
                name = "tulip";
                seed = SeedType.tulipBulb;
                initialPrice = 30;
                energy = 45;
                isEdible = true;
                break;
            }
            case "unmilledRice": {
                name = "unmilled rice";
                seed = SeedType.riceShoot;
                initialPrice = 30;
                energy = 3;
                isEdible = true;
                break;
            }
            case "blueberry": {
                name = "blueberry";
                seed = SeedType.blueberry;
                initialPrice = 50;
                energy = 25;
                isEdible = true;
                break;
            }
            case "corn": {
                name = "corn";
                seed = SeedType.corn;
                initialPrice = 50;
                energy = 25;
                isEdible = true;
                break;
            }
            case "hops": {
                name = "hops";
                seed = SeedType.hopsStarter;
                initialPrice = 25;
                energy = 45;
                isEdible = true;
                break;
            }
            case "hotPepper": {
                name = "hot pepper";
                seed = SeedType.pepper;
                initialPrice = 40;
                energy = 13;
                isEdible = true;
                break;
            }
            case "melon": {
                name = "melon";
                seed = SeedType.melon;
                initialPrice = 250;
                energy = 113;
                isEdible = true;
                break;
            }
            case "poppy": {
                name = "poppy";
                seed = SeedType.poppy;
                initialPrice = 140;
                energy = 45;
                isEdible = true;
                break;
            }
            case "radish": {
                name = "radish";
                seed = SeedType.radish;
                initialPrice = 90;
                energy = 45;
                isEdible = true;
                break;
            }
            case "redCabbage": {
                name = "red cabbage";
                seed = SeedType.redCabbage;
                initialPrice = 260;
                energy = 75;
                isEdible = true;
                break;
            }
            case "starfruit": {
                name = "starfruit";
                seed = SeedType.starfruit;
                initialPrice = 750;
                energy = 125;
                isEdible = true;
                break;
            }
            case "summerSpangle": {
                name = "summer spangle";
                seed = SeedType.spangle;
                initialPrice = 90;
                energy = 45;
                isEdible = true;
                break;
            }
            case "summerSquash": {
                name = "summer squash";
                seed = SeedType.summerSquash;
                initialPrice = 45;
                energy = 63;
                isEdible = true;
                break;
            }
            case "sunflower": {
                name = "sunflower";
                seed = SeedType.sunflower;
                initialPrice = 80;
                energy = 45;
                isEdible = true;
                break;
            }
            case "tomato": {
                name = "tomato";
                seed = SeedType.tomato;
                initialPrice = 60;
                energy = 20;
                isEdible = true;
                break;
            }
            case "wheat": {
                name = "wheat";
                seed = SeedType.wheat;
                initialPrice = 25;
                energy = 0;
                isEdible = false;
                break;
            }
            case "amaranth": {
                name = "amaranth";
                seed = SeedType.amaranth;
                initialPrice = 150;
                energy = 50;
                isEdible = true;
                break;
            }
            case "artichoke": {
                name = "artichoke";
                seed = SeedType.artichoke;
                initialPrice = 160;
                energy = 30;
                isEdible = true;
                break;
            }
            case "beet": {
                name = "beet";
                seed = SeedType.beet;
                initialPrice = 100;
                energy = 30;
                isEdible = true;
                break;
            }
            case "bokChoy": {
                name = "bok choy";
                seed = SeedType.bokChoy;
                initialPrice = 80;
                energy = 25;
                isEdible = true;
                break;
            }
            case "broccoli": {
                name = "broccoli";
                seed = SeedType.broccoli;
                initialPrice = 70;
                energy = 63;
                isEdible = true;
                break;
            }
            case "cranberries": {
                name = "cranberries";
                seed = SeedType.cranberry;
                initialPrice = 75;
                energy = 38;
                isEdible = true;
                break;
            }
            case "eggplant": {
                name = "eggplant";
                seed = SeedType.eggplant;
                initialPrice = 60;
                energy = 20;
                isEdible = true;
                break;
            }
            case "fairyRose": {
                name = "fairy rose";
                seed = SeedType.fairy;
                initialPrice = 290;
                energy = 45;
                isEdible = true;
                break;
            }
            case "grape": {
                name = "grape";
                seed = SeedType.grapeStarter;
                initialPrice = 80;
                energy = 38;
                isEdible = true;
                break;
            }
            case "pumpkin": {
                name = "pumpkin";
                seed = SeedType.pumpkin;
                initialPrice = 320;
                energy = 0;
                isEdible = false;
                break;
            }
            case "yam": {
                name = "yam";
                seed = SeedType.yam;
                initialPrice = 160;
                energy = 45;
                isEdible = true;
                break;
            }
            case "sweetGemBerry": {
                name = "sweet gem berry";
                seed = SeedType.rare;
                initialPrice = 3000;
                energy = 0;
                isEdible = false;
                break;
            }
            case "powdermelon": {
                name = "powdermelon";
                seed = SeedType.powdermelon;
                initialPrice = 60;
                energy = 63;
                isEdible = true;
                break;
            }
            case "ancientFruit": {
                name = "ancient fruit";
                seed = SeedType.ancient;
                initialPrice = 550;
                energy = 0;
                isEdible = false;
                break;
            }
            case "apricot": {
                name = "apricot";
                seed = SeedType.apricot;
                initialPrice = 59;
                energy = 38;
                isEdible = true;
                break;
            }
            case "cherry": {
                name = "cherry";
                seed = SeedType.cherry;
                initialPrice = 80;
                energy = 38;
                isEdible = true;
                break;
            }
            case "banana": {
                name = "banana";
                seed = SeedType.banana;
                initialPrice = 150;
                energy = 75;
                isEdible = true;
                break;
            }
            case "mango": {
                name = "mango";
                seed = SeedType.mango;
                initialPrice = 130;
                energy = 100;
                isEdible = true;
                break;
            }
            case "orange": {
                name = "orange";
                seed = SeedType.orange;
                initialPrice = 100;
                energy = 38;
                isEdible = true;
                break;
            }
            case "peach": {
                name = "peach";
                seed = SeedType.peach;
                initialPrice = 140;
                energy = 38;
                isEdible = true;
                break;
            }
            case "apple": {
                name = "apple";
                seed = SeedType.apple;
                initialPrice = 100;
                energy = 38;
                isEdible = true;
                break;
            }
            case "pomegranate": {
                name = "pomegranate";
                seed = SeedType.pomegranate;
                initialPrice = 140;
                energy = 38;
                isEdible = true;
                break;
            }
            case "oak": {
                name = "oak";
                seed = SeedType.acorn;
                initialPrice = 150;
                energy = 0;
                isEdible = false;
                break;
            }
            case "maple": {
                name = "maple";
                seed = SeedType.maple;
                initialPrice = 200;
                energy = 0;
                isEdible = false;
                break;
            }
            case "pine": {
                name = "pine";
                seed = SeedType.pineCone;
                initialPrice = 100;
                energy = 0;
                isEdible = false;
                break;
            }
            case "mahogany": {
                name = "mahogany";
                seed = SeedType.mahogany;
                initialPrice = 2;
                energy = -2;
                isEdible = true;
                break;
            }
            case "mushroom": {
                name = "mushroom";
                seed = SeedType.mushroomTree;
                initialPrice = 40;
                energy = 38;
                isEdible = true;
                break;
            }
            case "mystic": {
                name = "mystic";
                seed = SeedType.mysticTree;
                initialPrice = 1000;
                energy = 500;
                isEdible = true;
                break;
            }
            default: {
                name = "";
                seed = null;
                initialPrice = 0;
                energy = 0;
                isEdible = false;
                break;
            }
        }
    }


//        TODO: set initial values

    public String getName() {
        return name;
    }

    public SeedType getSeed() {
        return seed;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public static CropType getCropTypeByName(String name) {
        for (CropType cropType : CropType.values()) {
            if(cropType.getName().equals(name)) {
                return cropType;
            }
        }
        return null;
    }
}
