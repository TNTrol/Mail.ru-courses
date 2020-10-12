import com.google.inject.Provider;

import java.util.Scanner;

public class FactoryProvide implements Provider<IBooksFactory> {
    @Override
    public IBooksFactory get() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь");
        String str = scanner.nextLine();
        return new FileBooksFactory(str);
    }
}
