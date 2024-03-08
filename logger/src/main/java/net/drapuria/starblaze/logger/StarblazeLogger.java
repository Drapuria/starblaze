package net.drapuria.starblaze.logger;

import net.drapuria.starblaze.logger.configuration.Configuration;

public final class StarblazeLogger {

    private final Configuration configuration;

    private StarblazeLogger(Configuration configuration) {
        this.configuration = configuration;
    }

    public static StarblazeLogger setup(Configuration configuration) {
        return new StarblazeLogger(configuration);
    }
}