import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.name.Names;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;


import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("NotNullNullableValidation")
public final class GuiceListener extends GuiceResteasyBootstrapServletContextListener {
  private final DataDAO _dataDAO;

  public GuiceListener(DataDAO dataDAO)
  {
    super();
    _dataDAO = dataDAO;
  }

  @Override
  protected List<? extends Module> getModules(ServletContext context) {
    return Collections.singletonList(new GuiceModule(_dataDAO));
  }

  @SuppressWarnings("rawtypes")
  private static final class GuiceModule extends AbstractModule {

    private final DataDAO _dataDAO;

    public GuiceModule(DataDAO dataDAO)
    {
      _dataDAO = dataDAO;
    }
    @SuppressWarnings("PointlessBinding")
    @Override
    protected void configure() {
      //bind(JacksonMessageBodyHandler.class).toInstance(new JacksonMessageBodyHandler());

      bind(String.class).annotatedWith(Names.named("AddPage")).toInstance("add.html");
      bind(String.class).annotatedWith(Names.named("RemovePage")).toInstance("remove.html");
      bind(DataDAO.class).toInstance(_dataDAO);
      bind(Add.class);
      bind(Show.class);
      bind(Base.class);
      bind(Remove.class);

    }
  }
}
