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
        String type = switch (vehicle) {
            case Car c -> "Car: " + c.brand();
            case Bike b -> "Bike: " + b.brand();
        };
        System.out.println(type);
    }

    private static void switchPatternMatching() {
        System.out.println("\n=== Switch Pattern Matching (Final in Java 17) ===");

        Object[] objects = {"Hello", 42, 3.14, List.of(1, 2)};

        for (Object obj : objects) {
            String description = switch (obj) {
                case String s -> "String: " + s.length() + " chars";
                case Integer i -> "Integer: " + (i > 0 ? "positive" : "non-positive");
                case Double d -> "Double: " + d;
                case null -> "Null object";
                default -> "Unknown type: " + obj.getClass().getSimpleName();
            };
            System.out.println(description);
        }
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
    sealed interface Vehicle permits Car, Bike {}

    static final class Car implements Vehicle {
        private final String brand;
        Car(String brand) { this.brand = brand; }
        String brand() { return brand; }
    }

    static final class Bike implements Vehicle {
        private final String brand;
        Bike(String brand) { this.brand = brand; }
        String brand() { return brand; }
    }
}