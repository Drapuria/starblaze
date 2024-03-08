package net.drapuria.starblaze.logger.configuration;

import java.util.logging.Level;

public interface Configuration {

    boolean isOverrideDefaultLogging();

    Level getLogLevel();

    boolean isLogToConsole();

    boolean isMonolithicLogging();

}