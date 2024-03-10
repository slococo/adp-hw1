package edu.uastw.library;

import edu.uastw.library.exceptions.LibraryClosedException;
import edu.uastw.library.exceptions.LibraryFullException;
import edu.uastw.library.interfaces.ExceptionRunnable;
import edu.uastw.library.items.ItemType;
import edu.uastw.library.interfaces.LibraryItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Library implements Iterable<LibraryItem> {
    private static Library instance;
    private final List<LibraryItem> libraryItems;
    private int booksCapacity = 3;

    /**** Resilience variables ****/
    private int retryAttempts;
    private double libraryOpenCondition;
    private int timeMultiplier;
    private long lastAccessTime;
    private int rateLimit;
    private int timeout;
    private int tokens;
    private int interval;
    /******************************/
    
    private Library() {
        libraryItems = new ArrayList<>();
        lastAccessTime = System.currentTimeMillis();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    // Circuit Breaker
    private boolean isLibraryOpen() {
        double rand = Math.random();
        if (rand > libraryOpenCondition) {
            System.out.println("Library open");
            return true;
        }
        System.out.println("Library closed");
        return false;
    }

    // Retry method
    private void performWithRetry(ExceptionRunnable action) throws Exception {
        int attempt = 0;
        while (attempt < retryAttempts) {
            System.out.println("Attempt: " + attempt);
            try {
                action.run();
                break;
            } catch (Exception e) {
                attempt++;
                if (attempt >= retryAttempts) {
                    System.out.println("Attempts error");
                    throw e;
                }
            }
        }
    }

    // Timeout method
    private void performWithTimeout(Runnable action, long timeoutMillis) throws Exception {
        Thread thread = new Thread(action);
        thread.start();
        System.out.println("Thread state: " + thread.getState());
        thread.join(timeoutMillis);
        System.out.println("Thread state: " + thread.getState());
        if (thread.isAlive()) {
            thread.interrupt();
            throw new TimeoutException("Operation timed out");
        }
    }

    public void addLibraryItem(LibraryItem item) throws Exception {
        if (!isLibraryOpen()) {
            throw new LibraryClosedException("Library is closed");
        }

        performWithRetry(() -> {
            if (libraryItems.size() < booksCapacity) {
                System.out.println("'" + item.getTitle() + "' was added.");
                libraryItems.add(item);
            } else {
                System.out.println("Library capacity reached. Cannot add more items.");
                throw new LibraryFullException("Library is full");
            }
        });
    }

    public void removeLibraryItem(LibraryItem item) throws Exception {
        if (!isLibraryOpen()) {
            throw new LibraryClosedException("Library is closed");
        }

        performWithTimeout(() -> {
            long random = (long) (Math.random() * timeMultiplier);
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if (libraryItems.size() > 0) {
                libraryItems.remove(item);
            } else {
                System.out.println("Library is empty");
            }
        }, timeout);
    }

    public void setBooksCapacity(int capacity) {
        this.booksCapacity = capacity;
    }

    public int getBooksCapacity() {
        return booksCapacity;
    }

    // Display with rate limit method
    public void displayLibraryItems() {
        long currentTime = System.currentTimeMillis();
        long timeElapsed = currentTime - lastAccessTime;

        tokens += (int) (timeElapsed / interval) * rateLimit;
        System.out.println("Tokens: " + tokens);
        System.out.println("Time elapsed: " + timeElapsed);
        tokens = Math.min(tokens, rateLimit);

        if (tokens > 0) {
            System.out.println("Items available in the library:");
            libraryItems.forEach(System.out::println);
            tokens--;
            lastAccessTime = currentTime;
        } else {
            System.out.println("Rate limit exceeded. Please try again later.");
        }
    }

    @Override
    public Iterator<LibraryItem> iterator() {
        return libraryItems.stream().limit(booksCapacity).iterator();
    }

    public Iterator<LibraryItem> customTypeIterator(ItemType type) {
        List<LibraryItem> itemsOfType = new ArrayList<>();
        for (LibraryItem item : libraryItems) {
            if (item.getType() == type) {
                itemsOfType.add(item);
            }
        }
        return itemsOfType.iterator();
    }

    public static class Builder {
        private final Library library;

        public Builder() {
            library = Library.getInstance();
        }

        public Builder setBooksCapacity(int booksCapacity) {
            library.setBooksCapacity(booksCapacity);
            return this;
        }

        public Builder setRetryAttempts(int retryAttempts) {
            library.retryAttempts = retryAttempts;
            return this;
        }

        public Builder setLibraryOpenCondition(double libraryOpenCondition) {
            library.libraryOpenCondition = libraryOpenCondition;
            return this;
        }

        public Builder setTimeMultiplier(int timeMultiplier) {
            library.timeMultiplier = timeMultiplier;
            return this;
        }

        public Builder setRateLimit(int rateLimit) {
            library.rateLimit = rateLimit;
            library.tokens = rateLimit;
            return this;
        }

        public Builder setTimeout(int timeout) {
            library.timeout = timeout;
            return this;
        }

        public Builder setInterval(int interval) {
            library.interval = interval;
            return this;
        }

        public Library build() {
            return library;
        }
    }
}
