package org.francd.java23;

public class ImplicitClassesScoping {

    public static void main(String[] args) {
        implicitClasses();
    }

    private static void implicitClasses() {
        System.out.println("=== Implicit Classes (Preview 23) ===");

        // Implicit classes allow single-file programs without explicit class declaration
        System.out.println("Hello from implicit class demo");

        // Variables in source-file scope
        var message = "World";
        greet(message);

        // Using a helper class (inner class)
        var helper = new ImplicitClassesScoping().new Helper();
        helper.help();

        System.out.println("\nIn Java 23+, you can have multiple classes in one source file");
        System.out.println("without explicit top-level class declaration (preview feature)");
    }

    private static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }

    class Helper {
        void help() { System.out.println("Helper here!"); }
    }
}