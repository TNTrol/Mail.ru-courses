import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import javax.inject.Named;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {

    private @NotNull LoggerInterface _logger;

    @Inject
    public Application(@Named("File") LoggerInterface logger)
    {
        _logger = logger;
    }

    public void waitForInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Waiting for new lines. Key in Ctrl+D to exit.");
            while (true) {
                _logger.write(scanner.nextLine());
            }
        } catch (IllegalStateException | NoSuchElementException e) {
        }
    }
}
