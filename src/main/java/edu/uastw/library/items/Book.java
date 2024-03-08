package edu.uastw.library.items;

public class Book implements LibraryItem {
    private final String title;
    private final String author;

    private Book(String title, String author) {
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

    public static class Builder {
        private String title;
        private String author;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Book build() {
            return new Book(title, author);
        }
    }
}
