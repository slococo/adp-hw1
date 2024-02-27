package edu.uastw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library implements Iterable<LibraryItem> {
    private static Library instance;
    private final List<LibraryItem> libraryItems;
    private int booksCapacity = 3;

    Library() {
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
            System.out.println("'" + libraryItem.getTitle() + "' was added.");
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

    @Override
    public Iterator<LibraryItem> iterator() {
        return new LibraryItemIterator();
    }

    private class LibraryItemIterator implements Iterator<LibraryItem> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < booksCapacity;
        }

        @Override
        public LibraryItem next() {
            return libraryItems.get(currentIndex++);
        }
    }

    public Iterator<LibraryItem> customTypeIterator(ItemType type) {
        List<LibraryItem> itemsOfType = new ArrayList<>();
        for (LibraryItem item : libraryItems) {
            if (item.getType() == type) {
                itemsOfType.add(item);
            }
        }
        return itemsOfType.iterator();
    }
}
