package net.drapuria.starblaze.logger.handler;

public class ShutdownHandler extends Thread {

    private final StarblazeLoggingHandler handler;

    public ShutdownHandler(StarblazeLoggingHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        handler.getPublisher().shutdown();
    }
}
