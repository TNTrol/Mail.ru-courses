import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("/")
public class Base {

    private String _page;

    public Base(){
        InputStream is = Main.class.getResourceAsStream("remove.html" );
        try {
            _page = new String( is.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces("text/html; qs=0.1")
    public String getBase()
    {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p><a href=\"/add/\">Добавить данные</a></p>\n" +
                "<p><a href=\"/show/\">Посмотреть данные</a></p>\n" +
                "<p><a href=\"/remove/\">Удалить</a></p>\n" +
                "</body>\n" +
                "</html>";
    }

    @Path("/remove")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(@QueryParam("name") String name)
    {
        Product product = Main.connect.deleteProduct(name);
        if(product == null){
            return Response.status(404).build();
        }
        return Response.ok(product).build();
    }

    @Path("/remove")
    @GET
    public String getRem()
    {
        return _page;
    }

    @Path("/showOfProducer")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOfProducer(@QueryParam("name") String producer)
    {
        return Response.ok(Main.connect.getAllOfProducer(producer)).build();
    }
}
