import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import java.net.URL;

public class Main {

    public final static DataDAO dataDao = new DataDAO("products");;

    public static void main(String[] args) throws Exception {
        final Server server = new DefaultServer().build();

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.NO_SESSIONS
        );
        context.setContextPath("/");

        final URL resource = Main.class.getResource("/home.html");
        context.setBaseResource(Resource.newResource(resource.toExternalForm()));
        context.setWelcomeFiles(new String[]{"/home.html", "/show.html"});
        context.addServlet(
                new ServletHolder(
                        "default",
                        DefaultServlet.class
                ),
                "/"
        );
        context.addServlet(
                new ServletHolder("sync", new AppendServlets("/add.html")),
                "/add/*"
        );
        context.addServlet(
                new ServletHolder("s", new ShowServlets()),
                "/show/*"
        );

        final String hashConfig = Main.class.getResource("/hash_config").toExternalForm();
        final HashLoginService hashLoginService = new HashLoginService("login", hashConfig);
        final ConstraintSecurityHandler securityHandler = new SecurityHandlerBuilder().build(hashLoginService);
        server.addBean(hashLoginService);
        securityHandler.setHandler(context);
        server.setHandler(securityHandler);

        server.start();
    }
}
