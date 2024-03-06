package edu.uastw.library.decorators;

import edu.uastw.library.Library;

public abstract class LibraryDecorator {
    protected Library library;

    public LibraryDecorator(Library library) {
        this.library = library;
    }

    public abstract void extendedFunctionality();
}
