import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

public class Main {

    public static void main(String[] args) throws Exception {
        final Server server = JettyServer.build();
        final DataDAO connect = new DataDAO("products");
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.addServlet(HttpServletDispatcher.class, "/");
        context.addEventListener(new GuiceListener(connect));

        server.setHandler(context);
        server.start();
    }
}
