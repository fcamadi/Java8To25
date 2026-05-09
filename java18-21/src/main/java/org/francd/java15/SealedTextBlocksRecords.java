package org.francd.java15;

public class SealedTextBlocksRecords {

    public static void main(String[] args) {
        sealedClasses();
        textBlocksFinal();
        records();
    }

    private static void sealedClasses() {
        System.out.println("=== Sealed Classes (Preview 15, Final 17) ===");

        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(10, 20);
        Shape triangle = new Triangle(3, 4);

        System.out.println("Circle area: " + calculateArea(circle));
        System.out.println("Rectangle area: " + calculateArea(rectangle));
        System.out.println("Triangle area: " + calculateArea(triangle));
    }

    private static double calculateArea(Shape shape) {
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.width() * r.height();
            case Triangle t -> 0.5 * t.base() * t.height();
        };
    }

    private static void textBlocksFinal() {
        System.out.println("\n=== Text Blocks (Final in Java 15) ===");

        // Escape sequences
        String escaped = """
            Line 1\s   # Result: "Line 1   " (preserve spaces)
            """;
        System.out.println("Escaped: [" + escaped + "]");

        // Quotes without escaping
        String json = """
            {
                "name": "John",
                "age": 30
            }
            """;
        System.out.println("JSON: " + json);

        // Single quotes don't need escaping
        String sql = """
            SELECT * FROM table WHERE name = 'John'
            """;
        System.out.println("SQL: " + sql);
    }

    private static void records() {
        System.out.println("\n=== Records (Second Preview 15) ===");

        // Records are immutable data classes
        var employee = new Employee("EMP001", "Alice", 50000);
        System.out.println(employee);

        // With equals and hashCode
        var emp2 = new Employee("EMP001", "Alice", 50000);
        System.out.println("Equals: " + employee.equals(emp2));
    }

    // Sealed class - restricts which classes can extend it
    sealed interface Shape permits Circle, Rectangle, Triangle {}
    record Circle(double radius) implements Shape {}
    record Rectangle(double width, double height) implements Shape {}
    record Triangle(double base, double height) implements Shape {}

    // Non-sealed allows any class to extend
    sealed class ShapeBase permits Extender {}
    final class Extender extends ShapeBase {}

    record Employee(String id, String name, double salary) {}
}