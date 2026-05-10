package org.francd.java11;

import java.util.*;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionLambdaImprovements {

    public static void main(String[] args) {
        lambdaParameterInference();
        predicateNot();
        toArrayGenerator();
    }

    private static void lambdaParameterInference() {
        System.out.println("=== Lambda Parameter Inference (Java 11) ===");

        // Java 10 - explicit type required for multiple parameters
        // BiFunction<String, String, String> concat = (String a, String b) -> a + b;

        // Java 11 - var can be used in lambda parameters
        DoubleBinaryOperator concat = (var a, var b) -> a + b;
        System.out.println("Concat: " + concat.applyAsDouble(55, 66));

        // Mixed - var with some explicit types <- not correct
        //var processor = (var name, var age) -> name + " is " + age + " years old";
        //System.out.println(processor.("John", 30));

        // var in lambda with annotations (NEW in Java 11)
        Predicate<String> filter = (String s) -> s != null && s.length() > 3;
        System.out.println("Filter 'null': " + filter.test(null));
        System.out.println("Filter 'Java': " + filter.test("Java"));
        System.out.println("Filter 'Hi': " + filter.test("Hi"));

        // Multiple parameters with var - need explicit type
        DoubleBinaryOperator mathOps = (a, b) -> {
            System.out.println("Processing: " + a + " and " + b);
            return a * b;
        };
        System.out.println("Multiply: " + mathOps.applyAsDouble(5, 3));
    }

    private static void predicateNot() {
        System.out.println("\n=== Predicate.not() (Java 11) ===");

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "");

        // Before Java 11 - use negate()
        List<String> shortWords = words.stream()
            .filter(s -> !s.isEmpty())  // negate
            .filter(s -> s.length() < 6)
                .collect(Collectors.toList());
        System.out.println("Short words (old): " + shortWords);

        // Java 11 - use Predicate.not()
        List<String> notEmpty = words.stream()
            .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        System.out.println("Not empty: " + notEmpty);

        // with method reference
        List<String> nonNulls = words.stream()
            .filter(Predicate.not(Objects::isNull))
                .collect(Collectors.toList());
        System.out.println("Not null: " + nonNulls);

        // Combined with other predicates
        List<String> result = words.stream()
            .filter(Predicate.not(String::isBlank))
            .filter(Predicate.not(s -> s.length() > 5))
                .collect(Collectors.toList());
        System.out.println("Filtered: " + result);

        // Predicate.not() is useful for negating method references
        java.util.function.ToIntFunction<String> getLength = String::length;
        Predicate<String> hasLongName = s -> getLength.applyAsInt(s) > 5;
        Predicate<String> hasShortName = Predicate.not(hasLongName);
    }

    private static void toArrayGenerator() {
        System.out.println("\n=== Collection.toArray(Generator) ===");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Before Java 11
        String[] oldArray = names.toArray(new String[0]);

        // Java 11 - toArray with generator
        String[] newArray = names.toArray(String[]::new);
        System.out.println("Array length: " + newArray.length);
        System.out.println("Array: " + Arrays.toString(newArray));

        // Works with any collection
        Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] numberArray = numbers.toArray(Integer[]::new);
        System.out.println("Number array: " + Arrays.toString(numberArray));

        // Map.toArray
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        String[] keys = map.keySet().toArray(String[]::new);
        System.out.println("Map keys: " + Arrays.toString(keys));

        // Useful for stream operations
        String[] fromStream = Arrays.asList("x", "y", "z").stream()
            .toArray(String[]::new);
        System.out.println("From stream: " + Arrays.toString(fromStream));
    }
}