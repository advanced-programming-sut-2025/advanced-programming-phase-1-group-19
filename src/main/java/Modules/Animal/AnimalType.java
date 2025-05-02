package Modules.Animal;

import java.util.ArrayList;

public enum AnimalType {
    cow(false),
    goat(false),
    sheep(false),
    pig(false),
    chicken(true),
    duck(true),
    rabbit(true),
    dinosaur(true),
    ;
    private String name;
    private int capacity;
    private int cost;
    private int daysTillProduct;
    private ArrayList<AnimalProduct> products;
    private boolean isInCage;

    AnimalType(boolean isInCage) {
        this.isInCage = isInCage;
    }

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

    public static AnimalType getAnimalTypeByName(String name) {
        if(name.equals("cow")) return cow;
        else if(name.equals("goat")) return goat;
        else if(name.equals("sheep")) return sheep;
        else if(name.equals("pig")) return pig;
        else return null;
    }
}
