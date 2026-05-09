package org.francd.java10;

import java.util.*;

public class CollectionImprovements {

    public static void main(String[] args) {
        listImprovements();
        copyOfMethods();
        optionalImprovements();
    }

    private static void listImprovements() {
        System.out.println("=== List.copyOf() and Set.copyOf() ===");

        List<String> original = Arrays.asList("A", "B", "C");
        List<String> copy = List.copyOf(original);

        System.out.println("Original: " + original);
        System.out.println("Copy: " + copy);

        // Copy is immutable
        try {
            copy.add("D");
        } catch (UnsupportedOperationException e) {
            System.out.println("Copy is immutable: " + e.getClass().getSimpleName());
        }

        // Null handling
        List<String> withNulls = Arrays.asList("A", null, "B");
        try {
            List<String> nullCopy = List.copyOf(withNulls);
        } catch (NullPointerException e) {
            System.out.println("Null not allowed: " + e.getClass().getSimpleName());
        }

        // Set.copyOf
        Set<Integer> setOriginal = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> setCopy = Set.copyOf(setOriginal);
        System.out.println("Set copy: " + setCopy);
    }

    private static void copyOfMethods() {
        System.out.println("\n=== CopyOf Methods ===");

        // List.copyOf
        List<String> source = Arrays.asList("X", "Y", "Z");
        List<String> immutableList = List.copyOf(source);
        System.out.println("List.copyOf: " + immutableList);

        // Set.copyOf
        Set<String> sourceSet = new HashSet<>(Arrays.asList("One", "Two"));
        Set<String> immutableSet = Set.copyOf(sourceSet);
        System.out.println("Set.copyOf: " + immutableSet);

        // Map.copyOf
        Map<String, Integer> sourceMap = new HashMap<>();
        sourceMap.put("A", 1);
        sourceMap.put("B", 2);
        Map<String, Integer> immutableMap = Map.copyOf(sourceMap);
        System.out.println("Map.copyOf: " + immutableMap);

        // CopyOf preserves content but creates new immutable collection
        List<String> original = new ArrayList<>(Arrays.asList("Old"));
        List<String> copy = List.copyOf(original);
        original.add("New");
        System.out.println("Original after add: " + original);
        System.out.println("Copy unchanged: " + copy);
    }

    private static void optionalImprovements() {
        System.out.println("\n=== Optional.orElseThrow() ===");

        Optional<String> empty = Optional.empty();

        // orElseThrow (was added earlier, but prominent in Java 10)
        try {
            String result = empty.orElseThrow();
        } catch (NoSuchElementException e) {
            System.out.println("Empty throws NoSuchElementException");
        }

        // With custom exception
        Optional<Integer> opt = Optional.of(42);
        try {
            Integer val = opt.orElseThrow(() -> new IllegalArgumentException("Custom"));
            System.out.println("Value: " + val);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Optional with stream
        List<Optional<String>> optionals = Arrays.asList(
            Optional.of("A"),
            Optional.empty(),
            Optional.of("B")
        );

        List<String> collected = optionals.stream()
            .flatMap(Optional::stream)
            .toList();
        System.out.println("Flattened optionals: " + collected);

        // ifPresentOrElse
        Optional<String> present = Optional.of("Value");
        present.ifPresentOrElse(
            v -> System.out.println("Present: " + v),
            () -> System.out.println("Empty")
        );

        Optional<String> absent = Optional.empty();
        absent.ifPresentOrElse(
            v -> System.out.println("Present: " + v),
            () -> System.out.println("Empty - no value")
        );
    }
}