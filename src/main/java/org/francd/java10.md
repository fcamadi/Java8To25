# Java 10 Features

## Overview
Java 10 (released March 2018) introduced local-variable type inference (`var`) and several collection improvements.

---

## var - Local Variable Type Inference

### Basic Usage
```java
var number = 42;           // Inferred as Integer
var text = "Hello";        // Inferred as String
var decimal = 3.14;        // Inferred as Double

var list = new ArrayList<String>();
var map = new HashMap<String, Integer>();
```

### With Methods
```java
var result = calculate();  // Return type inferred

var stream = list.stream();
var iterator = list.iterator();
var entry = Map.entry("key", "value");
```

### With Complex Types
```java
var person = new Person("John", 30);
var point = new java.awt.Point(10, 20);
var strings = new String[]{"a", "b", "c"};

var anonymous = new Object() {
    String name = "Anonymous";
};
```

### In For Loops
```java
for (var item : items) {
    System.out.println(item);
}

for (var i = 0; i < 10; i++) {
    System.out.println(i);
}
```

### Limitations
```java
// Cannot be used without initialization
var x;          // ERROR
x = 10;

var a = 1, b = 2;  // ERROR - compound declaration

// Cannot be used for:
- Method parameters
- Class fields
- Lambda parameters (in Java 10, OK in Java 11+)

// Cannot be null
var nullValue = null;  // ERROR
```

### Type Erasure
```java
var list = new ArrayList<String>();  // ArrayList, not ArrayList<String>
var stringList = list;                // Inferred as ArrayList
```

---

## Collection Improvements

### List.copyOf(), Set.copyOf(), Map.copyOf()
```java
// Create immutable copies
List<String> original = Arrays.asList("A", "B", "C");
List<String> copy = List.copyOf(original);
// copy is immutable - throws UnsupportedOperationException on modify

// Set.copyOf
Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
Set<Integer> immutableSet = Set.copyOf(set);

// Map.copyOf
Map<String, Integer> map = new HashMap<>();
map.put("A", 1);
Map<String, Integer> immutableMap = Map.copyOf(map);
```

### Key Characteristics
- Creates an **immutable** copy
- Throws `NullPointerException` if input contains null (for List/Set)
- Original collection can be modified without affecting the copy
- Preserves all elements from the original

---

## Optional Improvements

### orElseThrow()
```java
Optional<String> empty = Optional.empty();

// Throws NoSuchElementException if empty
String value = empty.orElseThrow();

// With custom supplier
String custom = empty.orElseThrow(() ->
    new IllegalArgumentException("Required value missing"));
```

### ifPresentOrElse()
```java
Optional<String> present = Optional.of("Value");
present.ifPresentOrElse(
    v -> System.out.println("Found: " + v),
    () -> System.out.println("Not found")
);

Optional<String> absent = Optional.empty();
absent.ifPresentOrElse(
    v -> System.out.println("Found: " + v),
    () -> System.out.println("Not found")
);
```

---

## Stream Improvements

### toList() (Java 10)
```java
// New method in Java 10 - returns unmodifiable list
List<String> list = stream()
    .filter(s -> s.length() > 3)
    .toList();  // Unlike collect(Collectors.toList()), returns unmodifiable

// Before Java 10:
List<String> oldWay = stream()
    .filter(s -> s.length() > 3)
    .collect(Collectors.toList());  // Modifiable
```

### toSet(), toCollection()
```java
Set<String> set = stream().toSet();
List<String> linkedList = stream()
    .toCollection(LinkedList::new);
```

---

## Other Java 10 Features

### Parallel Full GC
```java
// G1GC becomes default in Java 9, but improvements in Java 10
// Use -XX:+UseParallelGC for parallel full GC
```

### Application Class-Data Sharing
```java
// Improved class data sharing for faster startup
// java -Xshare:on -XX:+UseAppCDS MyApp
```

### Additional Runtime Options
```java
// -XX:+PrintClassLoading for class loading info
// -XX:+UnlockCommercialFeatures for Oracle features
```

---

## Garbage Collection Improvements

### G1GC (Default since Java 9)
- Improved latency
- Better performance for large heaps
- Configurable pause time goals

### New Options in Java 10
```bash
-XX:+UseG1GC              # Use G1 garbage collector
-XX:MaxGCPauseMillis=200   # Target max pause time
-XX:InitiatingHeapOccupancyPercent=45  # Start GC threshold
```

---

## Performance Improvements

### Tiered Compilation
- Enabled by default
- Combines C1 and C2 compilers
- Faster warm-up

### String Deduplication
```java
// -XX:+UseStringDeduplication
// Reduces memory for duplicate strings
```

---

## Summary

| Feature | Description |
|---------|-------------|
| **var** | Local variable type inference |
| **List.copyOf()** | Create immutable list copy |
| **Set.copyOf()** | Create immutable set copy |
| **Map.copyOf()** | Create immutable map copy |
| **toList()** | Stream terminal operation returning unmodifiable list |
| **ifPresentOrElse()** | Optional consumer with else action |
| **orElseThrow()** | Throw exception if empty |

---

## Migration Notes

### Before (Java 8/9)
```java
List<String> list = Arrays.asList("A", "B");
List<String> copy = new ArrayList<>(list);
```

### After (Java 10+)
```java
var list = Arrays.asList("A", "B");
List<String> copy = List.copyOf(list);
```

### When to Use var
- **Yes**: When type is obvious from right side
- **Yes**: When it improves readability
- **No**: When type is complex or unclear
- **No**: For public API return types