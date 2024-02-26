package edu.uastw;

public class Magazine implements LibraryItem {
    private final String title;
    private final String publisher;

    public Magazine(String title, String publisher) {
        this.title = title;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getOwner() {
        return publisher;
    }
}
