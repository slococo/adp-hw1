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
- **Test:** Contains the main method to demonstrate the library system.

## Design Patterns Used

- **Builder Pattern:** Used in the LibraryBuilder class to construct the library by adding books and magazines.
- **Singleton Pattern:** Implemented in the Library class to ensure only one instance of the library exists throughout the application.
- **Decorator Pattern:** Implemented with LibraryDecorator and its concrete decorators (IncreaseBooksCapacityDecorator and DecreaseBooksCapacityDecorator) to extend the functionality of the Library dynamically.
- **Iterator Pattern:** Implemented in the Library class to provide a way to iterate over its collection of items.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Maven version 3 or higher
- Java version 11 or higher

## Usage

To run the project:

1. Clone or download the repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Run the Test class.

The Test class demonstrates the following functionalities:

- Creation of books and magazines.
- Adding items to the library.
- Displaying items in the library.
- Modifying the books capacity using decorators.
- Iterating over the library items using the Iterator pattern.

## Contributors

- Lucas Catolino
- Santiago Lo Coco
- Joel Kudiyirickal
