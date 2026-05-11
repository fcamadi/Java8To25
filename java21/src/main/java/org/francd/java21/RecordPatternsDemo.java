package org.francd.java21;

import java.util.List;

/**
 * Demonstrates Record Patterns in Java 21.
 *
 * This class shows how to use pattern matching with records to destructure
 * and inspect record components directly in instanceof checks and switch expressions.
 *
 * Topics covered:
 * - Basic record patterns in instanceof (destructure records directly)
 * - Nested record patterns (records containing other records)
 * - Record patterns in switch expressions
 * - Guarded patterns (patterns with conditions)
 *
 * Note: This is DIFFERENT from RecordsDemo in java17 module which shows how
 * to CREATE and USE records. This class shows how to DESTUCTURE records
 * using pattern matching.
 */
public class RecordPatternsDemo {

    public static void main(String[] args) {
        demonstrateRecordPatterns();
    }

    private static void demonstrateRecordPatterns() {
        System.out.println("=== Record Patterns (Java 21) ===\n");

        System.out.println("--- Basic Record Pattern in instanceof ---");
        record Point(int x, int y) {}

        Object obj = new Point(10, 20);
        if (obj instanceof Point(int x, int y)) {
            System.out.println("X: " + x + ", Y: " + y);
            System.out.println("Sum: " + (x + y));
        }

        System.out.println("\n--- Record with Inner Records ---");
        record Rectangle(Point topLeft, Point bottomRight) {}

        Object rect = new Rectangle(new Point(0, 10), new Point(10, 0));

        if (rect instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
            System.out.println("Top-left: (" + x1 + ", " + y1 + ")");
            System.out.println("Bottom-right: (" + x2 + ", " + y2 + ")");
            System.out.println("Width: " + (x2 - x1) + ", Height: " + (y1 - y2));
        }

        System.out.println("\n--- Record Patterns in Switch ---");
        Object[] shapes = {
            new Point(5, 5),
            new Rectangle(new Point(0, 5), new Point(5, 0)),
            "not a shape"
        };

        for (Object shape : shapes) {
            String result = switch (shape) {
                case Point(int x, int y) -> "Point at (" + x + ", " + y + ")";
                case Rectangle(Point(int x1, int y1), Point(int x2, int y2)) ->
                    "Rectangle from (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")";
                case null -> "Null shape";
                default -> "Unknown shape: " + shape.getClass().getSimpleName();
            };
            System.out.println(result);
        }

        System.out.println("\n--- Guarded Patterns ---");
        Object[] data = {new Point(3, 4), new Point(1, 1), "text"};

        for (Object d : data) {
            String info = switch (d) {
                case Point(int x, int y) when x > 0 && y > 0 -> "Point in first quadrant: (" + x + ", " + y + ")";
                case Point(int x, int y) -> "Point: (" + x + ", " + y + ")";
                case null -> "Null";
                default -> "Other: " + d.getClass().getSimpleName();
            };
            System.out.println(info);
        }

        System.out.println("\n--- Record Patterns with List ---");
        record Pair(String key, Object value) {}

        List<Pair> pairs = List.of(
            new Pair("name", "Alice"),
            new Pair("age", 30)
        );

        for (var pair : pairs) {
            if (pair instanceof Pair(String key, Object val)) {
                System.out.println(key + " = " + val);
            }
        }
    }
}