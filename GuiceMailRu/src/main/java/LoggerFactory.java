public interface LoggerFactory<T extends LoggerInterface > {

    T createLogger();
}
