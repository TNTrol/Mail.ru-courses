
import java.util.ArrayList;
import java.util.List;


public class Library {
    private List<Book> _books = new ArrayList<>();
    //private List<Author> _authors = new ArrayList<>();

    public void addBook(Book book)
    {
        _books.add(book);
    }

    public List<Book> getBookOfAuthor(String nameOfAuthor)
    {
        List<Book> books = new ArrayList<>();
        for(Book book : _books){
            Author author = book.getAuthor();
            if(author.getName().equals(nameOfAuthor))
                books.add(book);
        }
        return books;
    }

    public List<Book> getBooks()
    {
        return _books;
    }

}
