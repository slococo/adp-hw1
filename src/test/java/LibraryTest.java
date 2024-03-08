import edu.uastw.library.decorators.DecreaseBooksCapacityDecorator;
import edu.uastw.library.decorators.IncreaseBooksCapacityDecorator;
import edu.uastw.library.decorators.LibraryDecorator;
import edu.uastw.library.items.Book;
import edu.uastw.library.Library;
import edu.uastw.library.items.ItemType;
import edu.uastw.library.items.LibraryItem;
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
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private static int LIBRARY_CAPACITY = 3;
    private Library library;

    @Before
    public void setUp() {
        library = Library.getInstance();
        System.setOut(new PrintStream(outputStreamCaptor));
        library.setBooksCapacity(LIBRARY_CAPACITY);
    }

    @After
    public void tearDown() throws NoSuchFieldException, IllegalAccessException {
        Field libraryItemsField = Library.class.getDeclaredField("libraryItems");
        libraryItemsField.setAccessible(true);
        List<LibraryItem> libraryItems = (List<LibraryItem>) libraryItemsField.get(library);
        libraryItems.clear();
    }

    // @Test
    // public void testAddItem() {
    //     LibraryItem book = new Book("Test Book", "Test Author");
    //     try {
    //         library.addLibraryItem(book);
    //     } catch (Exception e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }

    //     assertTrue(library.iterator().hasNext());
    // }

    // @Test
    // public void testIterator() {
    //     LibraryItem book = new Book("Test Book", "Test Author");
    //     try {
    //         library.addLibraryItem(book);
    //     } catch (Exception e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }

    //     assertTrue(library.iterator().hasNext());
    //     assertEquals(book, library.iterator().next());
    // }

    // @Test
    // public void testBooksCapacity() {
    //     LibraryItem book1 = new Book("Book 1", "Author 1");
    //     LibraryItem book2 = new Book("Book 2", "Author 2");
    //     LibraryItem book3 = new Book("Book 3", "Author 3");

    //     try {
    //         library.addLibraryItem(book1);
    //         library.addLibraryItem(book2);
    //         library.addLibraryItem(book3);
    //     } catch (Exception e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }

    //     assertEquals(3, library.getBooksCapacity());
    // }

    // @Test
    // public void testCapacityReached() {
    //     LibraryItem book1 = new Book("Book 1", "Author 1");
    //     LibraryItem book2 = new Book("Book 2", "Author 2");
    //     LibraryItem book3 = new Book("Book 3", "Author 3");
    //     LibraryItem book4 = new Book("Book 4", "Author 4");

    //     library.addLibraryItem(book1);
    //     library.addLibraryItem(book2);
    //     library.addLibraryItem(book3);

    //     outputStreamCaptor.reset();

    //     library.addLibraryItem(book4);

    //     assertEquals("Library capacity reached. Cannot add more items.\n", outputStreamCaptor.toString());
    // }

    // @Test
    // public void testCustomIterator() {
    //     LibraryItem book1 = new Book("Book 1", "Author 1");
    //     LibraryItem book2 = new Book("Book 2", "Author 2");
    //     LibraryItem magazine = new Magazine("Magazine 1", "Publisher 1");

    //     library.addLibraryItem(book1);
    //     library.addLibraryItem(book2);
    //     library.addLibraryItem(magazine);

    //     Iterator<LibraryItem> bookIterator = library.customTypeIterator(ItemType.BOOK);

    //     assertTrue(bookIterator.hasNext());
    //     assertEquals("Book 1", bookIterator.next().getTitle());
    //     assertTrue(bookIterator.hasNext());
    //     assertEquals("Author 2", bookIterator.next().getOwner());
    //     assertFalse(bookIterator.hasNext());
    // }

    // @Test
    // public void testIncreaseBooksCapacityDecorator() {
    //     LibraryDecorator decorator = new IncreaseBooksCapacityDecorator(library, 2);
    //     decorator.extendedFunctionality();

    //     assertEquals(5, library.getBooksCapacity());
    // }

    // @Test
    // public void testDecreaseBooksCapacityDecorator() {
    //     LibraryDecorator decorator = new DecreaseBooksCapacityDecorator(library, 2);
    //     decorator.extendedFunctionality();

    //     assertEquals(1, library.getBooksCapacity());
    // }

    // @Test
    // public void testDecreaseBooksCapacityDecoratorMax() {
    //     library.setBooksCapacity(1);
    //     LibraryDecorator decorator = new DecreaseBooksCapacityDecorator(library, 2);
    //     decorator.extendedFunctionality();

    //     assertEquals(1, library.getBooksCapacity());
    // }

    // @Test
    // public void testDisplayLibraryItems() {
    //     LibraryItem book1 = new Book("Book 1", "Author 1");
    //     LibraryItem book2 = new Book("Book 2", "Author 2");

    //     library.addLibraryItem(book1);
    //     library.addLibraryItem(book2);

    //     outputStreamCaptor.reset();

    //     library.displayLibraryItems();
    //     String expectedOutput = "Items available in the library:\nBook 1 by Author 1\nBook 2 by Author 2\n";

    //     assertEquals(expectedOutput, outputStreamCaptor.toString());
    // }

    // @Test
    // public void testBuilder() {
    //     LibraryItem book1 = new Book("Book 1", "Author 1");
    //     LibraryItem book2 = new Book("Book 2", "Author 2");
    //     LibraryItem book3 = new Book("Book 3", "Author 3");

    //     Library library = new LibraryBuilder()
    //             .setBooksCapacity(3)
    //             .addLibraryItem(book1)
    //             .addLibraryItem(book2)
    //             .addLibraryItem(book3)
    //             .build();

    //     assertEquals(3, library.getBooksCapacity());
    // }
}
