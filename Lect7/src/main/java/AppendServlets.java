import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class AppendServlets extends HttpServlet {
    protected byte[] page;

    public AppendServlets(String nameFile) throws IOException {
        InputStream is = Main.class.getResourceAsStream(nameFile);
        page = is.readAllBytes();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getOutputStream().write(page);
        response.getOutputStream().flush();
    }

    protected  void doPost(HttpServletRequest request, HttpServletResponse response) {
        var param = request.getParameterMap();
        if(param.size() == 0)
            return;
        String[] name = param.get("name");
        String[] count = param.get("count");
        String[] cost = param.get("cost");
        int countInt = Integer.parseInt(count[0]);
        double costInt = Double.parseDouble(cost[0]);
        Main.dataDao.addProduct(new Product(name[0], countInt, costInt));
    }

}
