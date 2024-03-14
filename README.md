# Library System with Design Patterns


This is a simple Java project demonstrating a library system that utilizes various design patterns including builder, singleton, decorator, and iterator.

## Index
1. [Prerequisites](#prerequisites)
2. [Overview](#overview)
3. [Design Patterns Used](#design-patterns-used)
4. [Resilience Patterns Used](#resilience-patterns-used)
5. [Usage](#usage)
6. [Note](#note)
7. [Contributors](#contributors)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Maven >= 3
- Java >= 11

## Overview

The project consists of the following components:

- **Book:** Represents a book in the library.
- **Magazine:** Represents a magazine in the library.
- **LibraryItem:** An interface representing items in the library, implemented by both Book and Magazine classes.
- **Library:** Implements the singleton pattern and represents the library. It manages the collection of items (books and magazines) and supports adding items with a capacity constraint. It also implements the iterator pattern to provide a way to iterate over its items.
- **LibraryDecorator:** An abstract class for extending functionality of the Library.
- **IncreaseBooksCapacityDecorator:** A concrete decorator to increase the books capacity of the Library.
- **DecreaseBooksCapacityDecorator:** A concrete decorator to decrease the books capacity of the Library.
- **App:** Contains the main method to demonstrate the library system.

## Design Patterns Used

- **Builder Pattern:** Used in the Library, Book and Magazine classes to construct the library, book and magazine respectively.
- **Singleton Pattern:** Implemented in the Library class to ensure only one instance of the library exists throughout the application.
- **Decorator Pattern:** Implemented with LibraryDecorator and its concrete decorators (IncreaseBooksCapacityDecorator and DecreaseBooksCapacityDecorator) to extend the functionality of the Library dynamically.
- **Iterator Pattern:** Implemented by custom iterators in the Library class to iterate over the collection of items.

## Resilience Patterns Used

- **Retry Pattern:** The Retry pattern allows the system to automatically retry failed operations with the expectation that they might succeed on subsequent attempts.

- **Timeout Pattern:** The Timeout pattern sets a maximum time for an operation to complete before it's considered unsuccessful, helping prevent long-running operations from causing delays or blocking resources indefinitely.

- **Rate Limiting Pattern:** The Rate Limiting pattern restricts the number of requests a system can handle within a specified time frame to prevent overload and ensure fair resource allocation.

- **Circuit Breaker Pattern:** The Circuit Breaker pattern helps handle failures gracefully by temporarily blocking requests to a service when it's deemed unavailable or experiencing a high failure rate, thereby preventing cascading failures and conserving resources.

You can modify the parameters related to these resilience patterns from the `App.java` file.

## Usage

To run the project, first clone or download the repository to your local machine. Now, you have two options: using an IDE or using the CLI (terminal).

### IDE

1. Open the project in your preferred Java IDE.
2. Run the `App` class.

### CLI

```sh
mvn compile exec:java
```

The `App` class demonstrates the following functionalities:

- Creation of books and magazines.
- Adding items to the library.
- Displaying items in the library.
- Modifying the books capacity using decorators.
- Iterating over the library items using the Iterator pattern.

## Note

In the `./assets/` directory, you can find a UML diagram of the project and the document `adp_hw3.pdf`, which contains a brief description of the application idea and discussions about design decisions and patterns used.

## Contributors

- Lucas Catolino
- Santiago Lo Coco
- Joel Kudiyirickal
