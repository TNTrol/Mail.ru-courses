import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("/add")
public class Add {

    private final String _page;
    private final DataDAO _connect;

    @Inject
    public Add(@Named("AddPage") String fileName, DataDAO dataDAO)
    {
        _page = FileUtils.readFromResources(fileName, "<h1>Error of server</h1>");
        _connect = dataDAO;
    }

    @POST
    public void postAdd(@QueryParam("name") String name,
                     @QueryParam("count") int count,
                     @QueryParam("cost") double cost,
                     @QueryParam("producer") String producer)
    {
        _connect.addProduct(new Product(name, count, cost, producer));
    }

    @GET
    @Produces("text/html; qs=0.1")
    public String getAddPageHTML()
    {
        return _page;
    }
/*
    Почему-то не работает, много что перепробовал ,но проверял только с кодом из js клиента
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void postAddJSON(Product product)
    {
        _connect.addProduct(product);
    }*/
}
