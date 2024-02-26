import edu.uastw.Book;
import edu.uastw.Library;
import edu.uastw.LibraryItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryTest {
    private Library library;

    @Before
    public void setUp() {
        library = Library.getInstance();
    }

    @After
    public void tearDown() throws NoSuchFieldException, IllegalAccessException {
        Field libraryItemsField = Library.class.getDeclaredField("libraryItems");
        libraryItemsField.setAccessible(true);
        List<LibraryItem> libraryItems = (List<LibraryItem>) libraryItemsField.get(library);
        libraryItems.clear();
    }
    @Test
    public void testAddItem() {
        LibraryItem book = new Book("Test Book", "Test Author");
        library.addLibraryItem(book);
        assertTrue(library.iterator().hasNext());
    }

    @Test
    public void testIterator() {
        LibraryItem book = new Book("Test Book", "Test Author");
        library.addLibraryItem(book);
        assertTrue(library.iterator().hasNext());
        assertEquals(book, library.iterator().next());
    }

    @Test
    public void testBooksCapacity() {
        LibraryItem book1 = new Book("Book 1", "Author 1");
        LibraryItem book2 = new Book("Book 2", "Author 2");
        LibraryItem book3 = new Book("Book 3", "Author 3");

        library.addLibraryItem(book1);
        library.addLibraryItem(book2);
        library.addLibraryItem(book3);

        assertEquals(3, library.getBooksCapacity());
    }
}
