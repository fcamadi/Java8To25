package org.francd.java17;

import java.util.List;

public class SealedSwitchPatterns {

    public static void main(String[] args) {
        sealedClassesFinal();
        switchPatternMatching();
        newFeatures();
    }

    private static void sealedClassesFinal() {
        System.out.println("=== Sealed Classes (Final in Java 17) ===");

        Vehicle car = new Car("Toyota");
        Vehicle bike = new Bike("Yamaha");

        printVehicleType(car);
        printVehicleType(bike);
    }

    private static void printVehicleType(Vehicle vehicle) {
        String type;
        if (vehicle instanceof Car) {
            type = "Car: " + ((Car) vehicle).brand();
        } else if (vehicle instanceof Bike) {
            type = "Bike: " + ((Bike) vehicle).brand();
        } else {
            type = "Unknown";
        }
        System.out.println(type);
    }

    private static void switchPatternMatching() {
        System.out.println("\n=== Pattern Matching for instanceof (Final in Java 16) ===");

        Object[] objects = {"Hello", 42, 3.14, List.of(1, 2)};

        for (Object obj : objects) {
            String description = getDescription(obj);
            System.out.println(description);
        }
    }

    private static String getDescription(Object obj) {
        if (obj == null) {
            return "Null object";
        }
        if (obj instanceof String) {
            String s = (String) obj;
            return "String: " + s.length() + " chars";
        }
        if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            return "Integer: " + (i > 0 ? "positive" : "non-positive");
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            return "Double: " + d;
        }
        return "Unknown type: " + obj.getClass().getSimpleName();
    }

    private static void newFeatures() {
        System.out.println("\n=== Other Java 17 Features ===");

        // Strange Underscore Changes
        // var _ = "underscore"; // Not allowed in Java 17

        // Sealed class improvements
        // Pattern matching for switch (final)

        // Stream.toList() already covered

        // Foreign Function Interface (Preview)
        // Memory API (Preview)

        // ZGC - Scalable low-latency garbage collector
        System.out.println("ZGC available: " +
            Runtime.getRuntime().availableProcessors() + " processors");
    }

    // Sealed hierarchy
    sealed interface Vehicle permits Car, Bike {
        int getComsuption();
    }

    static final class Car implements Vehicle {
        private final String brand;
        Car(String brand) { this.brand = brand; }
        String brand() { return brand; }

        @Override
        public int getComsuption() {
            return 8;
        }
    }

    static final class Bike implements Vehicle {
        private final String brand;
        Bike(String brand) { this.brand = brand; }
        String brand() { return brand; }
    @Override
        public int getComsuption() {
            return 0;
        }
    }
}