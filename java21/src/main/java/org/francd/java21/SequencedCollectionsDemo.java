package org.francd.java21;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;
import java.util.Set;

/**
 * Demonstrates Sequenced Collections in Java 21.
 *
 * Sequenced collections provide a defined encounter order and methods to
 * access, add, and iterate at both ends. They unify the Collection, Set,
 * and Map hierarchies with a consistent API.
 *
 * Key interfaces:
 * - SequencedCollection: Extends Collection with first/last access
 * - SequencedSet: Extends Set with sequenced operations
 * - SequencedMap: Extends Map with key ordering
 *
 * Key methods:
 * - getFirst() / getLast() - access elements at ends
 * - addFirst() / addLast() - add elements at ends
 * - reversed() - get reversed view
 *
 * Replacing older patterns:
 * - List + Deque operations -> SequencedCollection
 * - LinkedHashSet -> SequencedSet
 * - LinkedHashMap -> SequencedMap
 */
public class SequencedCollectionsDemo {

    public static void main(String[] args) {
        demonstrateSequencedCollections();
    }

    private static void demonstrateSequencedCollections() {
        System.out.println("=== Sequenced Collections (Java 21) ===\n");

        System.out.println("--- SequencedCollection ---");
        SequencedCollection<String> list = new ArrayList<>(List.of("A", "B", "C"));
        list.addFirst("Start");
        list.addLast("End");

        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        System.out.println("List: " + list);
        System.out.println("Reversed: " + list.reversed());

        list.removeFirst();
        list.removeLast();
        System.out.println("After removing first/last: " + list);

        System.out.println("\n--- SequencedSet ---");
        SequencedSet<Integer> set = new LinkedHashSet<>(Set.of(3, 1, 2));
        set.addFirst(0);
        set.addLast(4);

        System.out.println("Set: " + set);
        System.out.println("First: " + set.getFirst());
        System.out.println("Last: " + set.getLast());
        System.out.println("Reversed: " + set.reversed());

        System.out.println("\n--- SequencedMap ---");
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        map.putFirst("first", 1);
        map.putLast("last", 3);
        map.put("middle", 2);

        System.out.println("Map: " + map);
        System.out.println("First key: " + map.sequencedKeySet().getFirst());
        System.out.println("Last key: " + map.sequencedKeySet().getLast());
        System.out.println("Reversed: " + map.reversed());

        map.putFirst("zero", 0);
        System.out.println("After putFirst: " + map);

        System.out.println("\n--- Use cases ---");
        var queue = new ArrayList<String>();
        queue.addLast("task1");
        queue.addLast("task2");
        queue.addLast("task3");

        while (!queue.isEmpty()) {
            System.out.println("Processing: " + queue.removeFirst());
        }
    }
}