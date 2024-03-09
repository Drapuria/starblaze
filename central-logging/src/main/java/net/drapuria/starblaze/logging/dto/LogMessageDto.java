package net.drapuria.starblaze.logging.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class LogMessageDto {

    private String softwareName;
    private String level;
    private String message;
    private Instant timestamp;

}