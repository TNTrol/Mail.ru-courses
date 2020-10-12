import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

    private IBooksFactory _factory = Mockito.mock(IBooksFactory.class);

    @Before
    public void before()
    {
        Mockito.clearInvocations(_factory);
        List<Book> books = new ArrayList<Book>();
        Author[] authors = {new Author("author1"), new Author("author2"), new Author("author3")};
        books.add(new Book("book1", authors[1]));
        books.add(new Book("book2", authors[1]));
        books.add(new Book("book3", authors[0]));
        books.add(new Book("book4", authors[3]));
        Mockito.when(_factory.createLibraryOfRange()).thenReturn(books);

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void smallerLibraryTest() throws ArrayIndexOutOfBoundsException
    {
        int count = 1;
        new Library(count, _factory);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void appendNewBookTest2()
    {
        int count = 4;
        Library lib = new Library(count, _factory);
        lib.addBook(new Book("books57", new Author("author27")));
    }

    @Test
    public void getLastSetBook()
    {
        int count = 6;
        Book book = new Book("book34", new Author("author34"));
        Library lib = new Library(count, _factory);
        lib.addBook(book);
        Assert.assertEquals(book, lib.getBook(4));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getFromNullTest2()
    {
        int count = 5;
        Library lib = new Library(count, _factory);
        lib.getBook(4);
    }

    @Test
    public void orderBook()
    {

    }
}
