package org.francd.java8.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class OptionalDemo {

    public static void main(String[] args) {
        creatingOptionals();
        conditionalExecution();
        transformations();
        errorHandling();
        practicalExamples();
    }

    private static void creatingOptionals() {
        System.out.println("=== Creating Optionals ===");

        Optional<String> empty = Optional.empty();
        System.out.println("Empty: " + empty.isPresent());

        Optional<String> of = Optional.of("Hello");
        System.out.println("Of: " + of.get());

        String nada = null;
        Optional<String> ofNullable = Optional.ofNullable(nada);
        System.out.println("OfNullable (null): " + ofNullable.isPresent());

        Optional<String> ofNullable2 = Optional.of("World");
        System.out.println("OfNullable (value): " + ofNullable2.isPresent());
    }

    private static void conditionalExecution() {
        System.out.println("\n=== Conditional Execution ===");

        Optional<String> opt = Optional.of("Java 8");

        opt.ifPresent(s -> System.out.println("Present: " + s));

        opt.ifPresentOrElse(
            s -> System.out.println("Has value: " + s),
            () -> System.out.println("No value")
        );

        Optional<String> emptyOpt = Optional.empty();
        emptyOpt.ifPresentOrElse(
            s -> System.out.println("Has: " + s),
            () -> System.out.println("Empty - no action taken")
        );
    }

    private static void transformations() {
        System.out.println("\n=== Transformations ===");

        Optional<String> opt = Optional.of("hello");

        Optional<String> mapped = opt.map(String::toUpperCase);
        System.out.println("Map: " + mapped.orElse("none"));

        Optional<String> flatMapped = opt.flatMap(s -> Optional.of(s + " world"));
        System.out.println("FlatMap: " + flatMapped.orElse("none"));

        Optional<String> filtered = opt.filter(s -> s.length() > 3);
        System.out.println("Filter (pass): " + filtered.orElse("none"));

        Optional<String> filtered2 = opt.filter(s -> s.length() > 10);
        System.out.println("Filter (fail): " + filtered2.orElse("none"));

        Optional<String> transformed = opt
            .map(String::toUpperCase)
            .filter(s -> s.startsWith("H"))
            .map(s -> s + " - transformed");
        System.out.println("Chain: " + transformed.orElse("none"));
    }

    private static void errorHandling() {
        System.out.println("\n=== Error Handling ===");

        Optional<String> opt = Optional.of("value");

        String result1 = opt.orElse("default");
        System.out.println("OrElse: " + result1);

        Optional<String> empty = Optional.empty();
        String result2 = empty.orElse("default");
        System.out.println("OrElse (empty): " + result2);

        String result3 = empty.orElseGet(() -> "computed default");
        System.out.println("OrElseGet: " + result3);

        try {
            String result4 = empty.orElseThrow(() ->
                new IllegalStateException("Custom exception"));
        } catch (IllegalStateException e) {
            System.out.println("OrElseThrow caught: " + e.getMessage());
        }

        Optional<String> withFallback = opt.or(() -> Optional.of("fallback"));
        System.out.println("Or: " + withFallback.get());
    }

    private static void practicalExamples() {
        System.out.println("\n=== Practical Examples ===");

        System.out.println("Default: " + getUserName(null));
        System.out.println("With value: " + getUserName(new User("John")));

        String result = findOrder()
            .flatMap(Order::getCustomer)
            .flatMap(Customer::getAddress)
            .map(Address::getCity)
            .orElse("Unknown");
        System.out.println("Chained: " + result);

        String nullName = null;
        String safe = Optional.ofNullable(nullName).orElse("Anonymous");
        System.out.println("Safe null: " + safe);

        List<String> names = Arrays.asList("Alice", null, "Bob", null, "Charlie");
        List<String> cleaned = names.stream()
            .flatMap(n -> Optional.ofNullable(n).stream())
            .toList();
        System.out.println("Cleaned: " + cleaned);
    }

    private static String getUserName(User user) {
        return Optional.ofNullable(user)
            .map(User::getName)
            .orElse("Guest");
    }

    private static Optional<Order> findOrder() {
        return Optional.of(new Order());
    }

    static class User {
        private final String name;
        User(String name) { this.name = name; }
        String getName() { return name; }
    }

    static class Order {
        Optional<Customer> getCustomer() {
            return Optional.of(new Customer());
        }
    }

    static class Customer {
        Optional<Address> getAddress() {
            return Optional.of(new Address());
        }
    }

    static class Address {
        String getCity() { return "Gijón"; }
    }
}