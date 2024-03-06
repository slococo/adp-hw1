package edu.uastw;

import edu.uastw.library.*;
import edu.uastw.library.decorators.DecreaseBooksCapacityDecorator;
import edu.uastw.library.decorators.IncreaseBooksCapacityDecorator;
import edu.uastw.library.decorators.LibraryDecorator;
import edu.uastw.library.items.Book;
import edu.uastw.library.items.ItemType;
import edu.uastw.library.items.LibraryItem;
import edu.uastw.library.items.Magazine;

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

        Iterator<LibraryItem> bookIterator = library.customTypeIterator(ItemType.BOOK);
        System.out.println("Books available in the library:");
        bookIterator.forEachRemaining(x -> System.out.println(x.getTitle() + " by " + x.getOwner()));
        printSeparator();

        Iterator<LibraryItem> magazineIterator = library.customTypeIterator(ItemType.MAGAZINE);
        System.out.println("Magazines available in the library:");
        magazineIterator.forEachRemaining(x -> System.out.println(x.getTitle() + " by " + x.getOwner()));
    }

    private static void printSeparator() {
        System.out.println("-------------------");
    }
}