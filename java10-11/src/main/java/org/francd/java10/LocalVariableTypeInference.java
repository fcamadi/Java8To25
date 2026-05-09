package org.francd.java10;

import java.util.*;

public class LocalVariableTypeInference {

    public static void main(String[] args) {
        varBasicUsage();
        varWithCollections();
        varWithComplexTypes();
        varLimitations();
        varInLoops();
        varWithLambdas();
    }

    private static void varBasicUsage() {
        System.out.println("=== var (Local Variable Type Inference) ===");

        var number = 42;
        System.out.println("Type: " + number + " (Integer)");

        var text = "Hello Java 10";
        System.out.println("Type: " + text + " (String)");

        var decimal = 3.14;
        System.out.println("Type: " + decimal + " (Double)");

        var list = new ArrayList<String>();
        list.add("Item");
        System.out.println("Type: " + list.getClass().getSimpleName());

        var map = new HashMap<String, Integer>();
        map.put("one", 1);
        System.out.println("Type: " + map.getClass().getSimpleName());

        var result = calculate();
        System.out.println("Method return: " + result);
    }

    private static void varWithCollections() {
        System.out.println("\n=== var with Collections ===");

        var numbers = List.of(1, 2, 3, 4, 5);
        System.out.println("List type inferred: " + numbers.getClass().getSimpleName());

        var stream = numbers.stream();
        System.out.println("Stream: " + stream.filter(n -> n % 2 == 0).reduce(0, Integer::sum));

        var iterator = numbers.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        var entry = Map.entry("key", "value");
        System.out.println("Entry: " + entry);

        var set = Set.of("A", "B", "C");
        System.out.println("Set: " + set);
    }

    private static void varWithComplexTypes() {
        System.out.println("\n=== var with Complex Types ===");

        var person = new Person("John", 30);
        System.out.println("Person: " + person.getName() + ", " + person.getAge());

        var point = new java.awt.Point(10, 20);
        System.out.println("Point: (" + point.x + ", " + point.y + ")");

        var strings = new String[]{"a", "b", "c"};
        System.out.println("Array length: " + strings.length);

        var anonymous = new Object() {
            String name = "Anonymous";
            void show() { System.out.println("Show: " + name); }
        };
        anonymous.show();

        var thread = new Thread(() -> System.out.println("Running in thread"));
        System.out.println("Thread class: " + thread.getClass().getSimpleName());
    }

    private static void varLimitations() {
        System.out.println("\n=== var Limitations ===");

        // var cannot be used with null
        // var nullValue = null; // ERROR

        // var cannot be used for method parameters
        // void method(var x) {} // ERROR

        // var cannot be used for class fields
        // class Example { var field; } // ERROR

        // var requires initializer on same line
        // var x;
        // x = 10; // ERROR

        // var cannot be used in compound declaration
        // var a = 1, b = 2; // ERROR

        // var works with ternary operator
        var condition = true;
        var ternary = condition ? "yes" : "no";
        System.out.println("Ternary result: " + ternary);

        // var with cast
        var casted = (Number) 42;
        System.out.println("Casted: " + casted);
    }

    private static void varInLoops() {
        System.out.println("\n=== var in Loops ===");

        List<String> items = Arrays.asList("Apple", "Banana", "Cherry");

        for (var item : items) {
            System.out.println("Item: " + item.toUpperCase());
        }

        for (var i = 0; i < 3; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        items.forEach(item -> System.out.println("ForEach: " + item));
    }

    private static void varWithLambdas() {
        System.out.println("\n=== var with Lambdas ===");

        // Cannot use var in lambda parameters (Java 10)
        // (var x, var y) -> x + y // ERROR in Java 10, OK in Java 11+

        // var in stream operations
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        var sum = nums.stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("Sum: " + sum);

        var doubled = nums.stream()
            .map(x -> x * 2) // var requires explicit type in lambda
            .toList();
        System.out.println("Doubled: " + doubled);
    }

    private static String calculate() {
        return "Calculation completed";
    }

    static class Person {
        private String name;
        private int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        String getName() { return name; }
        int getAge() { return age; }
    }
}