import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import javax.inject.Named;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {

    private @NotNull LoggerInterface _logger;
    private @NotNull Map<String, LoggerFactory> _loggerFactoryMap;

    @Inject
    public Application(Map<String, LoggerFactory> loggerFactoryMap)
    {
        _loggerFactoryMap = loggerFactoryMap;
        _logger = readTypeLogging();
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

    private LoggerInterface readTypeLogging()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите наименование ражима логирования( Console, File, FileAndModule)");
        String key = scanner.next();
        LoggerInterface logger = _loggerFactoryMap.get(key).createLogger();
        return logger;
    }
}
