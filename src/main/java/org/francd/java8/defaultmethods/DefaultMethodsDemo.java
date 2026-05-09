package org.francd.java8.defaultmethods;

public class DefaultMethodsDemo {

    public static void main(String[] args) {
        defaultMethodsInInterfaces();
        staticMethodsInInterfaces();
        multipleInheritance();
    }

    private static void defaultMethodsInInterfaces() {
        System.out.println("=== Default Methods in Interfaces ===");

        Vehicle car = new Car();
        car.start();
        car.stop();
        System.out.println("Speed: " + car.getSpeed());
    }

    private static void staticMethodsInInterfaces() {
        System.out.println("\n=== Static Methods in Interfaces ===");

        String info = Vehicle.info();
        System.out.println(info);

        String tireInfo = Tire.info();
        System.out.println(tireInfo);
    }

    private static void multipleInheritance() {
        System.out.println("\n=== Multiple Inheritance Resolution ===");

        FlyingCar flyingCar = new FlyingCar();
        flyingCar.start();
        flyingCar.fly();
        flyingCar.stop();

        System.out.println("Speed: " + flyingCar.getSpeed());
    }
}

interface Vehicle {
    void start();
    void stop();

    default void honk() {
        System.out.println("Beep! Beep!");
    }

    default int getSpeed() {
        return 0;
    }

    static String info() {
        return "Vehicle interface - version 1.0";
    }
}

interface Flying {
    void fly();

    default void land() {
        System.out.println("Landing...");
    }

    default int getSpeed() {
        return 100;
    }
}

interface Tire {
    static String info() {
        return "Tire interface - provides wheel information";
    }
}

class Car implements Vehicle {
    private int speed = 0;

    @Override
    public void start() {
        System.out.println("Car starting...");
        speed = 10;
    }

    @Override
    public void stop() {
        System.out.println("Car stopping...");
        speed = 0;
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}

class FlyingCar implements Vehicle, Flying {
    private int speed = 0;

    @Override
    public void start() {
        System.out.println("FlyingCar starting...");
        speed = 20;
    }

    @Override
    public void stop() {
        System.out.println("FlyingCar stopping...");
        speed = 0;
    }

    @Override
    public void fly() {
        System.out.println("FlyingCar flying...");
        speed = 200;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void honk() {
        System.out.println("FlyingCar: Whoosh!");
    }

    @Override
    public void land() {
        System.out.println("FlyingCar landing on runway...");
    }
}