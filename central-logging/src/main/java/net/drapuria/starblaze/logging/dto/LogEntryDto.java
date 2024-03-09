package net.drapuria.starblaze.logging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class LogEntryDto {

    private String level;
    private String message;
    private Instant timestamp;

}
