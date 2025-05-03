package Modules.Animal;

public enum AnimalProductType {
    milk("milk",125,AnimalType.cow),
    bigMilk("big milk",190,AnimalType.cow),
    goatMilk("goat milk",225,AnimalType.goat),
    bigGoatMilk("big goat milk",345,AnimalType.goat),
    wool("wool",250,AnimalType.sheep),
    truffle("truffle",625,AnimalType.pig),
    egg("egg",50,AnimalType.chicken),
    bigEgg("big egg",95,AnimalType.chicken),
    duckEgg("duck egg",95,AnimalType.duck),
    duckFeather("duck feather",250,AnimalType.duck),
    rabbitWool("rabbit wool",340,AnimalType.rabbit),
    rabbitFoot("rabbit foot",565,AnimalType.rabbit),
    dinosaurEgg("dinosaur egg",350,AnimalType.dinosaur),
    ;

    private String name;
    private int price;
    private AnimalType producer;

    AnimalProductType(String name, int price, AnimalType producer) {
        this.name = name;
        this.price = price;
        this.producer = producer;
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
}
