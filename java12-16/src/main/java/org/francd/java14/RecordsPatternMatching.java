package org.francd.java14;

public class RecordsPatternMatching {

    public static void main(String[] args) {
        recordDemo();
        patternMatchingInstanceof();
    }

    private static void recordDemo() {
        System.out.println("=== Records (Preview 14, Final 16) ===");

        // Define a record (preview in 14, final in 16)
        var person = new Person("John", 30);
        System.out.println("Name: " + person.name() + ", Age: " + person.age());

        // Records have equals, hashCode, toString automatically
        var person2 = new Person("John", 30);
        System.out.println("Equals: " + person.equals(person2));
        System.out.println("ToString: " + person);

        // Record with custom validation
        var point = new Point(10, 20);
        System.out.println("Point: " + point.x() + ", " + point.y());

        // Records in collections
        var people = java.util.List.of(
                new Person("Alice", 25),
                new Person("Bob", 30)
        );
        people.forEach(p -> System.out.println(p.name()));
    }

    private static void patternMatchingInstanceof() {
        System.out.println("\n=== Pattern Matching for instanceof (Preview 14, Final 16) ===");

        Object obj1 = "Hello";
        Object obj2 = 42;
        Object obj3 = java.time.LocalDate.now();

        // Before Java 14
        if (obj1 instanceof String) {
            String s = (String) obj1;
            System.out.println("Length: " + s.length());
        }

        // Java 14+ - pattern matching
        if (obj1 instanceof String s) {
            System.out.println("String length: " + s.length());
        }

        if (obj2 instanceof Integer i) {
            System.out.println("Integer value: " + i);
        }

        // With conditions - need separate check for Java 14
        if (obj1 instanceof String s) {
            if (s.length() > 3) {
                System.out.println("Long string: " + s);
            }
        }

        // In switch (Java 17+)
        /* this is JAva 14 !!
        Object value = "test";
        String result = switch (value) {
            case String s -> "String: " + s;
            case Integer i -> "Integer: " + i;
            default -> "Unknown";
        };
        System.out.println("Switch result: " + result);
        */
    }

    // Record definition (final in Java 16)
    record Person(String name, int age) {
        // Canonical constructor generated automatically
    }

    record Point(int x, int y) {
        // Can add custom methods
        public int distanceFromOrigin() {
            return (int) Math.sqrt(x * x + y * y);
        }
    }
}
