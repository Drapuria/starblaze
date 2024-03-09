package net.drapuria.starblaze.logging.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LogDto {

    @JsonProperty()
    private Long id;
    @JsonProperty()
    private String softwareName;
    @JsonProperty()
    private List<LogMessageDto> messages;

}