public class ConsoleLogger implements LoggerInterface{

    private int _N = 0;
    @Override
    public int getN()
    {
        return _N++;
    }
    public void write(String str) {
        System.out.println(getN() + " " + str);
    }
}
