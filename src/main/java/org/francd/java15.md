# Java 15 Features

## Text Blocks (Standard)

```java
String json = """
    {
        "name": "John",
        "age": 30
    }
    """;
```

## Sealed Classes (Preview)

```java
sealed interface Shape permits Circle, Rectangle, Triangle {}
record Circle(double radius) implements Shape {}
record Rectangle(double width, double height) implements Shape {}
record Triangle(double base, double height) implements Shape {}
```

## Records (Second Preview)

- Enhanced record features
- Local records allowed
- Can implement interfaces

## Summary

| Feature | Status |
|---------|--------|
| Text Blocks | Standard |
| Sealed Classes | Preview (final in 17) |
| Records | Second Preview (final in 16) |
| Pattern Matching instanceof | Preview |

---

## Java 15 Key Points

- Text blocks became standard
- Sealed classes preview
- Second preview of records
- Hidden classes added