
# People Statistics Application

A Java application that reads a newline-delimited JSON (`.ndjson`) file containing `Person` records, computes various statistics, and prints the results to the console.

## Table of Contents
- [Overview](#overview)
- [Setup](#setup)
- [How to run tests](#how-to-run-tests)
- [Design Log](#design-log)

---  

## Overview

This application:
- Loads person data from a `.ndjson` file.
- Performs statistical analysis on the dataset.
- Outputs results such as:
  - Oldest person’s name.
  - Average age for the dataset.
  - Youngest person in each country.
  - Average age by country.
  - 10-year age buckets for people in New Zealand.

## Setup

### Prerequisites
- Java 17 or later
- Gradle (or use the Gradle wrapper `./gradlew` included in the project)

### Clone and Build
```bash  
git clone https://github.com/Abhi200523-dotcom/phocas-intern-exercise  
cd phocas-intern-exercise  
./gradlew build 
```


### 3 - Using the application
```bash  
./gradlew run 
```

## How to run tests
Ensure that your environment variables are set up correctly to run the tests  
in your local environment.

On Linux and MacOS:
```  
./gradlew test  
```  

On Windows:

```  
gradlew test  
```

## Design Log

### Overview
This application reads newline-delimited JSON (`.ndjson`) records of `Person` objects, computes various statistics with Java Streams, and prints them to the console. The goal was to keep I/O code separate from computation logic for better testability and maintainability.

### Key Decisions and Rationale

1. **Use of Jackson (`ObjectMapper`) for JSON parsing**
  - **Why:** fast and supports direct mapping to `Person`.
  - **Trade-offs:** Adds a dependency, strict mapping means malformed lines throw exceptions.
  - **Alternative:** Gson (lighter, less strict) or manual parsing (no dependency but more error-prone).

2. **Use of `record` for `Person` instead of a traditional class**
  - **Why:** Records in Java provide a concise way to declare immutable data carriers with built-in implementations of `equals()`, `hashCode()`, and `toString()`. Since `Person` is purely a data holder, using a `record` avoids boilerplate and clearly signals immutability.
  - **Trade-offs:** Records cannot have mutable fields and have restrictions on extending other classes, so they are less flexible if requirements change to allow updates or complex behaviour.
  - **Alternative:** Define `Person` as a regular class with explicit fields, getters, and overridden `equals()`, `hashCode()`, and `toString()`, which offers more control but requires more code.

3. **Use of Java Streams for statistics**
  - **Why:** Concise, declarative style, and test.
  - **Trade-offs:** Harder to debug compared to  loops.
  - **Alternative:** Manual loops with accumulators.

4. **Deterministic tie-breaking in `findOldestName`**
  - **Why:** Comparator breaks ties with first/last name to ensure reproducible results.
  - **Trade-offs:** Choice of tie-breaker affects which record wins, alternative is to use first or last encountered.

5. **Using `TreeMap` for sorted NZ age buckets**
  - **Why:** Automatically sorts buckets by key for clean output.
  - **Trade-offs:** Slower inserts than `HashMap` (`O(log n)` vs average `O(1)`).
  - **Alternative:** Collect to `HashMap` then sort into a `LinkedHashMap`(`O(nlog n)`).

6. **Did Not Document Unit Tests**
  - **Why:** The test class and method names were self-descriptive enough to convey their purpose, so additional documentation was not necessary.
  - **Trade-offs:** While this saved time, relying solely on descriptive names can still make it harder for new contributors to fully understand the test setup.
  - **Alternative:** Add Javadoc or a short README section summarizing the purpose and coverage of each test class, especially for larger or more complex projects.


### Testing
- **Loader Tests:** Created temp file with valid and invalid lines; verified valid records parsed and invalid lines logged (captured `System.err` in tests).

### Use of AI Tools
I used AI (ChatGPT) to assist with:
- Explaining Java `Collectors` ,`Comparator` and `Streams`methods and parameters.
- Explaining Java concepts such as `TreeMap` vs `HashMap`, `LinkedHashMap` ordering, and stream based data processing.
- Suggesting unit test strategies for handling invalid JSON lines and generating edge case test ideas.
- Writing and refining Javadoc for the `LoadPeople` class and other parts of the codebase.
- Drafting an initial version of the `README.md`.

**Challenges:**
- AI sometimes suggested overly complex approaches for small tasks.
- Encountered a file path resolution issue: `Paths.get("interndata.ndjson")` searches the working directory, but the file was located in `src/main/resources`. Resolved by  moving the file to the working directory.
- Had to adjust the `build.gradle` file to ensure dependencies (e.g., Jackson) were included correctly and to configure the application plugin for running the `main` class without manual classpath setup.


**Verification:**
- I ran `gradlew test` after each change to confirm correctness.
- Manually reviewed generated code to ensure it met project requirements.
- Cross-checked AI explanations with the official Java documentation.
