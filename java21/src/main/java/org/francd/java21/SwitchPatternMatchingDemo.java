package org.francd.java21;

import java.util.List;

/**
 * Demonstrates Pattern Matching in Switch expressions (Java 21).
 *
 * This feature allows using patterns in case labels of switch expressions
 * and statements, enabling type-based and conditional matching.
 *
 * Topics covered:
 * - Type patterns in switch
 * - Null handling in switch
 * - Guarded patterns (with conditions)
 * - Record patterns in switch
 *
 * Note: This is different from "RecordPatternsDemo" which focuses specifically
 * on using patterns with record types. This class shows switch patterns
 * in general with various types.
 */
public class SwitchPatternMatchingDemo {

    public static void main(String[] args) {
        demonstrateSwitchPatterns();
    }

    private static void demonstrateSwitchPatterns() {
        System.out.println("=== Pattern Matching in Switch (Java 21) ===\n");

        System.out.println("--- Basic Type Patterns ---");
        Object[] values = {"Hello", 42, 3.14, List.of(1, 2), null, new int[]{1, 2}};

        for (Object value : values) {
            String result = typePattern(value);
            System.out.println(value + " -> " + result);
        }

        System.out.println("\n--- Guarded Patterns ---");
        Object[] numbers = {5, -3, 0, 100};

        for (Object n : numbers) {
            String result = guardedPattern(n);
            System.out.println(n + " -> " + result);
        }

        System.out.println("\n--- Record Patterns in Switch ---");
        Object[] shapes = {
            new Point(5, 5),
            new Rectangle(new Point(0, 5), new Point(5, 0)),
            "not a shape"
        };

        for (Object shape : shapes) {
            String result = recordPatternInSwitch(shape);
            System.out.println(result);
        }

        System.out.println("\n--- Enhanced Switch Expression ---");
        int[] numbers2 = {1, 2, 3, 4, 5};
        for (int n : numbers2) {
            System.out.println(n + " -> " + describeNumber(n));
        }
    }

    private static String typePattern(Object value) {
        return switch (value) {
            case String s -> "String: " + s.length() + " chars";
            case Integer i -> "Integer: " + i;
            case Double d -> "Double: " + d;
            case java.util.List l -> "List with " + l.size() + " elements";
            case int[] arr -> "Array with " + arr.length + " elements";
            case null -> "Null value";
            default -> "Unknown: " + value.getClass().getSimpleName();
        };
    }

    private static String guardedPattern(Object value) {
        return switch (value) {
            case Integer i when i > 0 -> "Positive: " + i;
            case Integer i when i < 0 -> "Negative: " + i;
            case Integer i -> "Zero: " + i;
            default -> "Not an integer";
        };
    }

    private static String recordPatternInSwitch(Object shape) {
        return switch (shape) {
            case Point(int x, int y) -> "Point at (" + x + ", " + y + ")";
            case Rectangle(Point(int x1, int y1), Point(int x2, int y2)) ->
                "Rectangle from (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")";
            case null -> "Null shape";
            default -> "Unknown shape: " + shape.getClass().getSimpleName();
        };
    }

    private static String describeNumber(int n) {
        return switch (n) {
            case 1 -> "One";
            case 2 -> "Two";
            case 3 -> "Three";
            default -> "Other (" + n + ")";
        };
    }

    record Point(int x, int y) {}
    record Rectangle(Point topLeft, Point bottomRight) {}
}