package net.drapuria.starblaze.logging.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LogDto {

    @JsonProperty(required = false)
    private Long id;
    @JsonProperty(required = false)
    private String softwareName;
    @JsonProperty(required = false)
    private List<LogMessageDto> messages;

}