package Modules.Animal;

import Modules.Map.Position;
import Modules.Map.TileObject;

import java.util.ArrayList;

public class Barn extends TileObject {

    private ArrayList<Animal> animals;

    public Barn() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setPosition(this.getPlacedTile().getPosition());
    }

    public Animal getAnimalByName(String name) {
        for (Animal animal : animals) {
            if(animal.getName().equals(name)){
                return animal;
            }
        }
        return null;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }
}
