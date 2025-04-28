package Modules.Farming;

public enum SeedType {
    jazz,
    carrot,
    cauliflower,
    coffeeBean,
    garlic,
    beanStarter,
    kale,
    parsnip,
    potato,
    rhubarb,
    strawberry,
    tulipBulb,
    riceShoot,
    blueberry,
    corn,
    hopsStarter,
    pepper,
    melon,
    poppy,
    radish,
    redCabbage,
    starfruit,
    spangle,
    summerSquash,
    sunflower,
    tomato,
    wheat,
    amaranth,
    artichoke,
    beet,
    bokChoy,
    broccoli,
    cranberry,
    eggplant,
    fairy,
    grapeStarter,
    pumpkin,
    yam,
    rare,
    powdermelon,
    ancient,
    apricot,
    cherry,
    banana,
    mango,
    orange,
    peach,
    apple,
    pomegranate,
    acorn,
    maple,
    pineCone,
    mahogany,
    mushroomTree,
    mysticTree,
    ;
    private final String name;

    SeedType() {
        String enumName = name();
        switch (enumName) {
            case "coffeeBean":
                this.name = "coffee bean";
                break;
            case "beanStarter":
                this.name = "bean starter";
                break;
            case "tulipBulb":
                this.name = "tulip bulb";
                break;
            case "riceShoot":
                this.name = "rice shoot";
                break;
            case "hopsStarter":
                this.name = "hops starter";
                break;
            case "redCabbage":
                this.name = "red cabbage";
                break;
            case "summerSquash":
                this.name = "summer squash";
                break;
            case "bokChoy":
                this.name = "bok choy";
                break;
            case "grapeStarter":
                this.name = "grape starter";
                break;
            case "pineCone":
                this.name = "pine cone";
                break;
            case "mushroomTree":
                this.name = "mushroom tree";
                break;
            case "mysticTree":
                this.name = "mystic tree";
                break;
            default:
                this.name = enumName;
                break;
        }
    }


    public String getName() {
        return name;
    }
}
