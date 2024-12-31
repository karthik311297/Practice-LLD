package lld.logger;

public class Demo {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger();
        logger.debug("Debug message");
        logger.info("Informational message");
        logger.error("Error message");
        logger.warn("Warning message");
    }
}
