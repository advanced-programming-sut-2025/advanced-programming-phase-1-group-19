package Modules.Animal;

public enum AnimalProductType {
    ;

    private String name;
    private int price;
    private Animal producer;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Animal getProducer() {
        return producer;
    }
}
