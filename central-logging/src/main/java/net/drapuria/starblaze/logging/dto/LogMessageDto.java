package net.drapuria.starblaze.logging.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import net.drapuria.starblaze.logging.deserializer.MillisInstantDeserializer;

import java.time.Instant;

@Data
public class LogMessageDto {

    private String softwareName;
    private String level;
    private String message;
    private Instant timestamp;

}