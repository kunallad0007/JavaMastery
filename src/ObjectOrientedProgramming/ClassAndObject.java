package ObjectOrientedProgramming;

// Basic way of writing the code
class Car{
    public void carName(String name){
        System.out.println("Name: " + name);
    }
    public void carEngine(String engine){
        System.out.println("Engine: " + engine);
    }
}

//more clean of wanted to do only this things then we can access the class using constructor

class Animal{
    String animalName;
    String animalBreed;
    Animal(String animalName, String animalBreed){
        this.animalName = animalName;
        this.animalBreed = animalBreed;
    }

    public void display(){
        System.out.println("--- Animal Details ---");
        System.out.println("Name : " + animalName);
        System.out.println("Breed: " + animalBreed);
    }
}

public class ClassAndObject {
    public static void main(String[] args) {
       Car car1 = new Car();
       Car car2 = new Car();
       System.out.println("--- Car Details ---");
       car1.carName("BMW");
       car1.carEngine("S55 Engine");

       car2.carName("Ferrari");
       car2.carEngine("F136 Engine");

//     Now we can directly call the class using the constructor
       Animal animal1 = new Animal("Lion", "Barbary Lion");
       Animal animal2 = new Animal("Deer", "Barasingha");

//       Now, let's print
       animal1.display();
       animal2.display();

    }
}
