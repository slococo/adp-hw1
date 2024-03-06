package edu.uastw.library;

import edu.uastw.library.items.LibraryItem;

public class LibraryBuilder {
    private final Library library;

    public LibraryBuilder() {
        library = Library.getInstance();
    }

    public LibraryBuilder addLibraryItem(LibraryItem libraryItem) {
        library.addLibraryItem(libraryItem);
        return this;
    }

    public LibraryBuilder setBooksCapacity(int booksCapacity) {
        library.setBooksCapacity(booksCapacity);
        return this;
    }

    public Library build() {
        return library;
    }
}
