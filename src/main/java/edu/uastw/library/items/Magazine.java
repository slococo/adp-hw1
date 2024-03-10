package edu.uastw.library.items;

import edu.uastw.library.interfaces.LibraryItem;

public class Magazine implements LibraryItem {
    private final String title;
    private final String publisher;

    private Magazine(String title, String publisher) {
        this.title = title;
        this.publisher = publisher;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getOwner() {
        return publisher;
    }

    @Override
    public ItemType getType() {
        return ItemType.MAGAZINE;
    }

    public static class Builder {
        private String title;
        private String publisher;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Magazine build() {
            return new Magazine(title, publisher);
        }
    }
}
