# Java 20 Features

## Scoped Values (Preview)

```java
var scopedValue = ScopedValue.where(value, "data")
    .get(() -> value.get());
```

## Record Patterns (Preview)

```java
// In instanceof
if (obj instanceof Point(int x, int y)) {
    System.out.println(x + y);
}

// In switch
String result = switch (obj) {
    case Point(int x, int y) when x > 0 -> "Q1";
    default -> "Other";
};
```

## Virtual Threads Updates

- Improvements based on feedback
- More stable for production

## Summary

| Feature | Status |
|---------|--------|
| Scoped Values | Preview |
| Record Patterns | Preview |
| Virtual Threads | Enhanced |
| Pattern Matching switch | Preview |

---

## Java 20 Key Points

- Scoped values as ThreadLocal alternative
- Record patterns in more contexts
- Virtual threads maturing