import com.google.inject.AbstractModule;

public class MyModule extends AbstractModule {
    @Override
    protected void configure() {
        //bind(IBooksFactory.class).to(FileBooksFactory.class);
        bind(IBooksFactory.class).toProvider(FactoryProvide.class);
    }
}
