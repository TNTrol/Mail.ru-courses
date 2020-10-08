import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        final Injector injector = Guice.createInjector(new MyModule());
        injector.getInstance(Application.class).waitForInput();
    }
}
