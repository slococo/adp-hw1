package edu.uastw;

public class Test {
    public static void main(String[] args) {
        LibraryItem book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        LibraryItem book2 = new Book("To Kill a Mockingbird", "Harper Lee");
        LibraryItem book3 = new Book("1984", "George Orwell");
        LibraryItem magazine1 = new Magazine("National Geographic", "National Geographic Society");

        Library library = new LibraryBuilder()
                .addLibraryItem(book1)
                .addLibraryItem(book2)
                .addLibraryItem(book3)
                .addLibraryItem(magazine1)
                .build();

        library.displayLibraryItems();

        LibraryDecorator increasedCapacityLibrary = new IncreaseBooksCapacityDecorator(library, 1);
        increasedCapacityLibrary.extendedFunctionality();

        library.addLibraryItem(magazine1);
        library.displayLibraryItems();

        System.out.println("Modified books capacity: " + library.getBooksCapacity());

        LibraryDecorator decreasedCapacityLibrary = new DecreaseBooksCapacityDecorator(library, 2);
        decreasedCapacityLibrary.extendedFunctionality();

        System.out.println("Modified books capacity: " + library.getBooksCapacity());
        library.displayLibraryItems();

        library.addLibraryItem(magazine1);
        library.displayLibraryItems();
    }
}