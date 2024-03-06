package edu.uastw.library.items;

public class Book implements LibraryItem {
    private final String title;
    private final String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getOwner() {
        return author;
    }

    @Override
    public ItemType getType() {
        return ItemType.BOOK;
    }
}
