package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        char charS = '.';
        byte byteS = 127;
        short shortS = 32000;
        int intS = 32405;
        long longS = 30231204L;
        boolean bS = true;
        float floatS = 2.3F;
        double doubleS = 120.234D;
        LOG.debug("All primitive types: char {} byte {} short {} "
                + "int {} long {} boolean {} float {} double {}", charS,
                byteS, shortS, intS, longS, bS, floatS, doubleS);
    }
}
