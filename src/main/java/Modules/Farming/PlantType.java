package Modules.Farming;

import Modules.Enums.Season;

import java.util.ArrayList;

public enum PlantType {
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
    private final CropType crop;
    private final ArrayList<Integer> stages;
    private final int totalTime;
    private final int reGrowth;
    private final ArrayList<Season> seasonsAvailable;
    private final boolean canBeComeGiant;
    private final boolean isTree;

    PlantType() {
        stages = new ArrayList<>();
        seasonsAvailable = new ArrayList<>();
        switch (this.name()) {
            case "blueJazz": {
                name = "blue jazz";
                seed = SeedType.jazz;
                crop = CropType.blueJazz;
                stages.add(1);
                stages.add(2);
                stages.add(2);
                stages.add(2);
                totalTime = 7;
                reGrowth = -1;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "carrot": {
                name = "carrot";
                seed = SeedType.carrot;
                crop = CropType.carrot;
                stages.add(1);
                stages.add(1);
                stages.add(1);
                totalTime = 3;
                reGrowth = -1;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "cauliflower": {
                name = "cauliflower";
                seed = SeedType.cauliflower;
                crop = CropType.cauliflower;
                stages.add(1);
                stages.add(2);
                stages.add(4);
                stages.add(4);
                stages.add(1);
                totalTime = 12;
                reGrowth = -1;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = true;
                isTree = false;
                break;
            }
            case "coffeeBean": {
                name = "coffee bean";
                seed = SeedType.coffeeBean;
                crop = CropType.coffeeBean;
                stages.add(1);
                stages.add(2);
                stages.add(2);
                stages.add(3);
                stages.add(2);
                totalTime = 10;
                reGrowth = 2;
                seasonsAvailable.add(Season.spring);
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "garlic": {
                name = "garlic";
                seed = SeedType.garlic;
                crop = CropType.garlic;
                stages.add(1);
                stages.add(1);
                stages.add(1);
                stages.add(1);
                totalTime = 4;
                reGrowth = -1;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "greenBean": {
                name = "green bean";
                seed = SeedType.beanStarter;
                crop = CropType.greenBean;
                stages.add(1);
                stages.add(1);
                stages.add(1);
                stages.add(3);
                stages.add(4);
                totalTime = 10;
                reGrowth = 3;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "kale": {
                name = "kale";
                seed = SeedType.kale;
                crop = CropType.kale;
                stages.add(1);
                stages.add(2);
                stages.add(2);
                stages.add(1);
                totalTime = 6;
                reGrowth = -1;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "parsnip": {
                name = "parsnip";
                seed = SeedType.parsnip;
                crop = CropType.parsnip;
                stages.add(1);
                stages.add(1);
                stages.add(1);
                stages.add(1);
                totalTime = 4;
                reGrowth = -1;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "potato": {
                name = "potato";
                seed = SeedType.potato;
                crop = CropType.potato;
                stages.add(1);
                stages.add(1);
                stages.add(1);
                stages.add(2);
                stages.add(1);
                totalTime = 6;
                reGrowth = -1;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "rhubarb": {
                name = "rhubarb";
                seed = SeedType.rhubarb;
                crop = CropType.rhubarb;
                stages.add(2);
                stages.add(2);
                stages.add(2);
                stages.add(3);
                stages.add(4);
                totalTime = 13;
                reGrowth = -1;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "strawberry": {
                name = "strawberry";
                seed = SeedType.strawberry;
                crop = CropType.strawberry;
                stages.add(1);
                stages.add(1);
                stages.add(2);
                stages.add(2);
                stages.add(2);
                totalTime = 8;
                reGrowth = 4;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "tulip": {
                name = "tulip";
                seed = SeedType.tulipBulb;
                crop = CropType.tulip;
                stages.add(1);
                stages.add(1);
                stages.add(2);
                stages.add(2);
                totalTime = 6;
                reGrowth = -1;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "unmilledRice": {
                name = "unmilled rice";
                seed = SeedType.riceShoot;
                crop = CropType.unmilledRice;
                stages.add(1);
                stages.add(2);
                stages.add(2);
                stages.add(3);
                totalTime = 8;
                reGrowth = -1;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "blueberry": {
                name = "blueberry";
                seed = SeedType.blueberry;
                crop = CropType.blueberry;
                stages.add(1);
                stages.add(3);
                stages.add(3);
                stages.add(4);
                stages.add(2);
                totalTime = 13;
                reGrowth = 4;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "corn": {
                name = "corn";
                seed = SeedType.corn;
                crop = CropType.corn;
                stages.add(2);
                stages.add(3);
                stages.add(3);
                stages.add(3);
                stages.add(3);
                totalTime = 14;
                reGrowth = 4;
                seasonsAvailable.add(Season.summer);
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "hops": {
                name = "hops";
                seed = SeedType.hopsStarter;
                crop = CropType.hops;
                stages.add(1);
                stages.add(1);
                stages.add(2);
                stages.add(3);
                stages.add(4);
                totalTime = 11;
                reGrowth = 1;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "hotPepper": {
                name = "hot pepper";
                seed = SeedType.pepper;
                crop = CropType.hotPepper;
                stages.add(1);
                stages.add(1);
                stages.add(1);
                stages.add(1);
                stages.add(1);
                totalTime = 5;
                reGrowth = 3;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "melon": {
                name = "melon";
                seed = SeedType.melon;
                crop = CropType.melon;
                stages.add(1);
                stages.add(2);
                stages.add(3);
                stages.add(3);
                stages.add(3);
                totalTime = 12;
                reGrowth = -1;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = true;
                isTree = false;
                break;
            }
            case "poppy": {
                name = "poppy";
                seed = SeedType.poppy;
                crop = CropType.poppy;
                stages.add(1);
                stages.add(2);
                stages.add(2);
                stages.add(2);
                totalTime = 7;
                reGrowth = -1;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "radish": {
                name = "radish";
                seed = SeedType.radish;
                crop = CropType.radish;
                stages.add(2);
                stages.add(1);
                stages.add(2);
                stages.add(1);
                totalTime = 6;
                reGrowth = -1;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "redCabbage": {
                name = "red cabbage";
                seed = SeedType.redCabbage;
                crop = CropType.redCabbage;
                stages.add(2);
                stages.add(1);
                stages.add(2);
                stages.add(2);
                stages.add(2);
                totalTime = 9;
                reGrowth = -1;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "starfruit": {
                name = "starfruit";
                seed = SeedType.starfruit;
                crop = CropType.starfruit;
                stages.add(2);
                stages.add(3);
                stages.add(2);
                stages.add(3);
                stages.add(3);
                totalTime = 13;
                reGrowth = -1;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "summerSpangle": {
                name = "summer spangle";
                seed = SeedType.spangle;
                crop = CropType.summerSpangle;
                stages.add(1);
                stages.add(2);
                stages.add(3);
                stages.add(1);
                totalTime = 8;
                reGrowth = -1;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "summerSquash": {
                name = "summer squash";
                seed = SeedType.summerSquash;
                crop = CropType.summerSquash;
                stages.add(1);
                stages.add(1);
                stages.add(1);
                stages.add(2);
                stages.add(1);
                totalTime = 6;
                reGrowth = 3;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "sunflower": {
                name = "sunflower";
                seed = SeedType.sunflower;
                crop = CropType.sunflower;
                stages.add(1);
                stages.add(2);
                stages.add(3);
                stages.add(2);
                totalTime = 8;
                reGrowth = -1;
                seasonsAvailable.add(Season.summer);
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "tomato": {
                name = "tomato";
                seed = SeedType.tomato;
                crop = CropType.tomato;
                stages.add(2);
                stages.add(2);
                stages.add(2);
                stages.add(2);
                stages.add(3);
                totalTime = 11;
                reGrowth = 4;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "wheat": {
                name = "wheat";
                seed = SeedType.wheat;
                crop = CropType.wheat;
                stages.add(1);
                stages.add(1);
                stages.add(1);
                stages.add(1);
                totalTime = 4;
                reGrowth = -1;
                seasonsAvailable.add(Season.summer);
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "amaranth": {
                name = "amaranth";
                seed = SeedType.amaranth;
                crop = CropType.amaranth;
                stages.add(1);
                stages.add(2);
                stages.add(2);
                stages.add(2);
                totalTime = 7;
                reGrowth = -1;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "artichoke": {
                name = "artichoke";
                seed = SeedType.artichoke;
                crop = CropType.artichoke;
                stages.add(2);
                stages.add(2);
                stages.add(1);
                stages.add(2);
                stages.add(1);
                totalTime = 8;
                reGrowth = -1;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "beet": {
                name = "beet";
                seed = SeedType.beet;
                crop = CropType.beet;
                stages.add(1);
                stages.add(1);
                stages.add(2);
                stages.add(2);
                totalTime = 6;
                reGrowth = -1;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "bokChoy": {
                name = "bok choy";
                seed = SeedType.bokChoy;
                crop = CropType.bokChoy;
                stages.add(1);
                stages.add(1);
                stages.add(1);
                stages.add(1);
                totalTime = 4;
                reGrowth = -1;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "broccoli": {
                name = "broccoli";
                seed = SeedType.broccoli;
                crop = CropType.broccoli;
                stages.add(2);
                stages.add(2);
                stages.add(2);
                stages.add(2);
                totalTime = 8;
                reGrowth = 4;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "cranberries": {
                name = "cranberries";
                seed = SeedType.cranberry;
                crop = CropType.cranberries;
                stages.add(1);
                stages.add(2);
                stages.add(1);
                stages.add(1);
                stages.add(2);
                totalTime = 7;
                reGrowth = 5;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "eggplant": {
                name = "eggplant";
                seed = SeedType.eggplant;
                crop = CropType.eggplant;
                stages.add(1);
                stages.add(1);
                stages.add(1);
                stages.add(1);
                totalTime = 5;
                reGrowth = 5;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "fairyRose": {
                name = "fairy rose";
                seed = SeedType.fairy;
                crop = CropType.fairyRose;
                stages.add(1);
                stages.add(4);
                stages.add(4);
                stages.add(3);
                totalTime = 12;
                reGrowth = -1;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "grape": {
                name = "grape";
                seed = SeedType.grapeStarter;
                crop = CropType.grape;
                stages.add(1);
                stages.add(1);
                stages.add(2);
                stages.add(3);
                stages.add(3);
                totalTime = 10;
                reGrowth = 3;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "pumpkin": {
                name = "pumpkin";
                seed = SeedType.pumpkin;
                crop = CropType.pumpkin;
                stages.add(1);
                stages.add(2);
                stages.add(3);
                stages.add(4);
                stages.add(3);
                totalTime = 13;
                reGrowth = -1;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = true;
                isTree = false;
                break;
            }
            case "yam": {
                name = "yam";
                seed = SeedType.yam;
                crop = CropType.yam;
                stages.add(1);
                stages.add(3);
                stages.add(3);
                stages.add(3);
                totalTime = 10;
                reGrowth = -1;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "sweetGemBerry": {
                name = "sweet gem berry";
                seed = SeedType.rare;
                crop = CropType.sweetGemBerry;
                stages.add(2);
                stages.add(4);
                stages.add(6);
                stages.add(6);
                stages.add(6);
                totalTime = 24;
                reGrowth = -1;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "powdermelon": {
                name = "powdermelon";
                seed = SeedType.powdermelon;
                crop = CropType.powdermelon;
                stages.add(1);
                stages.add(2);
                stages.add(1);
                stages.add(2);
                stages.add(1);
                totalTime = 7;
                reGrowth = -1;
                seasonsAvailable.add(Season.winter);
                canBeComeGiant = true;
                isTree = false;
                break;
            }
            case "ancientFruit": {
                name = "ancient fruit";
                seed = SeedType.ancient;
                crop = CropType.ancientFruit;
                stages.add(2);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(5);
                totalTime = 28;
                reGrowth = 7;
                seasonsAvailable.add(Season.spring);
                seasonsAvailable.add(Season.summer);
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = false;
                break;
            }
            case "apricotTree": {
                name = "apricot tree";
                seed = SeedType.apricot;
                crop = CropType.apricot;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 1;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "cherryTree": {
                name = "cherry tree";
                seed = SeedType.cherry;
                crop = CropType.cherry;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 1;
                seasonsAvailable.add(Season.spring);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "bananaTree": {
                name = "banana tree";
                seed = SeedType.banana;
                crop = CropType.banana;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 1;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "mangoTree": {
                name = "mango tree";
                seed = SeedType.mango;
                crop = CropType.mango;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 1;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "orangeTree": {
                name = "orange tree";
                seed = SeedType.orange;
                crop = CropType.orange;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 1;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "peachTree": {
                name = "peach tree";
                seed = SeedType.peach;
                crop = CropType.peach;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 1;
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "appleTree": {
                name = "apple tree";
                seed = SeedType.apple;
                crop = CropType.apple;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 1;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "pomegranateTree": {
                name = "pomegranate tree";
                seed = SeedType.pomegranate;
                crop = CropType.pomegranate;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 1;
                seasonsAvailable.add(Season.fall);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "oakTree": {
                name = "oak tree";
                seed = SeedType.acorn;
                crop = CropType.oak;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 7;
                seasonsAvailable.add(Season.spring);
                seasonsAvailable.add(Season.summer);
                seasonsAvailable.add(Season.fall);
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "mapleTree": {
                name = "maple tree";
                seed = SeedType.maple;
                crop = CropType.maple;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 9;
                seasonsAvailable.add(Season.spring);
                seasonsAvailable.add(Season.summer);
                seasonsAvailable.add(Season.fall);
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "pineTree": {
                name = "pine tree";
                seed = SeedType.pineCone;
                crop = CropType.pine;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 5;
                seasonsAvailable.add(Season.spring);
                seasonsAvailable.add(Season.summer);
                seasonsAvailable.add(Season.fall);
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "mahoganyTree": {
                name = "mahogany tree";
                seed = SeedType.mahogany;
                crop = CropType.mahogany;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 1;
                seasonsAvailable.add(Season.spring);
                seasonsAvailable.add(Season.summer);
                seasonsAvailable.add(Season.fall);
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "mushroomTree": {
                name = "mushroom tree";
                seed = SeedType.mushroomTree;
                crop = CropType.mushroom;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 1;
                seasonsAvailable.add(Season.spring);
                seasonsAvailable.add(Season.summer);
                seasonsAvailable.add(Season.fall);
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            case "mysticTree": {
                name = "mystic tree";
                seed = SeedType.mysticTree;
                crop = CropType.mystic;
                stages.add(7);
                stages.add(7);
                stages.add(7);
                stages.add(7);
                totalTime = 28;
                reGrowth = 7;
                seasonsAvailable.add(Season.spring);
                seasonsAvailable.add(Season.summer);
                seasonsAvailable.add(Season.fall);
                seasonsAvailable.add(Season.summer);
                canBeComeGiant = false;
                isTree = true;
                break;
            }
            default: {
                name = "";
                seed = null;
                crop = null;
                totalTime = 0;
                reGrowth = -1;
                canBeComeGiant = false;
                isTree = false;
                break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public SeedType getSeed() {
        return seed;
    }

    public ArrayList<Integer> getStages() {
        return stages;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public int getReGrowth() {
        return reGrowth;
    }

    public ArrayList<Season> getSeasonsAvailable() {
        return seasonsAvailable;
    }

    public boolean isCanBeComeGiant() {
        return canBeComeGiant;
    }

    public CropType getCrop() {
        return crop;
    }

    public boolean isTree() {
        return isTree;
    }

    public static PlantType getPlantTypeByName(String name) {
        for(PlantType plantType : PlantType.values()) {
            if(plantType.getName().equals(name)) {
                return plantType;
            }
        }
        return null;
    }

    public static PlantType getPlantTypeBySeed(SeedType seed) {
        for (PlantType plantType : PlantType.values()) {
            if(plantType.getSeed().equals(seed)) {
                return plantType;
            }
        }
        return null;
    }
}
