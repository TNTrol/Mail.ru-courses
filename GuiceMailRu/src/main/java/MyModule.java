import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class MyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LoggerInterface.class).annotatedWith(Names.named("Console")).to(ConsoleLogger.class);
        bind(LoggerInterface.class).annotatedWith(Names.named("File")).to(FileLogger.class);
        bind(LoggerInterface.class).annotatedWith(Names.named("FileAndConsole")).to(FileAndConsoleLogger.class);
    }
}
