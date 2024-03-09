package net.drapuria.starblaze.logger.configuration;

import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface Configuration {

    String getSoftwareName();

    boolean isOverrideDefaultLogging();

    Level getLogLevel();

    boolean isLogToConsole();


    Logger getParentLogger();

    String getApiUrl();

    String getApiKey();

    ScheduledExecutorService getExecutorService();

}