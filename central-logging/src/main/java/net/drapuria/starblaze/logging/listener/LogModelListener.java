package net.drapuria.starblaze.logging.listener;

import net.drapuria.starblaze.logging.model.Log;
import net.drapuria.starblaze.logging.service.LogServiceImpl;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class LogModelListener extends AbstractMongoEventListener<Log> {

    private final LogServiceImpl logService;

    public LogModelListener(LogServiceImpl logService) {
        this.logService = logService;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Log> event) {
        final Log log = event.getSource();
        log.setId(this.logService.generateLogId());
    }
}
