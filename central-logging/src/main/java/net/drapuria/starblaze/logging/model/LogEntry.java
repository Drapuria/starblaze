package net.drapuria.starblaze.logging.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogEntry {


    @Id
    private Long id;
    private String level;
    private String message;
    private Instant timestamp;
    private Log log;

    public LogEntry(String level, String message, Instant timestamp, Log log) {
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
        this.log = log;
    }
}
