package edu.uastw;

public class Book implements LibraryItem {
    private final String title;
    private final String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getOwner() {
        return author;
    }
}
