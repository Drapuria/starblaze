package net.drapuria.starblaze.logger.configuration;

import lombok.Builder;

import java.util.logging.Level;

@Builder
public class LoggingConfiguration implements Configuration {

    private boolean overrideDefaultLogging;
    @Builder.Default
    private Level logLevel = Level.ALL;
    @Builder.Default
    private boolean logToConsole = true;
    @Builder.Default
    private boolean monolithicLogging = true;

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
    public boolean isMonolithicLogging() {
        return this.monolithicLogging;
    }


}
