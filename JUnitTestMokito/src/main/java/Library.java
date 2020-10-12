import java.util.List;
import com.google.gson.Gson;

public class Library {

    private int _size = 0;
    private List<Book> _books;
    private Gson _gson;

    public Library(int countCell, IBooksFactory factory)
    {
        List<Book> books = factory.createLibraryOfRange();
        if(books.size() > countCell)
        {
            throw new ArrayIndexOutOfBoundsException("Кол-во книг больше кол-ва ячеек");
        }else{
            _size = countCell;
            _books = books;
            _gson = new Gson();
        }
    }

    public Book getBook(int i)
    {
        if(i < 0 || i >= _books.size())
            throw new ArrayIndexOutOfBoundsException("Индекс книги не соответсвует промежутку");
        Book book = _books.remove(i);
        System.out.println(i + "\n" + _gson.toJson(book));
        return book;
    }

    public void addBook(Book book)
    {
        if(_size == _books.size())
            throw new ArrayIndexOutOfBoundsException("Кол-во книг больше кол-ва ячеек");
        _books.add(book);
    }

    public int size()
    {
        return _books.size();
    }

    public void printLibrary()
    {
        System.out.println(_gson.toJson(_books));
    }
}
