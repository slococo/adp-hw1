package edu.uastw;

import java.util.Iterator;

public class App {
    public static void main(String[] args) {
        LibraryItem book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        LibraryItem book2 = new Book("To Kill a Mockingbird", "Harper Lee");
        LibraryItem book3 = new Book("1984", "George Orwell");
        LibraryItem magazine1 = new Magazine("National Geographic", "National Geographic Society");

        Library library = new LibraryBuilder()
                .setBooksCapacity(3)
                .addLibraryItem(book1)
                .addLibraryItem(book2)
                .addLibraryItem(book3)
                .addLibraryItem(magazine1)
                .build();

        library.displayLibraryItems();
        printSeparator();

        LibraryDecorator increasedCapacityLibrary = new IncreaseBooksCapacityDecorator(library, 1);
        increasedCapacityLibrary.extendedFunctionality();

        library.addLibraryItem(magazine1);
        library.displayLibraryItems();
        printSeparator();

        LibraryDecorator decreasedCapacityLibrary = new DecreaseBooksCapacityDecorator(library, 2);
        decreasedCapacityLibrary.extendedFunctionality();

        library.addLibraryItem(magazine1);
        library.iterator().forEachRemaining(x -> System.out.println(x.getTitle() + " by " + x.getOwner()));
        printSeparator();

        Iterator<LibraryItem> bookIterator = library.customTypeIterator(Library.ItemType.BOOK);
        System.out.println("Books available in the library:");
        bookIterator.forEachRemaining(x -> System.out.println(x.getTitle() + " by " + x.getOwner()));

        Iterator<LibraryItem> magazineIterator = library.customTypeIterator(Library.ItemType.MAGAZINE);
        System.out.println("Magazines available in the library:");
        magazineIterator.forEachRemaining(x -> System.out.println(x.getTitle() + " by " + x.getOwner()));
    }

    private static void printSeparator() {
        System.out.println("-------------------");
    }
}