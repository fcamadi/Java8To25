# Java 16 Features

## Records (Final)

```java
record Point(int x, int y) {
    public int quadrant() {
        if (x > 0 && y > 0) return 1;
        return 4;
    }
}

// Can implement interfaces
record Person(String name) implements Identifiable {
    public String getId() { return name; }
}
```

## Pattern Matching for instanceof (Final)

```java
if (obj instanceof String s && s.length() > 5) {
    System.out.println(s.toUpperCase());
}
```

## Stream.toList() Returns Unmodifiable

```java
List<String> list = stream.toList(); // Unmodifiable!
```

## Summary

| Feature | Status |
|---------|--------|
| Records | Standard |
| Pattern Matching instanceof | Standard |
| Stream.toList() | Unmodifiable |
| Pattern Matching switch | Preview |

---

## Java 16 Key Points

- Records became standard (final)
- Pattern matching finalized
- Stream.toList() returns unmodifiable
- New memory allocation model