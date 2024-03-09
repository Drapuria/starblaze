package net.drapuria.starblaze.logging.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.TimeZone;

@Component
public class MillisInstantDeserializer extends JsonDeserializer<Instant> {

    private final ZoneId zoneId;

    public MillisInstantDeserializer(@Value("${logging.timezone:default Europe/Berlin}") String timeZone) {
        this.zoneId = ZoneId.of(TimeZone.getTimeZone("Europe/Berlin").getID());
    }

    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        long millis = jsonParser.getLongValue();
        Instant instant = Instant.ofEpochMilli(millis);
        instant.atZone(zoneId);
        return instant;
    }
}