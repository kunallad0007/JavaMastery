package ObjectOrientedProgramming;

class Animal{
    String animal, animalName, animalBreed;
    Animal(String animal, String animalName, String animalBreed){
        this.animal = animal;
        this.animalName = animalName;
        this.animalBreed = animalBreed;
    }

    public void getAnimal(){
        System.out.println(animal + " Name: " + animalName);
        System.out.println(animal + " Breed: " + animalBreed);
    }

}

class Dog extends Animal{

    Dog(String animal, String animalName, String animalBreed){
        super(animal, animalName, animalBreed);
    }

    public void sound(){
        System.out.println("Dog makes barking sound");
    }
}

class Lion extends Animal{

    Lion(String animal, String animalName, String animalBreed){
        super(animal, animalName, animalBreed);
    }

    public void sound(){
        System.out.println("Lion makes roaring sound");
    }
}

public class Inheritances {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Dog","Jassy", "German Shepard");
        Lion lion1 = new Lion("Lion", "Shera", "Barbary Lion");

        System.out.println("--- "+ dog1.animal +" Details ---");
        dog1.sound();
        dog1.getAnimal();

        System.out.println("--- "+ lion1.animal +" Details ---");
        lion1.sound();
        lion1.getAnimal();
    }
}
