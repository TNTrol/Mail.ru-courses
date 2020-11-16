import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowServlets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str = "<html><body><h2>All data</h2> <table border=\"1\" width=\"50%\" cellpadding=\"5\">";
        List<Product> list = Main.dataDao.getAll();
        for(Product product: list)
        {
            str += "<tr><td>" + product.getName() + "</td><td>" + product.getCount() + "</td><td>" + product.getCost() + "</td></tr>";
        }
        str += "</table></body></html>";
        response.getWriter().println(str);
    }
}
