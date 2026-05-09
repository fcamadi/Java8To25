# Java 22 Features

## Class-File API (Final)

```java
// Parse class files programmatically
ClassFile cf = ClassFile.parse(bytecode);
cf.methods().forEach(m -> System.out.println(m.get().name()));
```

## Stream Gatherers (Preview)

- Custom stream operations
- Windowing, scanning, reducing

## String Templates (Second Preview)

- STR and FMT templates
- Type-safe string interpolation

## Summary

| Feature | Status |
|---------|--------|
| Class-File API | Standard |
| Stream Gatherers | Preview |
| String Templates | Preview |

---

## Java 22 Key Points

- Class-File API finalized
- Stream improvements
- Continued string template development