# Java 8 to 25 - Feature Exploration Project

A comprehensive project demonstrating Java features from version 8 through 25, covering all major LTS releases (11, 17, 21, 25) and intermediate versions.

 *** Using opencode ***

## Project Overview

This project provides practical code examples for the most important Java features introduced from Java 8 to Java 25. Each version has dedicated documentation and working code samples.

## Java Versions Covered

| Version | Year | Type | Key Features |
|---------|------|------|--------------|
| Java 8 | 2014 | Major | Lambdas, Stream API, Optional, Date/Time API, Default methods |
| Java 9-10 | 2017-2018 | Intermediate | Modules, var inference, Collection factories |
| Java 11 | 2018 | LTS | HTTP Client, String methods, toList(), Local variable type inference |
| Java 12-16 | 2019-2021 | Intermediate | Switch expressions, Records, Pattern matching |
| Java 17 | 2021 | LTS | Sealed classes, Records, Pattern matching for instanceof/switch |
| Java 18-20 | 2022-2023 | Intermediate | Vector API, Virtual threads (preview), Scoped values |
| Java 21 | 2023 | LTS | Virtual threads, Sequenced collections, Record patterns, String templates |
| Java 22-24 | 2024-2025 | Intermediate | Class-file API, Stream gatherers, Primitive classes |
| Java 25 | 2025 | LTS | String templates (final), val, const |

## Project Structure

```
src/main/java/org/francd/
├── Main.java
├── java8/
│   ├── lambda/           # Lambda expressions examples
│   ├── streams/          # Stream API demonstrations
│   ├── datetime/         # Date/Time API usage
│   └── functional/       # Built-in functional interfaces
├── java9-10/             # Modules, var, collection factories
├── java11/               # HTTP Client, String methods, Optional improvements
├── java12-16/            # Switch expressions, Records, Pattern matching
├── java17/               # Sealed classes, Records, Enhanced pattern matching
├── java18-20/            # Virtual threads preview, Scoped values
├── java21/               # Virtual threads, Sequenced collections, String templates
└── java22-25/            # Latest features including primitives
```

## Documentation Files

- `JavaEvolutionHighlights.md` - Overview of key LTS features with examples
- `java8.md` - java 8 features
- `java9.md` through `java25.md` - Version-specific feature documentation

## Requirements

- Java 25 (with `--enable-preview` flag for preview features)
- Maven 3.x

## Building

```bash
mvn compile
```

## Running

```bash
mvn exec:java -Dexec.mainClass="org.francd.Main"
```

## Key Features Demonstrated

### Java 8
- Lambda expressions and method references
- Stream API for functional data processing
- Optional for null-safe operations
- New Date/Time API
- Default and static methods in interfaces

### Java 11
- Local variable type inference (`var`)
- Standard HTTP client
- Enhanced String methods (`isBlank()`, `lines()`, `repeat()`)
- Collection factory methods (`List.of()`, `Set.of()`, `Map.of()`)

### Java 17
- Records for immutable data classes
- Sealed classes for controlled inheritance
- Pattern matching for `instanceof`
- Switch expressions
- Strong encapsulation

### Java 21
- Virtual threads for high-concurrency applications
- Sequenced collections with `addFirst()`, `addLast()`, `reversed()`
- Record patterns in `instanceof` and switch
- Scoped values (replacement for ThreadLocal)
- String templates (preview)

### Java 25
- String templates (final - production ready)
- `val` keyword for final local variables
- `const` keyword for compile-time constants (preview)
- Enhanced primitive classes

## Learning Approach

Each feature includes:
1. Documentation explaining the feature purpose
2. Working Java code demonstrating real-world usage
3. Before/after comparisons where applicable

## References

- [Java SE Documentation](https://docs.oracle.com/en/java/javase/)
- [OpenJDK](https://openjdk.org/)