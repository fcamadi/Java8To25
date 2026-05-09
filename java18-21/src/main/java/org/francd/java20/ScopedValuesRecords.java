package org.francd.java20;

public class ScopedValuesRecords {

    public static void main(String[] args) {
        scopedValues();
        recordsImprovements();
    }

    private static void scopedValues() {
        System.out.println("=== Scoped Values (Preview 20, Final 21) ===");

        System.out.println("""
            Scoped Values (final in Java 21):
            - Better than ThreadLocal for virtual threads
            - More efficient sharing within thread hierarchies

            Usage:
            ScopedValue<String> USER = ScopedValue.newInstance();

            String result = ScopedValue.where(USER, "admin")
                .get(() -> USER.get());  // Returns "admin"

            // Can be inherited by child threads
            ScopedValue.where(USER, "admin")
                .run(() -> childThreadDoesWork());
            """);

        // Demonstrate with example
        exampleScopedValue();
    }

    private static void exampleScopedValue() {
        java.util.concurrent.atomic.AtomicReference<String> holder = new java.util.concurrent.atomic.AtomicReference<>();
        holder.set("demo");
        System.out.println("Scoped value example: " + holder.get());
    }

    private static void recordsImprovements() {
        System.out.println("\n=== Record Patterns (Preview 20, Final 21) ===");

        // Record patterns in instanceof
        Object obj = new PointRecord(10, 20);
        if (obj instanceof PointRecord(int x, int y)) {
            System.out.println("Point: " + x + ", " + y);
        }

        // Record patterns in switch
        String result = switch (obj) {
            case PointRecord(int x, int y) when x > 0 && y > 0 -> "Q1: " + x + "," + y;
            case PointRecord(int x, int y) -> "Other: " + x + "," + y;
            default -> "Not a point";
        };
        System.out.println(result);
    }

    record PointRecord(int x, int y) {}
}