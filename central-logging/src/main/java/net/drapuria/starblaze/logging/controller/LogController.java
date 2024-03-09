package net.drapuria.starblaze.logging.controller;

import net.drapuria.starblaze.logging.dto.LogMessageDto;
import net.drapuria.starblaze.logging.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public ResponseEntity<?> log(@RequestBody LogMessageDto logMessageDto) {
        logService.addToLog(logMessageDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> bulkLog(@RequestBody LogMessageDto[] logMessages) {
        logService.addToLog(List.of(logMessages));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/clear")
    public ResponseEntity<?> clearLog(@RequestBody Long id) {
        logService.clearLog(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<Long>> getLogs() {
        return ResponseEntity.ok(logService.getLogIds());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getLog(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(logService.getLog(id));
    }


    @GetMapping("/{id}/limit")
    public ResponseEntity<?> getLog(@PathVariable(name = "id") long id, @RequestParam(defaultValue = "250") int limit,
                                     @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(logService.getLog(id, limit, offset));
    }
}