import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        Injector injector = Guice.createInjector(new MyModule());
        Library lib =  injector.getInstance(LibraryFactory.class).library(count);
        lib.printLibrary();
    }
}
