package edu.uastw;

class DecreaseBooksCapacityDecorator extends LibraryDecorator {
    private final int reducedCapacity;

    public DecreaseBooksCapacityDecorator(Library library, int reducedCapacity) {
        super(library);
        this.reducedCapacity = reducedCapacity;
    }

    @Override
    public void extendedFunctionality() {
        int newCapacity = library.getBooksCapacity() - reducedCapacity;
        if (newCapacity >= 0) {
            library.setBooksCapacity(newCapacity);
            System.out.println("Books capacity decreased by " + reducedCapacity);
        } else {
            System.out.println("Cannot decrease capacity. New capacity would be negative.");
        }
    }
}
