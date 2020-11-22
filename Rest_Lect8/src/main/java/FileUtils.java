import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    public static String readFromResources(String fileName, String defaultValue)
    {
        InputStream is = Main.class.getResourceAsStream(fileName );
        try {
            String page = new String( is.readAllBytes());
            return page;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
