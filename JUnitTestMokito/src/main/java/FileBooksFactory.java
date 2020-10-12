import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileBooksFactory implements IBooksFactory {
    private @NotNull String _fileName;
    private static final Type _listBooksType = new TypeToken<ArrayList<Book>>() {
    }.getType();

    public FileBooksFactory(String fileName)
    {
        _fileName = fileName;
    }

    @Override
    public List<Book> createLibraryOfRange() {
        try {
            return new Gson().fromJson(new BufferedReader(new FileReader(_fileName)), _listBooksType);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
