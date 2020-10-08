import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args)
    {
        final Injector injector = Guice.createInjector(new MyModule());
        injector.getInstance(Application.class).waitForInput();
    }
}
