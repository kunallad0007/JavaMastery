package ObjectOrientedProgramming;

/*
Challenge:

    Class vehicle:
        Brand, model, year

    Sub-Class: Car, Bike, Truck
        common method: carType, bikeType, truckType

    Output:
        --- Car Details ---
        Brand : BMW
        Model : X5
        Year  : 2022
        Car Type: SUV

        --- Bike Details ---
        Brand : Yamaha
        Model : R15
        Year  : 2021
        Bike Type: Sports Bike

        --- Truck Details ---
        Brand : Tata
        Model : Prima LX
        Year  : 2020
        Truck Type: Heavy Duty Truck
*/

//Vehicle Class
class Vehicle{
    private final String vehicleBrand, vehicleModel;
    private final int vehicleYear;

    Vehicle(String vehicleBrand, String vehicleModel, int vehicleYear){
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
    }

    public void showDetails(){
        System.out.println("Brand : " + vehicleBrand);
        System.out.println("Model : " + vehicleModel);
        System.out.println("Year : " + vehicleYear);
    }

}

//Car Class
class Car extends Vehicle{
    Car(String Brand, String Model, int Year){
        super(Brand, Model, Year);
    }

    public void carType(String carType){
        System.out.println("Car Type: " + carType);
    }

    public void fuelType(String fuelType){
        System.out.println("Fuel Type: " + fuelType);
    }
}

//Bike Class
class Bike extends Vehicle{
    Bike(String Brand, String Model, int Year){
        super(Brand, Model, Year);
    }

    public void bikeType(String Type){
        System.out.println("Bike Type: " + Type);
    }

    public void fuelType(String fuelType){
        System.out.println("Fuel Type: " + fuelType);
    }
}

//Truck Class
class Truck extends Vehicle{
    Truck(String Brand, String Model, int Year){
        super(Brand, Model, Year);
    }

    public void truckType(String Type){
        System.out.println("Truck Type: " + Type);
    }

    public void fuelType(String fuelType){
        System.out.println("Fuel Type: " + fuelType);
    }
}

//Main Class
public class PracticeProgram1 {
    public static void main(String[] args) {
        Car car = new Car("BMW", "X5", 2022);
        Bike bike = new Bike("Yamaha", "R15", 2021);
        Truck truck = new Truck("Tata", "Prima LX", 2020);

        System.out.println("--- Car Details ---");
        car.showDetails();
        car.carType("SUV");
        car.fuelType("Diesel");
        System.out.println();

        System.out.println("--- Bike Details ---");
        bike.showDetails();
        bike.bikeType("Sports Bike");
        bike.fuelType("Petrol");
        System.out.println();

        System.out.println("--- Truck Details ---");
        truck.showDetails();
        truck.truckType("Heavy Duty Truck");
        truck.fuelType("Diesel");
        System.out.println();

    }
}
