abstract class Vehicle {
    abstract void startEngine();
    abstract void stopEngine();
}

class Car extends Vehicle {
    void startEngine() {
        System.out.println("Car engine started ");
    }
    void stopEngine() {
        System.out.println("Car engine stopped ");
    }
}

class Bike extends Vehicle {
    void startEngine() {
        System.out.println("Bike engine started ");
    }
    void stopEngine() {
        System.out.println("Bike engine stopped ");
    }
}

public class Main3 {
    public static void main(String[] args) {
        Vehicle car  = new Car();
        Vehicle bike = new Bike();

        car.startEngine();    
        car.stopEngine();     
        System.out.println("------------------------------");
        bike.startEngine();   
        bike.stopEngine();    
    }
}