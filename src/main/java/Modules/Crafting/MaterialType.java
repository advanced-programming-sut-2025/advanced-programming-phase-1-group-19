package Modules.Crafting;

public enum MaterialType {
    egg,
    milk,
    sugar,
    cheese,
    fiber,
    oil,
    wheatFlour,
    coffee,
    bouquet,
    weddingRing,
    wood,
    stone
    ;

    private final String name;

    MaterialType() {
        String enumName = name();
        switch (enumName) {
            case "egg":{
                this.name = "egg";
                break;
            }
            case "milk":{
                this.name = "milk";
                break;
            }
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
