package net.drapuria.starblaze.logger.record;


import net.drapuria.starblaze.logger.configuration.Configuration;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogRecord;

public class LogRecordPublisher  {

    private final Configuration configuration;
    private final LogRecordQueue queue;
    private final ScheduledExecutorService executorService;
    private final boolean ownExecutorService;
    private final ScheduledFuture<?> scheduledFuture;
    private final URL apiUrl;
    public LogRecordPublisher(Configuration configuration, LogRecordQueue queue) throws MalformedURLException {
        this.configuration = configuration;
        this.queue = queue;
        if (configuration.getExecutorService() == null) {
            this.ownExecutorService = true;
            this.executorService = Executors.newSingleThreadScheduledExecutor();
        } else {
            this.ownExecutorService = false;
            this.executorService = configuration.getExecutorService();
        }
        this.scheduledFuture = this.executorService.scheduleWithFixedDelay(this::publishAll, 5, 10, TimeUnit.SECONDS);
        this.apiUrl = new URL(configuration.getApiUrl());
    }

    public void shutdown() {
        this.scheduledFuture.cancel(false);
        if (this.ownExecutorService) {
            this.executorService.shutdown();
        }
        this.publishAll();
    }

    public void publishAll() {
        if (this.queue.isEmpty()) {
            return;
        }
        try {
            this.publish(this.queue.removeAll());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void publish() throws IOException {
        if (this.queue.isEmpty()) {
            return;
        }
        if (this.queue.size() > 250) {
            this.publish(this.queue.removeAll(250));
        } else {
            this.publish(this.queue.removeAll());
        }
    }


    private void publish(List<LogRecord> logRecord) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + configuration.getApiKey());
        connection.setDoOutput(true);
        final StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (LogRecord record : logRecord) {
            jsonBuilder.append("{");
            jsonBuilder.append("\"software_name\": \"").append(configuration.getSoftwareName()).append("\",");
            jsonBuilder.append("\"level\": \"").append(record.getLevel().getName()).append("\",");
            jsonBuilder.append("\"message\": \"").append(record.getMessage()).append("\",");
            jsonBuilder.append("\"timestamp\": ").append(record.getMillis());
            jsonBuilder.append("},");
        }
        jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
        jsonBuilder.append("]");
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBuilder.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            throw new IOException("Failed to send log records to the server");
        }
        connection.disconnect();
    }
}