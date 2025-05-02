package Modules.Animal;

import Modules.Map.TileObject;

import java.util.ArrayList;

public class Coop extends TileObject {

    public ArrayList<Animal> animals;

    public Coop() {
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
