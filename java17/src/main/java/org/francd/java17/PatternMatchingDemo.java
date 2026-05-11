package org.francd.java17;

public class PatternMatchingDemo {

    public static void main(String[] args) {
        demonstratePatternMatching();
    }

    private static void demonstratePatternMatching() {
        System.out.println("=== Pattern Matching for instanceof (Final in Java 16) ===");

        Object[] objects = {"Hello", 42, 3.14, new Person("Alice", 25)};

        for (Object obj : objects) {
            if (obj instanceof String s) {
                System.out.println("String: " + s.length() + " chars");
            } else if (obj instanceof Integer i) {
                System.out.println("Integer: " + (i > 0 ? "positive" : "non-positive"));
            } else if (obj instanceof Double d) {
                System.out.println("Double: " + d);
            } else if (obj instanceof Person p) {
                System.out.println("Person: " + p.name() + ", " + p.age() + " years");
            }
        }

        System.out.println("\n--- Guarded patterns ---");

        Object value = "Hello World";

        if (value instanceof String s && s.length() > 5) {
            System.out.println("Long string: " + s.toUpperCase());
        }
    }

    record Person(String name, int age) {}
}