# Java 12 Features

## Overview
Java 12 (released March 2019) introduced Switch Expressions (preview), the Teeing collector, and several other improvements.

---

## Switch Expressions (Preview)

### Arrow Syntax
```java
// Traditional switch
switch (day) {
    case 1: dayName = "Monday"; break;
    case 2: dayName = "Tuesday"; break;
    default: dayName = "Unknown";
}

// New arrow syntax - no fall-through!
String dayName = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    default -> "Unknown";
};
```

### Multiple Values
```java
char grade = 'B';
String description = switch (grade) {
    case 'A' -> "Excellent";
    case 'B', 'C' -> "Good";
    case 'D' -> "Pass";
    case 'F' -> "Fail";
    default -> "Invalid";
};
```

### Block Statements with yield
```java
int result = switch (input) {
    case "one" -> {
        yield 1;
    }
    case "two" -> {
        yield 2;
    }
    default -> {
        yield -1;
    }
};
```

### Benefits
- No fall-through (fewer bugs)
- More concise
- Can be used as expression or statement
- Multiple values per case

---

## Teeing Collector

### Basic Usage
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Collect sum and count together
String result = numbers.stream()
    .collect(Collectors.teeing(
        Collectors.summingInt(Integer::intValue),
        Collectors.counting(),
        (sum, count) -> "Sum: " + sum + ", Count: " + count
    ));
// Output: Sum: 15, Count: 5
```

### Min and Max Together
```java
List<String> words = Arrays.asList("apple", "banana", "cherry");

String minMax = words.stream()
    .collect(Collectors.teeing(
        Collectors.minBy(Comparator.naturalOrder()),
        Collectors.maxBy(Comparator.naturalOrder()),
        (min, max) -> "Min: " + min.orElse("") + ", Max: " + max.orElse("")
    ));
```

### Statistics Example
```java
List<Double> prices = Arrays.asList(19.99, 29.99, 9.99, 49.99);

String stats = prices.stream()
    .collect(Collectors.teeing(
        Collectors.summingDouble(Double::doubleValue),
        Collectors.averagingDouble(Double::doubleValue),
        (total, avg) -> "Total: $" + total + ", Avg: $" + avg
    ));
```

### Grouping with Teeing
```java
Map<String, String> stats = products.stream()
    .collect(Collectors.groupingBy(
        Product::getCategory,
        Collectors.teeing(
            Collectors.summingDouble(Product::getPrice),
            Collectors.counting(),
            (sum, count) -> "Sum: " + sum + ", Count: " + count
        )
    ));
```

---

## String Improvements

### String.formatted()
```java
String template = "Hello, {0}!";
String result = template.formatted("World");

// Multiple arguments
String msg = "Name: {0}, Age: {1}".formatted("John", 30);

// With positional arguments
String recipe = "Step {0}: {1}\nStep {2}: {3}";
recipe.formatted(1, "Mix", 2, "Bake");
```

---

## Number Formatting (Compact)

### Compact Number Format
```java
NumberFormat compact = NumberFormat.getCompactNumberInstance(
    Locale.US,
    NumberFormat.Style.SHORT
);

compact.format(1000);   // "1K"
compact.format(1000000); // "1M"
compact.format(1000000000); // "1B"

// Long style
NumberFormat longFmt = NumberFormat.getCompactNumberInstance(
    Locale.US,
    NumberFormat.Style.LONG
);

longFmt.format(1000);   // "1 thousand"
longFmt.format(1000000); // "1 million"
```

### Usage in Applications
```java
Map<String, Long> stats = Map.of(
    "views", 1500000L,
    "likes", 45000L
);

NumberFormat compact = NumberFormat.getCompactNumberInstance();
stats.forEach((k, v) -> System.out.println(k + ": " + compact.format(v)));
```

---

## File mismatch()

### Basic Usage
```java
Path file1 = Files.createTempFile("f1", ".txt");
Path file2 = Files.createTempFile("f2", ".txt");

Files.writeString(file1, "Hello World");
Files.writeString(file2, "Hello Java");

long pos = Files.mismatch(file1, file2);  // Returns position of first mismatch
// Output: 6 (first different character)

Files.writeString(file1, "Same");
Files.writeString(file2, "Same");
long same = Files.mismatch(file1, file2);  // Returns -1 if identical
// Output: -1
```

---

## Other Java 12 Features

### Shenandoah GC (Experimental)
```bash
# Enable Shenandoah GC
-XX:+UseShenandoahGC
```

### Microbenchmark Suite
- JMH (Java Microbenchmark Harness) integrated
- Standardized microbenchmarks

### String Constant Pool Constants
```java
// New constants for String internals
String.CASE_INSENSITIVE_ORDER
```

---

## Switch Expression Patterns

### With Enum
```java
Month month = Month.APRIL;
String quarter = switch (month) {
    case JANUARY, FEBRUARY, MARCH -> "Q1";
    case APRIL, MAY, JUNE -> "Q2";
    case JULY, AUGUST, SEPTEMBER -> "Q3";
    default -> "Q4";
};
```

### With Null
```java
String result = switch (input) {
    case null -> "Null input";
    case "a" -> "Option A";
    default -> "Other";
};
```

---

## Code Examples

### Before Java 12
```java
// Traditional switch - verbose, error-prone
int days;
switch (month) {
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
        days = 31;
        break;
    case 4:
    case 6:
    case 9:
    case 11:
        days = 30;
        break;
    case 2:
        days = 28;
        break;
    default:
        days = 0;
}
```

### Java 12+
```java
// Switch expression - concise, safe
int days = switch (month) {
    case 1, 3, 5, 7, 8, 10, 12 -> 31;
    case 4, 6, 9, 11 -> 30;
    case 2 -> 28;
    default -> 0;
};
```

---

## Summary

| Feature | Description |
|---------|-------------|
| **Switch Expressions** | Arrow syntax, no fall-through, can return value |
| **Teeing Collector** | Merge two collectors into one result |
| **String.formatted()** | New string formatting method |
| **Compact Number Format** | Short/long number formatting (1K, 1M) |
| **Files.mismatch()** | Find first differing byte between files |

---

## Compatibility Notes

- Switch Expressions were **preview** in Java 12
- Became **standard** in Java 14
- Teeing collector is **standard** from Java 12
- Compact number formatting is **standard** from Java 12

---

## IDE Support

- IntelliJ IDEA: Full support from 2019.1+
- Eclipse: Full support from 2019-03+
- VS Code: Via Language Support for Java