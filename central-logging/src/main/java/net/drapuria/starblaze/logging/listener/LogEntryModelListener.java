package net.drapuria.starblaze.logging.listener;

import net.drapuria.starblaze.logging.model.LogEntry;
import net.drapuria.starblaze.logging.service.LogServiceImpl;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class LogEntryModelListener extends AbstractMongoEventListener<LogEntry> {

    private final LogServiceImpl logService;

    public LogEntryModelListener(LogServiceImpl logService) {
        this.logService = logService;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<LogEntry> event) {
        final LogEntry logEntry = event.getSource();
        logEntry.setId(this.logService.generateLogEntryId(logEntry.getLog()));
    }
}
