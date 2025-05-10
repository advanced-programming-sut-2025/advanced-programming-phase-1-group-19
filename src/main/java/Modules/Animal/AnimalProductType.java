package Modules.Animal;

public enum AnimalProductType {
    milk("milk",125,AnimalType.cow, true),
    bigMilk("big milk",190,AnimalType.cow, true),
    goatMilk("goat milk",225,AnimalType.goat, true),
    bigGoatMilk("big goat milk",345,AnimalType.goat, true),
    wool("wool",250,AnimalType.sheep, false),
    truffle("truffle",625,AnimalType.pig, false),
    egg("egg",50,AnimalType.chicken, true),
    bigEgg("big egg",95,AnimalType.chicken, true),
    duckEgg("duck egg",95,AnimalType.duck, true),
    duckFeather("duck feather",250,AnimalType.duck, false),
    rabbitWool("rabbit wool",340,AnimalType.rabbit, false),
    rabbitFoot("rabbit foot",565,AnimalType.rabbit, true),
    dinosaurEgg("dinosaur egg",350,AnimalType.dinosaur, true),
    ;

    private String name;
    private int price;
    private AnimalType producer;
    private boolean isEdible;
    AnimalProductType(String name, int price, AnimalType producer, boolean isEdible) {
        this.name = name;
        this.price = price;
        this.producer = producer;
        this.isEdible = isEdible;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public AnimalType getProducer() {
        return producer;
    }

    public boolean isEdible() {
        return isEdible;
    }
}
