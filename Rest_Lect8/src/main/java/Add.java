import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("/add")
public class Add {

    private final String fileName  = "add.html";
    private String _page;

    public Add(){
        InputStream is = Main.class.getResourceAsStream(fileName );
        try {
            _page = new String( is.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @POST
    public void post(@QueryParam("name") String name, @QueryParam("count") int count, @QueryParam("cost") double cost, @QueryParam("producer") String producer)  {
        //Product product1 ;
        Main.connect.addProduct(new Product(name, count, cost, producer));
    }
/*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void post1(Product product)
    {
        Main.connect.addProduct(product);
    }*/

    @GET
    @Produces("text/html; qs=0.1")
    public String get()
    {
        return _page;
    }

}
