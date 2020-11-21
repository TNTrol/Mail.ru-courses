import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/show")
public class Show {

    @Path("/html")
    @GET
    @Produces("text/html; qs=0.1")
    public String get()
    {
        String str = "<html><body><h2>All data</h2> <table border=\"1\" width=\"50%\" cellpadding=\"5\">";
        List<Product> list = Main.connect.getAll();
        for(Product product: list)
        {
            str += "<tr><td>" + product.getName() + "</td><td>" + product.getCount() + "</td><td>" + product.getCost() + "</td></tr>";
        }
        str += "</table></body></html>";
        return str;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJSON()
    {
        return Response.ok(Main.connect.getAll()).build();
    }
}
