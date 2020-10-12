import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args)
    {
        int count = 512;
        Injector injector = Guice.createInjector(new MyModule());
        Library lib =  injector.getInstance(LibraryFactory.class).library(count);
        //lib.getBook(10);
        Author author = new Author("data");
        Book book = new Book("fff", author);
        lib.addBook(book);
        //lib.printLibrary();
    }
}
