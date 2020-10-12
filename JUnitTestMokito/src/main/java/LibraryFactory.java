import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class LibraryFactory {
    private @NotNull IBooksFactory _booksFactory;

    @Inject
    public LibraryFactory(IBooksFactory booksFactory)
    {
        _booksFactory = booksFactory;
    }

    public Library library(int count)
    {
        return new Library(count, _booksFactory);
    }
}
