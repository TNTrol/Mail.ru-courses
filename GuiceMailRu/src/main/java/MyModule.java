import com.google.inject.AbstractModule;

import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.MapBinder;


public class MyModule extends AbstractModule {

    @Override
    protected void configure() {

        install(new FactoryModuleBuilder().implement(LoggerInterface.class, ConsoleLogger.class)
                .build(new TypeLiteral<LoggerFactory<ConsoleLogger>>() {}));
        install(new FactoryModuleBuilder().implement(LoggerInterface.class, FileLogger.class)
                .build(new TypeLiteral<LoggerFactory<FileLogger>>() {}));
        install(new FactoryModuleBuilder().implement(LoggerInterface.class, FileAndConsoleLogger.class)
                .build(new TypeLiteral<LoggerFactory<FileAndConsoleLogger>>() {}));
        MapBinder<String, LoggerFactory> binder = MapBinder.newMapBinder(binder(), String.class, LoggerFactory.class);
        binder.addBinding("Console").to(new TypeLiteral<LoggerFactory<ConsoleLogger>>(){});
        binder.addBinding("File").to(new TypeLiteral<LoggerFactory<FileLogger>>(){});
        binder.addBinding("FileAndConsole").to(new TypeLiteral<LoggerFactory<FileAndConsoleLogger>>(){});
    }
}
