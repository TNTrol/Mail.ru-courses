import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

    public static void json()
    {
        Library lib = new Library();
        Author author = new Author("Pushkin", "1800");
        Author author1 = new Author("Tolstoy", "1800");
        Author author2 = new Author("Bulgakov", "1891");
        Book book1 = new Book("War and peace", "bolconsky", author1);
        Book book2 = new Book("Evgeniy Onegin", "My uncle", author);
        Book book3 = new Book("Master and Margarite", "Пятый всадний иудеи Понтий Пилат", author2);
        Book book = new Book("Собачье сердце", "Преображенский", author2);
        lib.addBook(book);
        lib.addBook(book1);
        lib.addBook(book2);
        lib.addBook(book3);
        try {


            PrintWriter writer = new PrintWriter("../lib.json", "UTF-8");
            Gson gson = new Gson();
            writer.println(gson.toJson(lib.getBooks()));
            writer.close();
        }catch (Exception e){

        }
    }
}
