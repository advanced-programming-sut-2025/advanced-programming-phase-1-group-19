package Modules.Crafting;

public enum MaterialType {
    sugar,
    cheese,
    fiber,
    oil,
    wheatFlour,
    coffee,
    bouquet,
    weddingRing,
    wood,
    stone,
    ironOre,
    ironBar,
    copperOre,
    copperBar,
    goldOre,
    goldBar,
    iridiumOre,
    iridiumBar,
    coal,
    pickle,
    wine,
    hardWood,
    diamond,
    quartz,

    ;

    private String name;

    MaterialType() {
        String enumName = name();
        switch (enumName) {
            case "sugar":{
                this.name = "sugar";
                break;
            }
            case "cheese":{
                this.name = "cheese";
                break;
            }
            case "fiber":{
                this.name = "fiber";
                break;
            }
            case "oil":{
                this.name = "oil";
                break;
            }
            case "bread":{
                this.name = "bread";
                break;
            }
            case "wheatFlour":{
                this.name = "wheatFlour";
                break;
            }
            case "coffee":{
                this.name = "coffee";
                break;
            }
            case "bouquet":{
                this.name = "bouquet";
                break;
            }
            case "weddingRing":{
                this.name = "weddingRing";
                break;
            }
            case "wood":{
                this.name = "wood";
                break;
            }
            case "stone":{
                this.name = "stone";
                break;
            }
            case "ironOre":{
                this.name = "iron Ore";
                break;
            }
            case "ironBar":{
                this.name = "iron Bar";
                break;
            }
            case "copperOre":{
                this.name = "copper Ore";
                break;
            }
            case "copperBar":{
                this.name = "copper Bar";
                break;
            }
            case "goldOre":{
                this.name = "gold Ore";
                break;
            }
            case "goldBar":{
                this.name = "gold Bar";
                break;
            }
            case "iridiumOre":{
                this.name = "iridium Ore";
                break;
            }
            case "iridiumBar":{
                this.name = "iridium Bar";
                break;
            }
            case "coal":{
                this.name = "coal";
                break;
            }
            case "pickle":{
                this.name = "pickle";
                break;
            }
            case "wine":{
                this.name = "wine";
                break;
            }
            case "hardWood":{
                this.name = "hard Wood";
                break;
            }
            default:{

            }
        }
    }
    public String getName() {
        return name;
    }

    public static MaterialType getMaterialTypeByName(String name) {
        for (MaterialType mt : MaterialType.values()) {
            if (mt.name.equals(name)) {
                return mt;
            }
        }
        return null;
    }
}
