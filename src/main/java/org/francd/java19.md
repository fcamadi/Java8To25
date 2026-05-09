# Java 19 Features

## Virtual Threads (Preview)

```java
// Create virtual thread
Thread vt = Thread.ofVirtual().start(() -> System.out.println("Virtual!"));
vt.join();

// Using executor
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> task());
}
```

## Pattern Matching for switch (Preview)

```java
Object obj = "Hello";
String result = switch (obj) {
    case String s -> "String: " + s;
    case Integer i -> "Integer: " + i;
    default -> "Unknown";
};
```

## Structured Concurrency (Incubator)

- Group tasks that share dependencies
- Cancel all if one fails

## Summary

| Feature | Status |
|---------|--------|
| Virtual Threads | Preview (final in 21) |
| Pattern Matching switch | Preview (final in 21) |
| Record Patterns | Preview |
| Structured Concurrency | Incubator |

---

## Java 19 Key Points

- Virtual threads - lightweight threading
- Major concurrency improvements
- Preview of project Loom features