import java.io.FileWriter;

public class FileAndConsoleLogger extends AbstractFileWriter implements LoggerInterface
{
    private FileWriter _writer;
    private int _N = 0;
    public FileAndConsoleLogger()
    {
        initWriter("log.txt");
    }

    @Override
    public int getN() {
        return _N++;
    }

    @Override
    public void write(String str) {
        System.out.println(getN() + " " + str);
        writeToFile(str);
    }
}
