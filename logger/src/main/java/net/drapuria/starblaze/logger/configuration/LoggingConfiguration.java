package net.drapuria.starblaze.logger.configuration;

import lombok.Builder;
import lombok.NonNull;

import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

@Builder
public class LoggingConfiguration implements Configuration {

    private String softwareName;
    private boolean overrideDefaultLogging;
    @Builder.Default
    private Level logLevel = Level.ALL;
    @Builder.Default
    private boolean logToConsole = true;
    @Builder.Default
    private Logger parentLogger = Logger.getGlobal();
    private String apiUrl;
    private String apiKey;
    private ScheduledExecutorService executorService;

    @Override
    public String getSoftwareName() {
        return this.softwareName;
    }

    @Override
    public boolean isOverrideDefaultLogging() {
        return this.overrideDefaultLogging;
    }

    @Override
    public Level getLogLevel() {
        return this.logLevel;
    }

    @Override
    public boolean isLogToConsole() {
        return this.logToConsole;
    }

    @Override
    public Logger getParentLogger() {
        return this.parentLogger;
    }

    @Override
    public String getApiUrl() {
        return this.apiUrl;
    }

    @Override
    public String getApiKey() {
        return this.apiKey;
    }

    @Override
    public ScheduledExecutorService getExecutorService() {
        return this.executorService;
    }
}