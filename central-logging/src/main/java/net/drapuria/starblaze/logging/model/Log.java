package net.drapuria.starblaze.logging.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Log {

    @Id
    private long id;
    private String softwareName;
    private List<LogEntry> logs;

    public Log(String softwareName, List<LogEntry> logs) {
        this.softwareName = softwareName;
        this.logs = logs;
    }
}
