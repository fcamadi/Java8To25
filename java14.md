# Java 14 Features

## Switch Expressions (Standard)

```java
String dayName = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    default -> "Unknown";
};
```

## Records (Preview)

```java
record Person(String name, int age) {}

var person = new Person("John", 30);
// Automatically has: equals(), hashCode(), toString(), accessor methods
```

## Pattern Matching for instanceof (Preview)

```java
if (obj instanceof String s) {
    System.out.println(s.length()); // s is cast automatically
}
```

## Summary

| Feature | Status |
|---------|--------|
| Switch Expressions | Standard |
| Records | Preview (final in 16) |
| Pattern Matching instanceof | Preview (final in 16) |
| Switch expressions yield | Standard |

---

## Java 14 Key Points

- Switch expressions became standard
- Records introduced as preview
- Pattern matching for instanceof preview