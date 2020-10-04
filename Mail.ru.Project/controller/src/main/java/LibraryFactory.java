import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class LibraryFactory {

    public static Library createLibrary(String fileName)
    {
        Library lib = new Library();
        List<Book> books = readLibraryFromFile(fileName);
        for (Book book : books)
            lib.addBook(book);
        return lib;
    }

    private static List<Book> readLibraryFromFile(String fileName) {
        List<Book> books;
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            books = gson.fromJson(reader, new TypeToken<List<Book>>() {}.getType());
            reader.close();
            return books;
        }catch (Exception e){
            return null;
        }

    }
}
