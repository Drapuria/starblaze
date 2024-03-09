package net.drapuria.starblaze.logger.handler;

import net.drapuria.starblaze.logger.configuration.Configuration;
import net.drapuria.starblaze.logger.record.LogRecordPublisher;
import net.drapuria.starblaze.logger.record.LogRecordQueue;

import java.net.MalformedURLException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class StarblazeLoggingHandler extends Handler {

    private final Configuration configuration;
    private final LogRecordQueue queue;
    private final LogRecordPublisher publisher;

    public StarblazeLoggingHandler(Configuration configuration) {
        this.configuration = configuration;
        this.queue = new LogRecordQueue();
        try {
            this.publisher = new LogRecordPublisher(configuration, queue);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void publish(LogRecord record) {
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}
