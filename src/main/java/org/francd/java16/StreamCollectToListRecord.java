package org.francd.java16;

import java.util.*;
import java.util.stream.Stream;

public class StreamCollectToListRecord {

    public static void main(String[] args) {
        toListUnmodifiable();
        recordFeatures();
        streamToArray();
        instanceofPattern();
    }

    private static void toListUnmodifiable() {
        System.out.println("=== Stream.toList() Returns Unmodifiable (Java 16) ===");

        // Java 10-15: toList() returned modifiable list
        // Java 16+: toList() returns unmodifiable list

        List<String> list = Arrays.asList("a", "b", "c").stream().toList();
        System.out.println("List: " + list);

        try {
            list.add("d");
        } catch (UnsupportedOperationException e) {
            System.out.println("Unmodifiable: " + e.getClass().getSimpleName());
        }

        // For mutable list, use toCollection(ArrayList::new)
        List<String> mutable = Arrays.asList("a", "b").stream()
            .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
        mutable.add("c");
        System.out.println("Mutable: " + mutable);

        // toSet() also returns unmodifiable
        Set<Integer> set = Set.of(1, 2, 3).stream().toList().stream().collect(java.util.stream.Collectors.toSet());
    }

    private static void recordFeatures() {
        System.out.println("\n=== Records (Final in Java 16) ===");

        // Records are final by default - cannot extend
        // record ExtendedPerson extends Person {} // ERROR

        // Can implement interfaces
        record PersonRecord(String name, int age) implements Identifiable {
            @Override
            public String getId() {
                return name + "-" + age;
            }
        }

        var person = new PersonRecord("John", 30);
        System.out.println("Person: " + person);
        System.out.println("Id: " + person.getId());

        // Local records
        record LocalPoint(int x, int y) {
            public int quadrant() {
                if (x > 0 && y > 0) return 1;
                if (x < 0 && y > 0) return 2;
                if (x < 0 && y < 0) return 3;
                return 4;
            }
        }

        var p = new LocalPoint(10, -5);
        System.out.println("Quadrant: " + p.quadrant());
    }

    private static void streamToArray() {
        System.out.println("\n=== Stream.toArray() Improvements ===");

        // toArray with IntFunction
        String[] array = Stream.of("a", "b", "c")
            .toArray(String[]::new);
        System.out.println("Array: " + Arrays.toString(array));

        // toArray() without arguments
        Object[] objArray = Stream.of(1, 2, 3)
            .toArray();
    }

    private static void instanceofPattern() {
        System.out.println("\n=== Pattern Matching for instanceof (Final in Java 16) ===");

        Object obj = "Hello Java 16";

        if (obj instanceof String s && s.length() > 5) {
            System.out.println("Long string: " + s.toUpperCase());
        }

        // Works in switch (preview in 16, final in 17)
        String type = switch (obj) {
            case String s -> "String: " + s;
            case Integer i -> "Integer: " + i;
            case null -> "Null value";
            default -> "Unknown";
        };
        System.out.println("Type: " + type);
    }

    interface Identifiable {
        String getId();
    }
}