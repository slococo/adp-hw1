package edu.uastw;

public class LibraryBuilder {
    private final Library library;

    public LibraryBuilder() {
        library = Library.getInstance();
    }

    public LibraryBuilder addLibraryItem(LibraryItem libraryItem) {
        library.addLibraryItem(libraryItem);
        return this;
    }

    public Library build() {
        return library;
    }
}
