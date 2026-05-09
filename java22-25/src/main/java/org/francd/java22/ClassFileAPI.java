package org.francd.java22;

public class ClassFileAPI {

    public static void main(String[] args) {
        classFileApi();
        streamGatherers();
    }

    private static void classFileApi() {
        System.out.println("=== Class-File API (Final in Java 22) ===");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println();

        System.out.println("The Class-File API allows parsing and building class files.");
        System.out.println("""
            Key classes:
            - ClassFile / ClassModel: represents a class file
            - MethodModel / FieldModel: represents methods/fields
            - ConstantPool: contains constant pool entries

            Usage example:
            byte[] bytecode = ...;
            ClassModel cm = ClassFile.parse(bytecode);
            System.out.println(cm.thisClass().asInternalName());
            for (MethodModel m : cm.methods()) {
                System.out.println(m.name().stringValue());
            }
            """);

        demonstrateWithReflection();
    }

    private static void demonstrateWithReflection() {
        System.out.println("\n--- Using Reflection as alternative ---");
        Class<?> clazz = ClassFileAPI.class;
        System.out.println("Class name: " + clazz.getName());
        System.out.println("Methods: " + clazz.getDeclaredMethods().length);
        System.out.println("Fields: " + clazz.getDeclaredFields().length);
    }

    private static void streamGatherers() {
        System.out.println("\n=== Stream Gatherers (Final in Java 22) ===");

        // Window fixed - groups of n elements
        var windowed = java.util.stream.Stream.of(1, 2, 3, 4, 5)
            .gather(java.util.stream.Gatherers.windowFixed(3))
            .toList();
        System.out.println("Window fixed (size 3): " + windowed);

        // Window sliding - overlapping groups
        var sliding = java.util.stream.Stream.of(1, 2, 3, 4, 5)
            .gather(java.util.stream.Gatherers.windowSliding(3))
            .toList();
        System.out.println("Window sliding (size 3): " + sliding);

        // Fold - reduce with initial value
        var folded = java.util.stream.Stream.of("a", "b", "c")
            .gather(java.util.stream.Gatherers.fold(() -> "", (acc, s) -> acc + s))
            .findFirst()
            .orElse("");
        System.out.println("Fold: " + folded);
    }
}