import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/remove")
public class Remove {

    private final String _page;
    private final DataDAO _connect;

    @Inject
    public Remove(@Named("RemovePage") String fileName, DataDAO connect)
    {
        _page = FileUtils.readFromResources(fileName, "<h1>Error of server</h1>");
        _connect = connect;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postRemove(@QueryParam("name") String name)
    {
        Product product = _connect.deleteProduct(name);
        if(product == null)
            return Response.status(404).build();
        return Response.ok(product).build();
    }

    @GET
    public String getRemovePageHTML()
    {
        return _page;
    }
}
