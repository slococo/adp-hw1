package edu.uastw.library.interfaces;

import edu.uastw.library.items.ItemType;

public interface LibraryItem {
    String getTitle();
    String getOwner();
    ItemType getType();

    default String defaultToString() {
        return getTitle() + " by " + getOwner();
    }
}
