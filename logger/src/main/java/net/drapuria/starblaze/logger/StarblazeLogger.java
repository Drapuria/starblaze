package net.drapuria.starblaze.logger;

import net.drapuria.starblaze.logger.configuration.Configuration;
import net.drapuria.starblaze.logger.handler.ShutdownHandler;
import net.drapuria.starblaze.logger.handler.StarblazeLoggingHandler;

import java.util.logging.ConsoleHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public final class StarblazeLogger {

    private final Configuration configuration;

    private StarblazeLogger(Configuration configuration) {
        this.configuration = configuration;
        this.register();
    }

    private void register() {
        if (this.configuration.isOverrideDefaultLogging()) {
            LogManager.getLogManager().reset();
        }
        StarblazeLoggingHandler handler = new StarblazeLoggingHandler(this.configuration);
        Runtime.getRuntime().addShutdownHook(new ShutdownHandler(handler));
        if (this.configuration.isOverrideDefaultLogging()) {
            Logger rootLogger = this.configuration.getParentLogger();
            rootLogger.setLevel(this.configuration.getLogLevel());
            rootLogger.setUseParentHandlers(false);
            if (this.configuration.isLogToConsole()) {
                ConsoleHandler consoleHandler = new ConsoleHandler();
                consoleHandler.setLevel(this.configuration.getLogLevel());
                rootLogger.addHandler(consoleHandler);
            }
            rootLogger.addHandler(handler);
            return;
        }
        Logger.getGlobal().addHandler(handler);
    }

    @SuppressWarnings("unused")
    public static StarblazeLogger setup(Configuration configuration) {
        return new StarblazeLogger(configuration);
    }
}