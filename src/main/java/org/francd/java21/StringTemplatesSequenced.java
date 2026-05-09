package org.francd.java21;

import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;

public class StringTemplatesSequenced {

    public static void main(String[] args) {
        sequencedCollections();
        virtualThreadsFinal();
        stringTemplates();
        patternMatching();
    }

    private static void sequencedCollections() {
        System.out.println("=== Sequenced Collections (Java 21) ===");

        // New interfaces
        SequencedCollection<String> list = new java.util.ArrayList<>(java.util.List.of("A", "B", "C"));
        list.addFirst("Start");
        list.addLast("End");

        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        System.out.println("Reversed: " + list.reversed());

        // SequencedSet and SequencedMap
        SequencedSet<Integer> set = new java.util.LinkedHashSet<>(java.util.Set.of(1, 2, 3));
        set.addFirst(0);
        System.out.println("Set first: " + set.getFirst());

        SequencedMap<String, Integer> map = new java.util.LinkedHashMap<>();
        map.putFirst("A", 1);
        map.putLast("C", 3);
        map.put("B", 2);
        System.out.println("Map: " + map);
    }

    private static void virtualThreadsFinal() {
        System.out.println("\n=== Virtual Threads (Final in Java 21) ===");

        // Virtual threads are production-ready
        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 5; i++) {
                final int id = i;
                executor.submit(() -> {
                    System.out.println("Task " + id + " in virtual thread");
                });
            }
        }

        System.out.println("Virtual threads are now fully supported");
    }

    private static void stringTemplates() {
        System.out.println("\n=== String Templates (Preview in 21) ===");

        // String templates (preview)
        String name = "World";
        int age = 30;

        // Using .formatted() (Java 12+)
        String formatted = "Hello %s, age %d".formatted(name, age);
        System.out.println("Formatted: " + formatted);

        // Rich String Literals (preview in 21, delayed to 25)
        // StringTemplate st = STR """Hello \{name}""";
    }

    private static void patternMatching() {
        System.out.println("\n=== Pattern Matching Enhancements (Java 21) ===");

        // Unnamed patterns
        Object[] values = {1, "text", null, 3.14};
        for (Object v : values) {
            String info = switch (v) {
                case null -> "Null value";
                case Integer i -> "Integer: " + i;
                case String s -> "String: " + s;
                default -> "Other: " + v.getClass().getSimpleName();
            };
            System.out.println(info);
        }

        // Record patterns
        record Point21(int x, int y) {}
        var point = new Point21(5, 10);
        if (point instanceof Point21(int x, int y)) {
            System.out.println("X: " + x + ", Y: " + y);
        }
    }
}