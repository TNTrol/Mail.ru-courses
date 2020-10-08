import java.io.FileWriter;

public class FileLogger extends AbstractFileWriter implements LoggerInterface {

    private int _N = 0;

    public FileLogger()
    {
        initWriter("log.txt");
    }

    @Override
    public int getN() {
        return _N++;
    }

    @Override
    public void write(String str) {
        writeToFile(str);
    }
}
