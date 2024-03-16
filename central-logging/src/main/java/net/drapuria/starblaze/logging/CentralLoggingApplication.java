package net.drapuria.starblaze.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CentralLoggingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentralLoggingApplication.class, args);
    }

}