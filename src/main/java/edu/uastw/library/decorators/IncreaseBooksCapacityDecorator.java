package edu.uastw.library.decorators;

import edu.uastw.library.Library;

public class IncreaseBooksCapacityDecorator extends LibraryDecorator {
    private final int additionalCapacity;

    public IncreaseBooksCapacityDecorator(Library library, int additionalCapacity) {
        super(library);
        this.additionalCapacity = additionalCapacity;
    }

    @Override
    public void extendedFunctionality() {
        library.setBooksCapacity(library.getBooksCapacity() + additionalCapacity);
        System.out.println("Books capacity increased by " + additionalCapacity);
    }
}
