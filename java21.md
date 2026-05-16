# Java 21 Features (LTS)

## Sequenced Collections

### Overview
Sequenced Collections provide a more natural way to manage collections with `addFirst` and `addLast` methods.

### Examples
```java
import java.util.SequencedCollection;
import java.util.ArrayList;

class SequencedCollectionsExample {
    public static void main(String[] args) {
        SequencedCollection<String> col = new ArrayList<>();
        col.addFirst("first");
        col.addLast("last");
        System.out.println("First: " + col.getFirst()); // first
        System.out.println("Last: " + col.getLast());  // last
        System.out.println("Reversed: " + col.reversed()); // reversed view
    }
}
```

### Best Practices
- Use `addFirst` and `addLast` methods for adding elements to the front and back of the collection.
- Use `getFirst` and `getLast` methods to retrieve the first and last elements.

### Performance Benchmarks
- Adding elements to the front and back of a collection is more efficient with `addFirst` and `addLast`.

---

## Virtual Threads (Final)

### Overview
Virtual threads are production-ready and provide a more efficient way to manage threads.

### Examples
```java
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class VirtualThreadsExample {
    public static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> task());
        }
    }

    public static void task() {
        // Task logic
    }
}
```

### Best Practices
- Use virtual threads for I/O-bound tasks to improve concurrency.
- Avoid blocking the main thread with virtual threads.

### Performance Benchmarks
- Virtual threads can improve the throughput and concurrency of I/O-bound tasks.

---

## String Templates (Preview)

### Overview
String templates provide a preview feature for more flexible string interpolation.

### Examples
```java
String name = "World";
String greeting = String.format("Hello %s", name);
System.out.println(greeting); // Hello World

// New String Templates (Java 21 Preview)
String greetingNew = String.format("Hello {0}", name);
System.out.println(greetingNew); // Hello World
```

### Best Practices
- Use string templates for more expressive string interpolation.
- Ensure to handle any potential performance implications when using the preview feature.

### Performance Benchmarks
- String templates can provide better performance and readability for complex string formatting.

---

## Pattern Matching

### Overview
Pattern matching allows for more expressive switch cases using unnamed patterns.

### Examples
```java
public class PatternMatchingExample {
    public static void main(String[] args) {
        Object o = 42;
        switch (o) {
            case null -> "Null";
            case int i -> "Integer: " + i;
            case String s -> "String";
        }
    }
}
```

### Best Practices
- Use switch cases with patterns for more readable and concise code.
- Ensure to handle all possible cases to avoid runtime errors.

### Performance Benchmarks
- Pattern matching can improve code readability and maintainability.

---

## Summary

| Feature | Status | Best Practice |
|---------|--------|---------------|
| Sequenced Collections | Standard | Use `addFirst` and `addLast` for efficient collection management. |
| Virtual Threads | Standard | Use for I/O-bound tasks. |
| String Templates | Preview | Use for expressive string interpolation. |
| Pattern Matching | Standard | Use for readable switch cases. |

---

## Java 21 Key Points

- LTS release (September 2023)
- Virtual threads production-ready
- Sequenced collections API
- Major improvements in concurrency