package org.francd.java25;

public class StringTemplates {

    public static void main(String[] args) {
        stringTemplatesFinal();
        valAndConst();
    }

    private static void stringTemplatesFinal() {
        System.out.println("=== String Templates (Final in Java 25) ===");

        // String templates are finally final in Java 25!
        String name = "John";
        int age = 30;

        // STR template
        // String greeting = STR."Hello \{name}, you are \{age} years old";

        // Using formatted (available since Java 12)
        String formatted = "Hello %s, you are %d years old".formatted(name, age);
        System.out.println("Formatted: " + formatted);

        // FMT for formatting
        // String aligned = FMT."""
        //     Name:  %-10s\{name}
        //     Age:   %03d\{age}
        // """;

        System.out.println("\nTemplate benefits:");
        System.out.println("- Type safety: templates verified at compile time");
        System.out.println("- Interpolation: embedded expressions");
        System.out.println("- Custom templates: STR, FMT, RAW");
    }

    private static void valAndConst() {
        System.out.println("\n=== val and const (Preview 25) ===");

        // val - like var but for final
        // val name = "John";  // final String name = "John"

        // const - compile-time constant
        // const int MAX = 100;

        // Demonstrating with existing code
        final String JAVA_VERSION = "25";
        System.out.println("Java version: " + JAVA_VERSION);
    }
}