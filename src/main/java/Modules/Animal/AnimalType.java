package Modules.Animal;

import java.util.ArrayList;
import java.util.List;

public enum AnimalType {
    cow(false,1500,new ArrayList<AnimalProduct>(List.of(new AnimalProduct("milk",1,AnimalProductType.milk) , new AnimalProduct("big milk",1,AnimalProductType.bigMilk))),4),
    goat(false,4000,new ArrayList<AnimalProduct>(List.of(new AnimalProduct("goat milk",1,AnimalProductType.goatMilk),new AnimalProduct("big goat milk",1,AnimalProductType.bigGoatMilk))),8),
    sheep(false,8000,new ArrayList<AnimalProduct>(List.of(new AnimalProduct("wool",1,AnimalProductType.wool))),12),
    pig(false,16000,new ArrayList<AnimalProduct>(List.of(new AnimalProduct("truffle",1,AnimalProductType.truffle))),8),
    chicken(true,800,new ArrayList<AnimalProduct>(List.of(new AnimalProduct("egg",1,AnimalProductType.egg),new AnimalProduct("big egg",1,AnimalProductType.bigEgg))),4),
    duck(true,1200,new ArrayList<AnimalProduct>(List.of(new AnimalProduct("duck egg",1,AnimalProductType.duckEgg),new AnimalProduct("duck feather",1,AnimalProductType.duckFeather))),8),
    rabbit(true,8000,new ArrayList<AnimalProduct>(List.of(new AnimalProduct("rabbit wool",1,AnimalProductType.rabbitWool),new AnimalProduct("rabbit foot",1,AnimalProductType.rabbitFoot))),12),
    dinosaur(true,14000,new ArrayList<AnimalProduct>(List.of(new AnimalProduct("dinosaur egg",1,AnimalProductType.dinosaurEgg))),8),
    ;
    private int capacity;
    private int cost;
    private int daysTillProduct;
    private ArrayList<AnimalProduct> products;
    private boolean isInCage;

    AnimalType(boolean isInCage,int cost,ArrayList<AnimalProduct> products,int capacity) {
        this.isInCage = isInCage;
        this.cost = cost;
        this.products = products;
        this.capacity = capacity;
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
        else if(name.equals("chicken")) return chicken;
        else if(name.equals("duck")) return duck;
        else if(name.equals("rabbit")) return rabbit;
        else if(name.equals("dinosaur")) return dinosaur;
        else return null;
    }

    public boolean isInCage() {
        return isInCage;
    }

    public int getCost() {
        return cost;
    }
}
