package net.drapuria.starblaze.logging.configuration;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.annotation.PostConstruct;
import net.drapuria.starblaze.logging.deserializer.InstantMillisSerializer;
import net.drapuria.starblaze.logging.deserializer.MillisInstantDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.Instant;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder(MillisInstantDeserializer millisInstantDeserializer,
                                                      InstantMillisSerializer instantMillisSerializer) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Instant.class, millisInstantDeserializer);
        module.addSerializer(Instant.class, instantMillisSerializer);

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().modules(module);
        builder.propertyNamingStrategy(PropertyNamingStrategies.SnakeCaseStrategy.INSTANCE);
        return builder;
    }

    @Autowired
    private Environment env;

    @PostConstruct
    public void printProperty() {
        System.out.println("Spring data mongodb database: " + env.getProperty("spring.data.mongodb.database"));
        System.out.println("Logging Timezone: " + env.getProperty("logging.timezone"));
    }
}