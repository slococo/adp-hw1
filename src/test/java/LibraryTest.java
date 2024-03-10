import edu.uastw.library.decorators.DecreaseBooksCapacityDecorator;
import edu.uastw.library.decorators.IncreaseBooksCapacityDecorator;
import edu.uastw.library.decorators.LibraryDecorator;
import edu.uastw.library.exceptions.LibraryFullException;
import edu.uastw.library.items.Book;
import edu.uastw.library.Library;
import edu.uastw.library.items.ItemType;
import edu.uastw.library.interfaces.LibraryItem;
import edu.uastw.library.items.Magazine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryTest {
    private static final int LIBRARY_CAPACITY = 3;
    private static final int RETRY_ATTEMPTS = 1;
    private static final int LIBRARY_OPEN = -1;
    private static final int TIME_MULTIPLIER = 0;
    private static final int RATE_LIMIT = 1;
    private static final int TIMEOUT = 5;
    private static final int INTERVAL = 1;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Library library;

    @Before
    public void setUp() {
        library = new Library.Builder()
                .setBooksCapacity(LIBRARY_CAPACITY)
                .setRetryAttempts(RETRY_ATTEMPTS)
                .setLibraryOpenCondition(LIBRARY_OPEN)
                .setTimeMultiplier(TIME_MULTIPLIER)
                .setRateLimit(RATE_LIMIT)
                .setTimeout(TIMEOUT)
                .setInterval(INTERVAL)
                .build();

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() throws NoSuchFieldException, IllegalAccessException {
        Field libraryItemsField = Library.class.getDeclaredField("libraryItems");
        libraryItemsField.setAccessible(true);
        List<LibraryItem> libraryItems = (List<LibraryItem>) libraryItemsField.get(library);
        libraryItems.clear();
    }

     @Test
     public void testAddItem() throws Exception {
         LibraryItem book = new Book.Builder()
                    .setTitle("Test Book")
                    .setAuthor("Test Author")
                    .build();

         library.addLibraryItem(book);

         assertTrue(library.iterator().hasNext());
     }

     @Test
     public void testIterator() throws Exception {
         LibraryItem book = new Book.Builder()
                 .setTitle("Test Book")
                 .setAuthor("Test Author")
                 .build();

         library.addLibraryItem(book);

         assertTrue(library.iterator().hasNext());
         assertEquals(book, library.iterator().next());
     }

     @Test
     public void testBooksCapacity() throws Exception {
         LibraryItem book1 = new Book.Builder()
                    .setTitle("Book 1")
                    .setAuthor("Author 1")
                    .build();
         LibraryItem book2 = new Book.Builder()
                    .setTitle("Book 2")
                    .setAuthor("Author 2")
                    .build();
         LibraryItem book3 = new Book.Builder()
                    .setTitle("Book 3")
                    .setAuthor("Author 3")
                    .build();

         library.addLibraryItem(book1);
         library.addLibraryItem(book2);
         library.addLibraryItem(book3);

         assertEquals(3, library.getBooksCapacity());
     }

     @Test(expected = LibraryFullException.class)
     public void testCapacityReached() throws Exception {
         LibraryItem book1 = new Book.Builder()
                 .setTitle("Book 1")
                 .setAuthor("Author 1")
                 .build();
         LibraryItem book2 = new Book.Builder()
                 .setTitle("Book 2")
                 .setAuthor("Author 2")
                 .build();
         LibraryItem book3 = new Book.Builder()
                 .setTitle("Book 3")
                 .setAuthor("Author 3")
                 .build();
         LibraryItem book4 = new Book.Builder()
                .setTitle("Book 4")
                .setAuthor("Author 4")
                .build();

         library.addLibraryItem(book1);
         library.addLibraryItem(book2);
         library.addLibraryItem(book3);
         library.addLibraryItem(book4);

         assertEquals("Library capacity reached. Cannot add more items.\n", outputStreamCaptor.toString());
     }

     @Test
     public void testCustomIterator() throws Exception {
         LibraryItem book1 = new Book.Builder()
                 .setTitle("Book 1")
                 .setAuthor("Author 1")
                 .build();
         LibraryItem book2 = new Book.Builder()
                 .setTitle("Book 2")
                 .setAuthor("Author 2")
                 .build();
         LibraryItem magazine = new Magazine.Builder()
                .setTitle("Magazine 1")
                .setPublisher("Publisher 1")
                .build();
         library.addLibraryItem(book1);
         library.addLibraryItem(book2);
         library.addLibraryItem(magazine);

         Iterator<LibraryItem> bookIterator = library.customTypeIterator(ItemType.BOOK);

         assertTrue(bookIterator.hasNext());
         assertEquals("Book 1", bookIterator.next().getTitle());
         assertTrue(bookIterator.hasNext());
         assertEquals("Author 2", bookIterator.next().getOwner());
         assertFalse(bookIterator.hasNext());
     }

     @Test
     public void testIncreaseBooksCapacityDecorator() {
         LibraryDecorator decorator = new IncreaseBooksCapacityDecorator(library, 2);
         decorator.extendedFunctionality();

         assertEquals(5, library.getBooksCapacity());
     }

     @Test
     public void testDecreaseBooksCapacityDecorator() {
         LibraryDecorator decorator = new DecreaseBooksCapacityDecorator(library, 2);
         decorator.extendedFunctionality();

         assertEquals(1, library.getBooksCapacity());
     }

     @Test
     public void testDecreaseBooksCapacityDecoratorMax() {
         library.setBooksCapacity(1);
         LibraryDecorator decorator = new DecreaseBooksCapacityDecorator(library, 2);
         decorator.extendedFunctionality();

         assertEquals(1, library.getBooksCapacity());
     }

     @Test
     public void testDisplayLibraryItems() throws Exception {
         LibraryItem book1 = new Book.Builder()
                 .setTitle("Book 1")
                 .setAuthor("Author 1")
                 .build();
         LibraryItem book2 = new Book.Builder()
                 .setTitle("Book 2")
                 .setAuthor("Author 2")
                 .build();
         library.addLibraryItem(book1);
         library.addLibraryItem(book2);

         library.displayLibraryItems();

         String expectedOutput = "Items available in the library:\nBook 1 by Author 1\nBook 2 by Author 2\n";
         assertTrue(outputStreamCaptor.toString().contains(expectedOutput));
     }

     @Test
     public void testBuilder() {
         Library library = new Library.Builder()
                 .setBooksCapacity(3)
                 .build();

         assertEquals(3, library.getBooksCapacity());
     }

    @Test()
    public void testRemoveLibraryItem() throws Exception {
        LibraryItem book1 = new Book.Builder()
                .setTitle("Book 1")
                .setAuthor("Author 1")
                .build();
        LibraryItem book2 = new Book.Builder()
                .setTitle("Book 2")
                .setAuthor("Author 2")
                .build();
        library.addLibraryItem(book1);
        library.addLibraryItem(book2);

        library.removeLibraryItem(book1);
    }
}
