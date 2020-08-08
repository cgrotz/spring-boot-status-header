package de.cgrotz.spring.status.webflux;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public WebFluxInterceptor statusWebFluxInterceptor() {
        return new WebFluxInterceptor();
    }
}
