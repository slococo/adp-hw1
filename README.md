# Library System with Design Patterns

This is a simple Java project demonstrating a library system that utilizes various design patterns including builder, singleton, decorator, and iterator.

## Overview

The project consists of the following components:

- **Book:** Represents a book in the library.
- **Magazine:** Represents a magazine in the library.
- **LibraryItem:** An interface representing items in the library, implemented by both Book and Magazine classes.
- **Library:** Implements the singleton pattern and represents the library. It manages the collection of items (books and magazines) and supports adding items with a capacity constraint. It also implements the iterator pattern to provide a way to iterate over its items.
- **LibraryBuilder:** Implements the builder pattern to construct the library by adding books and magazines.
- **LibraryDecorator:** An abstract class for extending functionality of the Library.
- **IncreaseBooksCapacityDecorator:** A concrete decorator to increase the books capacity of the Library.
- **DecreaseBooksCapacityDecorator:** A concrete decorator to decrease the books capacity of the Library.
- **App:** Contains the main method to demonstrate the library system.

## Design Patterns Used

- **Builder Pattern:** Used in the LibraryBuilder class to construct the library by adding books and magazines.
- **Singleton Pattern:** Implemented in the Library class to ensure only one instance of the library exists throughout the application.
- **Decorator Pattern:** Implemented with LibraryDecorator and its concrete decorators (IncreaseBooksCapacityDecorator and DecreaseBooksCapacityDecorator) to extend the functionality of the Library dynamically.
- **Iterator Pattern:** Implemented by custom iterators in the Library class to iterate over the collection of items.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Maven >= 3
- Java >= 11

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

## Contributors

- Lucas Catolino
- Santiago Lo Coco
- Joel Kudiyirickal
