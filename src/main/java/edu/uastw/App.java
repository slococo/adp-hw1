package edu.uastw;

import edu.uastw.library.*;
import edu.uastw.library.decorators.DecreaseBooksCapacityDecorator;
import edu.uastw.library.decorators.IncreaseBooksCapacityDecorator;
import edu.uastw.library.decorators.LibraryDecorator;
import edu.uastw.library.interfaces.IgnoreCoverage;
import edu.uastw.library.items.Book;
import edu.uastw.library.items.ItemType;
import edu.uastw.library.interfaces.LibraryItem;
import edu.uastw.library.items.Magazine;

import java.util.Iterator;

@IgnoreCoverage
public class App {
    /***************** Resilience variables *****************/
    private static final int RETRY_ATTEMPTS = 2;
    private static final double LIBRARY_OPEN_CONDITION = 0;
    private static final int TIME_MULTIPLIER = 3000;
    private static final int RATE_LIMIT = 2;
    private static final int TIMEOUT = 1000;
    private static final int INTERVAL = 10000;
    /********************************************************/

    public static void main(String[] args) {
        LibraryItem book1 = new Book.Builder()
                                .setTitle("The Great Gatsby")
                                .setAuthor( "F. Scott Fitzgerald")
                                .build();

        LibraryItem book2 = new Book.Builder()
                                .setTitle("To Kill a Mockingbird")
                                .setAuthor("Harper Lee")
                                .build();
                                
        LibraryItem book3 = new Book.Builder()
                                .setTitle("1984")
                                .setAuthor("George Orwell")
                                .build();
                                
        LibraryItem magazine1 = new Magazine.Builder()
                                .setTitle("National Geographic")
                                .setPublisher("National Geographic Society")
                                .build();

        Library library = new Library.Builder()
                .setBooksCapacity(3)
                .setRetryAttempts(RETRY_ATTEMPTS)
                .setLibraryOpenCondition(LIBRARY_OPEN_CONDITION)
                .setTimeMultiplier(TIME_MULTIPLIER)
                .setRateLimit(RATE_LIMIT)
                .setTimeout(TIMEOUT)
                .setInterval(INTERVAL)
                .build();

        try {
            library.addLibraryItem(book1);
            printSeparator();
            library.addLibraryItem(book2);
            printSeparator();
            library.addLibraryItem(book3);
            printSeparator();
            library.addLibraryItem(magazine1);
            printSeparator();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        printSeparator();
        library.displayLibraryItems();
        printSeparator();
        library.displayLibraryItems();
        printSeparator();
        library.displayLibraryItems();
        printSeparator();

        try {
            Thread.sleep(INTERVAL + 5);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        library.displayLibraryItems();
        printSeparator();

        LibraryDecorator increasedCapacityLibrary = new IncreaseBooksCapacityDecorator(library, 1);
        increasedCapacityLibrary.extendedFunctionality();
        printSeparator();

        try {
			library.addLibraryItem(magazine1);
		} catch (Exception e) {
            System.out.println(e.getMessage());
		}
        
        printSeparator();
        library.displayLibraryItems();
        printSeparator();

        LibraryDecorator decreasedCapacityLibrary = new DecreaseBooksCapacityDecorator(library, 2);
        decreasedCapacityLibrary.extendedFunctionality();
        printSeparator();

        try {
			library.addLibraryItem(magazine1);
            printSeparator();
		} catch (Exception e) {
            System.out.println(e.getMessage());
		}

        library.iterator().forEachRemaining(System.out::println);
        printSeparator();

        Iterator<LibraryItem> bookIterator = library.customTypeIterator(ItemType.BOOK);
        System.out.println("Books available in the library:");
        bookIterator.forEachRemaining(System.out::println);
        printSeparator();

        Iterator<LibraryItem> magazineIterator = library.customTypeIterator(ItemType.MAGAZINE);
        System.out.println("Magazines available in the library:");
        magazineIterator.forEachRemaining(System.out::println);
        printSeparator();

        try {
            library.removeLibraryItem(book1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printSeparator() {
        System.out.println("-------------------");
    }
}