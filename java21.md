# Java 21 Features (LTS)

## Sequenced Collections

```java
// New interfaces
SequencedCollection<String> col = new ArrayList<>();
col.addFirst("first");
col.addLast("last");
col.getFirst(); // first
col.getLast();  // last
col.reversed(); // reversed view
```

## Virtual Threads (Final)

```java
// Production ready
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> task());
}
```

## String Templates (Preview)

```java
String name = "World";
String greeting = STR."Hello \{name}"; // Not final in 21
```

## Pattern Matching

```java
// Unnamed patterns
case null -> "Null"
case int i -> "Integer: " + i
case String s -> "String"
```

## Summary

| Feature | Status |
|---------|--------|
| Sequenced Collections | Standard |
| Virtual Threads | Standard |
| String Templates | Preview |
| Record Patterns | Standard |
| Scoped Values | Standard |

---

## Java 21 Key Points

- LTS release (September 2023)
- Virtual threads production-ready
- Sequenced collections API
- Major improvements in concurrency