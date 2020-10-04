import com.google.gson.Gson;
import java.util.List;
import java.util.Scanner;

public class Aplication {

    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();

        Library lib = LibraryFactory.createLibrary("../lib.json");
        String nameOfAuthor = scanner.next();
        List<Book> books = lib.getBookOfAuthor(nameOfAuthor);
        for(Book book: books)
            System.out.print(gson.toJson(book));
        
    }


}
