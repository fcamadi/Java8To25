# Java 17 Features (LTS)

## Sealed Classes (Final)

```java
sealed interface Vehicle permits Car, Bike {}
final class Car implements Vehicle {}
final class Bike implements Vehicle {}
```

## Pattern Matching for switch (Final)

```java
String result = switch (obj) {
    case String s -> "String: " + s;
    case Integer i -> "Integer: " + i;
    default -> "Unknown";
};
```

## Other Features

- Strong encapsulation of internal APIs
- New serialization filtering API
- Records can be used in switch patterns
- Stream.toList() already covered

## Summary

| Feature | Status |
|---------|--------|
| Sealed Classes | Standard |
| Pattern Matching switch | Standard |
| Records | Standard |
| Switch expressions | Standard |

---

## Java 17 Key Points

- LTS release (September 2021)
- Sealed classes finalized
- Pattern matching for switch finalized
- Strong encapsulation
- Switch expressions standard