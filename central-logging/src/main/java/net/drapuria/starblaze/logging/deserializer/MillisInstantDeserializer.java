package net.drapuria.starblaze.logging.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;

@Component
public class MillisInstantDeserializer extends JsonDeserializer<Instant> {

    private final ZoneId zoneId;

    public MillisInstantDeserializer(@Value("${logging.timezone}") String timeZone) {
        this.zoneId = ZoneId.of(timeZone);
    }

    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        long millis = jsonParser.getLongValue();
        Instant instant = Instant.ofEpochMilli(millis);
        instant.atZone(zoneId);
        return instant;
    }
}