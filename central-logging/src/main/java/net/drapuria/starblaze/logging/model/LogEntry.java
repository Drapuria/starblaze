package net.drapuria.starblaze.logging.model;

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
@NoArgsConstructor
@Builder
public class LogEntry {

    @Id
    private Long id;
    private LogType logType;
    private Instant timestamp;
    private String message;

}
