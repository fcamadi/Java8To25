package org.francd.java17;

public class RecordsDemo {

    public static void main(String[] args) {
        demonstrateRecords();
    }

    private static void demonstrateRecords() {
        System.out.println("=== Records (Preview 14, Final in Java 16) ===");

        var person = new Person("John", 30);
        System.out.println("Name: " + person.name() + ", Age: " + person.age());
        System.out.println("ToString: " + person);

        var person2 = new Person("John", 30);
        System.out.println("Equals: " + person.equals(person2));
        System.out.println("HashCode: " + person.hashCode());

        var point = new Point(10, 20);
        System.out.println("Point: " + point.x() + ", " + point.y());
        System.out.println("Distance: " + point.distanceFromOrigin());

        var employee = new Employee("EMP001", "Alice", 50000);
        System.out.println("Employee: " + employee);

        var people = java.util.List.of(
            new Person("Alice", 25),
            new Person("Bob", 30)
        );
        System.out.println("People list:");
        people.forEach(p -> System.out.println("  - " + p.name()));

        var coords = java.util.List.of(
            new Coordinate(1, 2),
            new Coordinate(3, 4)
        );
        System.out.println("Coordinates: " + coords);
    }

    record Person(String name, int age) {}

    record Point(int x, int y) {
        public int distanceFromOrigin() {
            return (int) Math.sqrt(x * x + y * y);
        }
    }

    record Employee(String id, String name, double salary) {}

    record Coordinate(double lat, double lon) {}
}