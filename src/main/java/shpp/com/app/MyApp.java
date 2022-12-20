package shpp.com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shpp.com.services.Calculate;
import shpp.com.services.Receiver;

public class MyApp {
    private static final Logger logger = LoggerFactory.getLogger(MyApp.class);

    public static void main(String[] args) {
        logger.info("Start app ... ");
        Receiver receiver = new Receiver();
        receiver.receive();
        Calculate calculate = new Calculate();
        calculate.operation(receiver.getWorkpiece().getMaterial(), receiver.getWorkpiece());
        logger.info("End app ... ");
    }
}
