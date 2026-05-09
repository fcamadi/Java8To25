# Java 25 Features

## String Templates (Final)

```java
// Finally standardized!
String name = "John";
String greeting = STR."Hello \{name}";

// FMT for formatted strings
String aligned = FMT."""
    Name:  %-10s\{name}
    Age:   %03d\{age}
""";
```

## val and const Keywords

```java
// val - final local variable (implicit type)
val message = "Hello"; // final String message = "Hello"

// const - compile-time constant
const int MAX = 100;
```

## Other Features

- Primitive classes further developed
- Performance optimizations
- Security improvements

## Summary

| Feature | Status |
|---------|--------|
| String Templates | Standard |
| val keyword | Preview |
| const keyword | Preview |
| Primitive Classes | Enhanced |

---

## Java 25 Key Points

- String templates become standard
- More concise local variable declaration
- Continued value type evolution

---

# Java Version Summary Table

| Version | Year | Type | Key Features |
|---------|------|------|--------------|
| 8 | 2014 | - | Lambdas, Streams, Optional, Date/Time |
| 9 | 2017 | - | Modules, JShell, Private methods in interfaces |
| 10 | 2018 | - | var, List.copyOf |
| 11 | 2018 | LTS | HTTP Client, String methods |
| 12 | 2019 | - | Switch Expressions, Teeing |
| 13 | 2019 | - | yield, Text Blocks preview |
| 14 | 2020 | - | Switch standard, Records preview |
| 15 | 2020 | - | Text Blocks standard, Sealed preview |
| 16 | 2021 | - | Records final, Pattern matching |
| 17 | 2021 | LTS | Sealed final, Pattern switch final |
| 18 | 2022 | - | UTF-8 default |
| 19 | 2022 | - | Virtual Threads preview |
| 20 | 2023 | - | Scoped Values preview |
| 21 | 2023 | LTS | Virtual Threads, Sequenced Collections |
| 22 | 2024 | - | Class-File API |
| 23 | 2024 | - | Implicit Classes |
| 24 | 2024 | - | Primitive Classes |
| 25 | 2025 | - | String Templates final, val/const |