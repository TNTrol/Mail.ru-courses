import javax.ws.rs.*;

@Path("/")
public class Base {

    @GET
    @Produces("text/html; qs=0.1")
    public String getBase()
    {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>Base</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p><a href=\"/add/\">Добавить данные</a></p>\n" +
                "<p><a href=\"/show/html/\">Посмотреть данные в виде html стницы</a></p>\n" +
                "<p><a href=\"/remove/\">Удалить</a></p>\n" +
                "<p> <a href=\"/show/\">Посмотреть</a> все в виде json</p>"+
                "<p> <a href=\"/show/produce/momCat\">Посмотреть</a> все в виде json товары производителя momCat</p>"+

                "</body>\n" +
                "</html>";
    }

}
