# Java 8 Features

## Overview
Java 8 (released March 2014) introduced major changes including lambda expressions, streams, and a new Date/Time API.

---

## Lambda Expressions

### Basic Syntax
```java
// No parameters
Runnable noParams = () -> System.out.println("Hello!");

// One parameter
Consumer<String> oneParam = s -> System.out.println("Consumed: " + s);

// Two parameters
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

// With type inference
BinaryOperator<Integer> multiply = (Integer a, Integer b) -> a * b;

// Multi-statement body
Function<String, Integer> length = s -> {
    int len = s.length();
    return len * 2;
};
```

### Functional Interfaces
```java
// Predicate - test a condition
Predicate<String> isEmpty = s -> s.isEmpty();

// Function - transform input to output
Function<String, String> upperCase = String::toUpperCase;

// Supplier - provide a value
Supplier<List<String>> listSupplier = ArrayList::new;

// Consumer - consume a value
Consumer<String> printer = System.out::println;

// UnaryOperator - transform one value
UnaryOperator<Integer> square = n -> n * n;

// BinaryOperator - combine two values
BinaryOperator<Integer> max = Integer::max;
```

### Method References
```java
// Static method
Function<String, Integer> parse = Integer::parseInt;

// Instance method of object
Function<String, String> toUpper = String::toUpperCase;

// Instance method of parameter
BiFunction<String, String, String> concat = String::concat;

// Constructor
Supplier<String> newString = String::new;
```

---

## Stream API

### Creating Streams
```java
// From array
Stream<String> fromArray = Stream.of("a", "b", "c");

// From collection
List<String> list = Arrays.asList("one", "two");
Stream<String> fromList = list.stream();

// From iterate (unbounded)
Stream<Integer> iterate = Stream.iterate(1, n -> n + 2).limit(5);

// From generate (unbounded)
Stream<Double> random = Stream.generate(Math::random).limit(3);

// Primitive streams
IntStream intRange = IntStream.range(1, 5);
```

### Intermediate Operations
```java
// filter - keep elements matching condition
stream.filter(s -> s.length() > 4)

// map - transform each element
stream.map(String::toUpperCase)

// flatMap - flatten nested structures
stream.flatMap(s -> Stream.of(s.split("")))

// distinct - remove duplicates
stream.distinct()

// skip - skip first N elements
stream.skip(5)

// limit - take first N elements
stream.limit(10)

// sorted - sort elements
stream.sorted()

// peek - inspect without consuming
stream.peek(n -> System.out.println(n))
```

### Terminal Operations
```java
// forEach - consume each element
stream.forEach(System.out::println);

// count - count elements
long count = stream.count();

// anyMatch, allMatch, noneMatch
boolean hasLong = stream.anyMatch(s -> s.length() > 5);
boolean allShort = stream.allMatch(s -> s.length() < 10);
boolean noneStartsWithX = stream.noneMatch(s -> s.startsWith("X"));

// findFirst, findAny
Optional<String> first = stream.findFirst();

// reduce - aggregate elements
Optional<Integer> sum = stream.reduce(Integer::sum);
Integer sumWithIdentity = stream.reduce(0, Integer::sum);

// collect - collect to collection
List<String> list = stream.collect(Collectors.toList());
String joined = stream.collect(Collectors.joining(", "));
Map<String, List<T>> grouped = stream.collect(Collectors.groupingBy(T::getKey));
```

### Parallel Streams
```java
// Convert to parallel
Stream<Integer> parallel = list.parallelStream();

// Use with caution - not thread-safe for shared state
long sum = largeList.parallelStream()
    .filter(n -> n % 2 == 0)
    .mapToLong(n -> n)
    .sum();
```

---

## Optional Class

### Creating Optionals
```java
Optional<String> empty = Optional.empty();
Optional<String> of = Optional.of("Hello");
Optional<String> ofNullable = Optional.ofNullable(null);
```

### Conditional Execution
```java
opt.ifPresent(s -> System.out.println(s));

opt.ifPresentOrElse(
    s -> System.out.println("Value: " + s),
    () -> System.out.println("No value")
);
```

### Transformations
```java
// map - transform the value
Optional<String> upper = opt.map(String::toUpperCase);

// flatMap - chain Optionals
Optional<String> chained = opt.flatMap(s -> Optional.of(s + " world"));

// filter - keep if condition matches
Optional<String> filtered = opt.filter(s -> s.length() > 3);
```

### Error Handling
```java
String result = opt.orElse("default");
String computed = opt.orElseGet(() -> expensiveCalculation());
String thrown = opt.orElseThrow(() -> new RuntimeException("Required"));
Optional<String> fallback = opt.or(() -> Optional.of("fallback"));
```

---

## Date/Time API (java.time)

### LocalDate, LocalTime, LocalDateTime
```java
LocalDate today = LocalDate.now();
LocalDate specific = LocalDate.of(2024, Month.JANUARY, 15);

LocalTime now = LocalTime.now();
LocalTime time = LocalTime.of(14, 30, 45);

LocalDateTime dateTime = LocalDateTime.now();
LocalDateTime fromParts = LocalDateTime.of(2024, 6, 20, 10, 30);

// Adding/subtracting
LocalDate plus = today.plusDays(10);
LocalDateTime later = dateTime.plusHours(5);
LocalDate minus = today.minusWeeks(2);
```

### Period and Duration
```java
// Period - date-based
Period period = Period.between(startDate, endDate);
LocalDate withPeriod = start.plus(Period.of(2, 6, 15));

// Duration - time-based
Duration duration = Duration.ofHours(2);
Duration meeting = Duration.between(startTime, endTime);
```

### Formatting
```java
LocalDateTime dt = LocalDateTime.of(2024, 3, 15, 14, 30, 45);

DateTimeFormatter f1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
DateTimeFormatter f2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
DateTimeFormatter f3 = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");

String formatted = dt.format(f1);
LocalDate parsed = LocalDate.parse("2024-05-20", DateTimeFormatter.ISO_LOCAL_DATE);
```

### Timezones
```java
ZonedDateTime now = ZonedDateTime.now();
ZonedDateTime madrid = ZonedDateTime.now(ZoneId.of("Europe/Madrid"));

// Convert between zones
ZonedDateTime tokyo = madrid.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));

// Instant
Instant instant = Instant.now();
```

### Temporal Adjusters
```java
LocalDate nextMonday = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
LocalDate firstFriday = date.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
```

---

## Default Methods in Interfaces

```java
interface Vehicle {
    void start();
    void stop();

    // Default method
    default void honk() {
        System.out.println("Beep!");
    }

    // Static method
    static String info() {
        return "Vehicle v1.0";
    }
}

class Car implements Vehicle {
    @Override
    public void start() { System.out.println("Starting..."); }
    @Override
    public void stop() { System.out.println("Stopping..."); }
}
```

### Multiple Inheritance Resolution
```java
interface A { default void test() { System.out.println("A"); } }
interface B { default void test() { System.out.println("B"); } }

class C implements A, B {
    @Override
    public void test() {
        A.super.test(); // Call A's implementation
    }
}
```

---

## Built-in Functional Interfaces (java.util.function)

| Interface | Method | Description |
|-----------|--------|-------------|
| `Predicate<T>` | `test(T)` | Returns boolean |
| `Function<T,R>` | `apply(T)` | Transforms T to R |
| `Supplier<T>` | `get()` | Returns T |
| `Consumer<T>` | `accept(T)` | Consumes T |
| `UnaryOperator<T>` | `apply(T)` | T → T |
| `BinaryOperator<T>` | `apply(T,T)` | (T,T) → T |
| `BiPredicate<T,U>` | `test(T,U)` | Returns boolean |
| `BiFunction<T,U,R>` | `apply(T,U)` | (T,U) → R |
| `BiConsumer<T,U>` | `accept(T,U)` | Consumes T and U |

### Primitive Variants
- `IntPredicate`, `LongPredicate`, `DoublePredicate`
- `IntFunction<R>`, `LongFunction<R>`, `DoubleFunction<R>`
- `ToIntFunction<T>`, `ToLongFunction<T>`, `ToDoubleFunction<T>`
- `ObjIntConsumer<T>`, `ObjLongConsumer<T>`, `ObjDoubleConsumer<T>`

---

## Collectors (java.util.stream.Collectors)

### Basic
```java
List<String> list = stream.collect(Collectors.toList());
Set<String> set = stream.collect(Collectors.toSet());
String joined = stream.collect(Collectors.joining(", "));
Map<String, Integer> map = stream.collect(Collectors.toMap(s -> s, String::length));
```

### Grouping and Partitioning
```java
Map<String, List<T>> grouped = stream.collect(Collectors.groupingBy(T::getCategory));
Map<String, Long> countByCategory = stream.collect(Collectors.groupingBy(T::getCategory, Collectors.counting()));
Map<Boolean, List<T>> partitioned = stream.collect(Collectors.partitioningBy(p -> p.getPrice() > 500));
```

### Aggregation
```java
Long count = stream.collect(Collectors.counting());
Optional<T> min = stream.collect(Collectors.minBy(Comparator.naturalOrder()));
Double average = stream.collect(Collectors.averagingDouble(T::getValue));
IntSummaryStatistics stats = stream.collect(Collectors.summarizingInt(T::getValue));
```

### Downstream
```java
Set<String> categories = stream.collect(Collectors.mapping(T::getCategory, Collectors.toSet()));
double withTax = stream.collect(Collectors.collectingAndThen(Collectors.summingDouble(T::getPrice), total -> total * 1.21));
```