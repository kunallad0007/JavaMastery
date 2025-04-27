package ObjectOrientedProgramming;

class Car{
    public void carName(String name){
//        String test = "Test";
        System.out.println("Car Name: " + name);
    }
    public void carEngine(String engine){
        System.out.println("Car Engine: " + engine);
    }
}

public class ClassAndObject {
    public static void main(String[] args) {
       Car car1 = new Car();
       Car car2 = new Car();

       car1.carName("BMW");
       car1.carEngine("S55 Engine");

       car2.carName("Ferrari");
       car2.carEngine("F136 Engine");

//       car1.carName("test car").test;
    }
}
