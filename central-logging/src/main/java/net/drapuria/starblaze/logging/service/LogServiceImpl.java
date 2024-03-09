package net.drapuria.starblaze.logging.service;

import net.drapuria.starblaze.logging.dto.LogEntryDto;
import net.drapuria.starblaze.logging.dto.LogMessageDto;
import net.drapuria.starblaze.logging.model.Log;
import net.drapuria.starblaze.logging.model.LogEntry;
import net.drapuria.starblaze.logging.repository.LogEntryRepository;
import net.drapuria.starblaze.logging.repository.LogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final LogEntryRepository entryRepository;

    public LogServiceImpl(LogRepository logRepository, LogEntryRepository entryRepository) {
        this.logRepository = logRepository;
        this.entryRepository = entryRepository;
    }

    @Override
    public void addToLog(LogMessageDto logMessage) {
        final Log log = this.logRepository.findBySoftwareName(logMessage.getSoftwareName())
                .orElseGet(() -> this.logRepository.save(new Log(logMessage.getSoftwareName(), new ArrayList<>())));
        this.entryRepository.save(LogEntry.builder()
                .log(log)
                .level(logMessage.getLevel())
                .message(logMessage.getMessage())
                .timestamp(logMessage.getTimestamp())
                .build());
    }

    @Override
    public void addToLog(List<LogMessageDto> logMessages) {
        final Map<String, Log> logs = new HashMap<>();
        logMessages.forEach(logMessage -> {
            final Log log = logs.computeIfAbsent(logMessage.getSoftwareName(), softwareName -> this.logRepository.findBySoftwareName(softwareName)
                    .orElseGet(() -> this.logRepository.save(new Log(softwareName, new ArrayList<>()))));
            this.entryRepository.save(LogEntry.builder()
                    .log(log)
                    .level(logMessage.getLevel())
                    .message(logMessage.getMessage())
                    .timestamp(logMessage.getTimestamp())
                    .build());
        });
    }

    @Override
    public void clearLog(Long id) {
        this.entryRepository.deleteAllByLog_Id(id);
    }

    @Override
    public List<LogEntryDto> getLog(Long id) {
        return this.entryRepository.findAllByLog_Id(id)
                .stream()
                .map(logEntry -> new LogEntryDto(logEntry.getLevel(), logEntry.getMessage(), logEntry.getTimestamp()))
                .toList();
    }

    @Override
    public List<LogEntryDto> getLog(Long id, int limit, int offset) {
        int page = 0; // page number
        int size = 250; // number of items per page
        Sort sort = Sort.by("timestamp").ascending(); // sort order

        PageRequest pageable = PageRequest.of(page, size, sort);
        Page<LogEntry> logEntries = entryRepository.findByLog_Id(id, pageable);
        return logEntries.getContent()
                .stream()
                .map(logEntry -> new LogEntryDto(logEntry.getLevel(), logEntry.getMessage(), logEntry.getTimestamp()))
                .toList();
    }

    @Override
    public List<Long> getLogIds() {
        return this.logRepository.findAll().stream().map(Log::getId).toList();
    }

    public long generateLogId() {
        return this.logRepository.count() + 1;
    }

    public long generateLogEntryId(Log log) {
        return this.entryRepository.countByLog(log) + 1;
    }
}