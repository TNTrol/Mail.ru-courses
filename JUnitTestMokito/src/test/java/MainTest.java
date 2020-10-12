import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import nl.pvanassen.guicejunitrunner.GuiceJUnitRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules(MainTest.TestModule.class)
public class MainTest {


    @Inject
    private IBooksFactory _factory;

    private final Author[] _authors = {new Author("author1"), new Author("author2"), new Author("author3")};
    private final Book[] _books = {new Book("book1", _authors[1]), new Book("book2", _authors[1]),
            new Book("book3", _authors[0]), new Book("book4", _authors[2])};
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final Gson _gson = new Gson();

    public static class TestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(IBooksFactory.class).toInstance(Mockito.mock(IBooksFactory.class));
        }
    }

    @Before
    public void before()
    {
        Mockito.clearInvocations(_factory);
        List<Book> books = new ArrayList<>();
        for (Book book : _books)
            books.add(book);
        Mockito.when(_factory.createLibraryOfRange()).thenReturn(books);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

    }

    @After
    public void after() {
        System.setOut(originalOut);
        System.setErr(originalErr);
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
        int count = 5;
        List<Book> list = new ArrayList<>();
        Library lib = new Library(count, _factory);
        final int size = lib.size();
        for (int i = 0; i < size; i++)
        {
            list.add(lib.getBook(0));
        }
        Assert.assertArrayEquals(_books, list.toArray());
    }

    @Test
     public void printInformationLibraryTest()
     {
         List<Book> books = new ArrayList<>();
         for(Book book : _books)
             books.add(book);

         Library lib = new Library(5, _factory);
         lib.printLibrary();
         Assert.assertEquals(_gson.toJson(books) + "\n", outContent.toString());
     }

     @Test
     public void getBookTest()
     {
         Library lib = new Library(5, _factory);
         lib.getBook(0);
         Assert.assertEquals("0\n" + _gson.toJson(_books[0]) + "\n", outContent.toString());
     }
}
