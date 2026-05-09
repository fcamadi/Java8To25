package org.francd.java9.collections;

import java.util.*;

public class ImmutableCollections {

    public static void main(String[] args) {
        listFactories();
        setFactories();
        mapFactories();
        immutableVsUnmodifiable();
    }

    private static void listFactories() {
        System.out.println("=== List Factories (Java 9) ===");

        List<String> list = List.of("Apple", "Banana", "Cherry");
        System.out.println("List.of(): " + list);

        List<String> single = List.of("Only one");
        System.out.println("Single element: " + single);

        List<String> empty = List.of();
        System.out.println("Empty list: " + empty);

        System.out.println("\nUnmodifiable:");
        try {
            list.add("Durian");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify: " + e.getClass().getSimpleName());
        }

        System.out.println("Contains 'Apple': " + list.contains("Apple"));
        System.out.println("Index of 'Banana': " + list.indexOf("Banana"));
    }

    private static void setFactories() {
        System.out.println("\n=== Set Factories (Java 9) ===");

        Set<String> set = Set.of("Red", "Green", "Blue");
        System.out.println("Set.of(): " + set);

        Set<String> single = Set.of("One");
        System.out.println("Single: " + single);

        Set<String> empty = Set.of();
        System.out.println("Empty: " + empty);

        System.out.println("\nNo duplicates allowed:");
        try {
            Set.of("A", "A", "B");
        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate rejected: " + e.getMessage());
        }

        System.out.println("Contains 'Green': " + set.contains("Green"));
    }

    private static void mapFactories() {
        System.out.println("\n=== Map Factories (Java 9) ===");

        Map<String, Integer> map = Map.of(
            "One", 1,
            "Two", 2,
            "Three", 3
        );
        System.out.println("Map.of(): " + map);

        Map<String, Integer> single = Map.of("Key", 100);
        System.out.println("Single entry: " + single);

        Map<String, Integer> empty = Map.of();
        System.out.println("Empty: " + empty);

        Map<String, Integer> entryMap = Map.ofEntries(
            Map.entry("A", 1),
            Map.entry("B", 2),
            Map.entry("C", 3)
        );
        System.out.println("Map.ofEntries(): " + entryMap);

        try {
            map.put("Four", 4);
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify Map.of()");
        }
    }

    private static void immutableVsUnmodifiable() {
        System.out.println("\n=== Immutable vs Unmodifiable ===");

        List<String> original = new ArrayList<>();
        original.add("Original");

        List<String> unmodifiable = Collections.unmodifiableList(original);
        System.out.println("Unmodifiable: " + unmodifiable);

        original.add("Changed!");
        System.out.println("After change in original list: " + unmodifiable);

        List<String> trulyImmutable = List.of("Immutable");
        // trulyImmutable.add("whatever"); not possible to modify it
        System.out.println("Immutable List.of(): " + trulyImmutable);
    }
}