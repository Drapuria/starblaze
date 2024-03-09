package net.drapuria.starblaze.logger.record;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.LogRecord;

public class LogRecordQueue {

    private final Queue<LogRecord> queue = new ConcurrentLinkedQueue<>();


    public void add(LogRecord record) {
        queue.add(record);
    }

    public LogRecord poll() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void clear() {
        queue.clear();
    }

    public List<LogRecord> removeAll(int amount) {
        List<LogRecord> records = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            LogRecord record = this.poll();
            if (record != null) {
                records.add(record);
            } else if (queue.isEmpty()) {
                break;
            }
        }
        return records;
    }

    public List<LogRecord> removeAll() {
        List<LogRecord> records = new ArrayList<>(queue);
        this.clear();
        return records;
    }
}