package net.drapuria.starblaze.logging.service;

import net.drapuria.starblaze.logging.dto.LogDto;
import net.drapuria.starblaze.logging.dto.LogEntryDto;
import net.drapuria.starblaze.logging.dto.LogMessageDto;
import net.drapuria.starblaze.logging.model.LogEntry;

import java.util.List;

public interface LogService {

    void addToLog(LogMessageDto logMessage);

    void addToLog(List<LogMessageDto> logMessages);

    void clearLog(Long id);

    List<LogEntryDto> getLog(Long id);

    List<LogEntryDto> getLog(Long id, int limit, int offset);

    List<Long> getLogIds();
}