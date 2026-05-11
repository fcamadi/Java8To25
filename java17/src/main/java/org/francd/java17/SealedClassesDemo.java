package org.francd.java17;

public class SealedClassesDemo {

    public static void main(String[] args) {
        demonstrateSealedClasses();
    }

    private static void demonstrateSealedClasses() {
        System.out.println("=== Sealed Classes (Final in Java 17) ===");

        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(10, 20);
        Shape triangle = new Triangle(3, 4);

        System.out.println("Circle area: " + calculateArea(circle));
        System.out.println("Rectangle area: " + calculateArea(rectangle));
        System.out.println("Triangle area: " + calculateArea(triangle));

        Vehicle car = new Car("Toyota");
        Vehicle bike = new Bike("Yamaha");

        printVehicleType(car);
        printVehicleType(bike);

        System.out.println("\n=== Non-sealed allows any class ===");
        ShapeBase base = new Extender();
        System.out.println("Extender works: " + (base instanceof Extender));
    }

    private static double calculateArea(Shape shape) {
        if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return Math.PI * c.radius() * c.radius();
        } else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return r.width() * r.height();
        } else if (shape instanceof Triangle) {
            Triangle t = (Triangle) shape;
            return 0.5 * t.base() * t.height();
        }
        return 0;
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
}

sealed interface Shape permits Circle, Rectangle, Triangle {}

record Circle(double radius) implements Shape {}

record Rectangle(double width, double height) implements Shape {}

record Triangle(double base, double height) implements Shape {}


sealed interface Vehicle permits Car, Bike {
    int getConsumption();
}

final class Car implements Vehicle {
    private final String brand;
    Car(String brand) { this.brand = brand; }
    String brand() { return brand; }
    public int getConsumption() { return 8; }
}

final class Bike implements Vehicle {
    private final String brand;
    Bike(String brand) { this.brand = brand; }
    String brand() { return brand; }
    public int getConsumption() { return 0; }
}

sealed class ShapeBase permits Extender {}

final class Extender extends ShapeBase {}