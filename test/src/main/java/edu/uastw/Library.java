package edu.uastw;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private static Library instance;
    private final List<LibraryItem> libraryItems;
    private int booksCapacity = 100;

    public Library() {
        this.libraryItems = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }
    public void setBooksCapacity(int capacity) {
        this.booksCapacity = capacity;
    }

    public int getBooksCapacity() {
        return booksCapacity;
    }

    public void addLibraryItem(LibraryItem libraryItem) {
        if (libraryItems.size() < booksCapacity) {
            libraryItems.add(libraryItem);
        } else {
            System.out.println("Library capacity reached. Cannot add more items.");
        }
    }

    public void displayLibraryItems() {
        System.out.println("Items available in the library:");
        libraryItems.forEach(libraryItem ->
                System.out.println(libraryItem.getTitle() + " by " + libraryItem.getOwner())
        );
    }
}
