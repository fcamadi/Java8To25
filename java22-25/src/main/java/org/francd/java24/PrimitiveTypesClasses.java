package org.francd.java24;

public class PrimitiveTypesClasses {

    public static void main(String[] args) {
        primitiveClasses();
        compactClassDescriptors();
    }

    private static void primitiveClasses() {
        System.out.println("=== Primitive Classes (Preview 24) ===");

        // Primitive classes - inline value types
        // primitive class Point(int x, int y) {}

        System.out.println("Primitive classes bring value types to Java");
        System.out.println("Similar to records but more efficient for primitives");
        System.out.println("Like 'int' but with identity and methods");

        // Example concept (not actual code)
        // Point p = new Point(1, 2);
        // No boxing/unboxing overhead
    }

    private static void compactClassDescriptors() {
        System.out.println("\n=== Compact Class Descriptors (Preview 24) ===");

        // More efficient class file format
        // Smaller memory footprint
        // Faster class loading

        System.out.println("Compact descriptors reduce class file size");
        System.out.println("Improves startup time and memory usage");
    }
}