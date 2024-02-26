package edu.uastw;

public abstract class LibraryDecorator extends Library {
    protected Library library;

    public LibraryDecorator(Library library) {
        this.library = library;
    }

    public abstract void extendedFunctionality();
}
