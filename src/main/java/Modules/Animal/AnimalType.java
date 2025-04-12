package Modules.Animal;

import java.util.ArrayList;

public enum AnimalType {
    ;
    private String name;
    private int capacity;
    private int cost;
    private int daysTillProduct;
    private ArrayList<AnimalProduct> products;

    AnimalType() {}

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDaysTillProduct() {
        return daysTillProduct;
    }

    public ArrayList<AnimalProduct> getProducts() {
        return products;
    }

    public AnimalType getAnimalTypeByName(String name) {}
}
